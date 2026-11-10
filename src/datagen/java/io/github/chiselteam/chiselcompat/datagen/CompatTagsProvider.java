package io.github.chiselteam.chiselcompat.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public final class CompatTagsProvider implements DataProvider {
    private static final Set<String> PICKAXE_STONE = Set.of("ae2_certus", "ae2_fluix", "ae2_sky_stone",
            "rose_quartz", "atm_ancient_stone", "tc_arcane_stone", "tc_thaumium");
    private final PackOutput output;
    public CompatTagsProvider(PackOutput output) { this.output = output; }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Map<String, List<String>> tags = new TreeMap<>();
        for (var family : CompatData.families()) {
            var definition = family.getDefinition();
            String name = definition.name();
            var blocks = definition.variants().stream().filter(entry -> !entry.isExistingBlock())
                    .map(entry -> "chiselcompat:" + entry.name()).toList();
            var members = new ArrayList<>(CompatIntegrations.EXTERNAL_BLOCKS.getOrDefault(name, List.of()));
            members.addAll(blocks);
            tags.put("chiselcompat/tags/block/families/" + name, members);
            add(tags, "framedblocks/tags/block/frameable", blocks);
            boolean material = CompatIntegrations.MATERIALS.contains(name);
            if (material || PICKAXE_STONE.contains(name)) add(tags, "minecraft/tags/block/mineable/pickaxe", blocks);
            if (material) add(tags, "c/tags/block/storage_blocks", blocks);
            if ((material && !Set.of("charcoal", "coal_coke").contains(name)) || name.equals("tc_thaumium"))
                add(tags, "minecraft/tags/block/needs_stone_tool", blocks);
            if (name.startsWith("dn_") && name.contains("stained_glass")) {
                add(tags, "minecraft/tags/block/impermeable", blocks);
                add(tags, "c/tags/block/glass_blocks", blocks);
                add(tags, "c/tags/block/dyed", blocks);
            }
        }
        var futures = new ArrayList<CompletableFuture<?>>();
        tags.forEach((path, entries) -> {
            var values = new JsonArray();
            entries.stream().distinct().sorted().forEach(id -> {
                var entry = new JsonObject();
                entry.addProperty("id", id);
                entry.addProperty("required", false);
                values.add(entry);
            });
            var json = new JsonObject();
            json.add("values", values);
            futures.add(DataProvider.saveStable(cache, json, output.getOutputFolder(PackOutput.Target.DATA_PACK).resolve(path + ".json")));
        });
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }
    private static void add(Map<String, List<String>> tags, String path, List<String> blocks) {
        tags.computeIfAbsent(path, ignored -> new ArrayList<>()).addAll(blocks);
    }
    @Override
    public String getName() { return "Chisel Compat block tags"; }
}
