package io.github.chiselteam.chiselcompat;

import io.github.chiselteam.chisel.api.ChiselAPI;
import io.github.chiselteam.chisel.api.family.Variant;
import io.github.chiselteam.chisel.api.family.VariantFamily;
import io.github.chiselteam.chisel.block.item.ChiselBlockItem;
import io.github.chiselteam.chiselcompat.content.ChiselFamily;
import io.github.chiselteam.chiselcompat.content.compat.CompatFamilies;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.List;

@Mod(ChiselCompat.MODID)
public final class ChiselCompat {
    public static final String MODID = "chiselcompat";
    private final DeferredRegister.Blocks blocks = DeferredRegister.createBlocks(MODID);
    private final DeferredRegister.Items items = DeferredRegister.createItems(MODID);
    private final DeferredRegister<CreativeModeTab> tabs = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public ChiselCompat(IEventBus bus) {
        List<ChiselFamily> families = CompatFamilies.enabledFamilies(FMLEnvironment.isProduction(), ModList.get()::isLoaded);
        families.forEach(this::registerFamily);
        if (!families.isEmpty()) {
            tabs.register("compat", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.chiselcompat"))
                    .icon(() -> items.getEntries().iterator().next().get().getDefaultInstance())
                    .displayItems((parameters, output) -> items.getEntries().forEach(item -> output.accept(item.get())))
                    .build());
        }
        blocks.register(bus);
        items.register(bus);
        tabs.register(bus);
    }

    private void registerFamily(ChiselFamily family) {
        var definition = family.getDefinition();
        var itemFamily = new VariantFamily(prefix(definition.name()).toString());
        ChiselAPI.registerFamily(prefix(definition.name()), builder -> {
            builder.tag(definition.tag());
            for (var variant : definition.variants()) {
                if (variant.isExistingBlock()) {
                    builder.variant(variant.existingBlock(), variant.modelType());
                    continue;
                }
                var block = blocks.registerBlock(variant.name(), variant.blockFactory(), variant.properties());
                var itemVariant = new Variant(variant.name(), block::get, itemFamily, variant.modelType(), false);
                items.registerItem(variant.name(), properties -> new ChiselBlockItem(itemVariant, properties), () -> new Item.Properties().useBlockDescriptionPrefix());
                builder.variant(variant.name(), block, variant.modelType());
            }
        });
    }

    public static Identifier prefix(String path) {
        return Identifier.fromNamespaceAndPath(MODID, path);
    }
}
