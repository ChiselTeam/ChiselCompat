package io.github.chiselteam.chiselcompat.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

/** Self drops, conditional on registration so disabled integrations have no missing-item loot. */
public final class CompatLootProvider implements DataProvider {
    private final PackOutput output;
    public CompatLootProvider(PackOutput output) { this.output = output; }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        var futures = new ArrayList<CompletableFuture<?>>();
        for (var family : CompatData.families()) {
            for (var variant : family.getDefinition().variants()) {
                if (variant.isExistingBlock()) continue;
                String id = "chiselcompat:" + variant.name();
                var entry = new JsonObject();
                entry.addProperty("type", "minecraft:item");
                entry.addProperty("name", id);
                var entries = new JsonArray();
                entries.add(entry);
                var explosion = new JsonObject();
                explosion.addProperty("type", "minecraft:survives_explosion");
                var pool = new JsonObject();
                pool.addProperty("rolls", 1);
                pool.add("entries", entries);
                pool.add("condition", explosion);
                var pools = new JsonArray();
                pools.add(pool);
                var registered = new JsonObject();
                registered.addProperty("type", "neoforge:registered");
                registered.addProperty("registry", "minecraft:block");
                registered.addProperty("value", id);
                var conditions = new JsonArray();
                conditions.add(registered);
                var table = new JsonObject();
                table.addProperty("type", "minecraft:block");
                table.addProperty("random_sequence", "chiselcompat:blocks/" + variant.name());
                table.add("pools", pools);
                table.add("neoforge:conditions", conditions);
                futures.add(DataProvider.saveStable(cache, table, output.getOutputFolder(PackOutput.Target.DATA_PACK)
                        .resolve("chiselcompat/loot_table/blocks/" + variant.name() + ".json")));
            }
        }
        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }
    @Override
    public String getName() { return "Chisel Compat conditional block loot"; }
}
