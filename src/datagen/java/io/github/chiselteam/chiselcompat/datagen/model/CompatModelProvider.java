package io.github.chiselteam.chiselcompat.datagen.model;

import io.github.chiselteam.chisel.api.family.Variant;
import io.github.chiselteam.chisel.api.family.VariantFamily;
import io.github.chiselteam.chiselcompat.ChiselCompat;
import io.github.chiselteam.chiselcompat.datagen.CompatData;
import io.github.chiselteam.chiselcompat.datagen.model.generator.*;
import io.github.chiselteam.chiselcompat.datagen.model.generator.ctm.*;
import io.github.chiselteam.chiselcompat.datagen.model.generator.special.GlassModelGenerator;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Stream;

public final class CompatModelProvider extends ModelProvider {
    private static final Map<String, Supplier<VariantModelGenerator>> GENERATORS = Map.ofEntries(
            Map.entry("cube_all", CubeAllModelGenerator::new),
            Map.entry("top_bottom_side", TopBottomSideModelGenerator::new),
            Map.entry("connected", CTMModelGenerator::new),
            Map.entry("ctmv", CTMVModelGenerator::new),
            Map.entry("ctmh", CTMHModelGenerator::new),
            Map.entry("glass", GlassModelGenerator::new),
            Map.entry("multiblock_2x2", Multiblock2x2ModelGenerator::new),
            Map.entry("multiblock_3x3", Multiblock3x3ModelGenerator::new),
            Map.entry("multiblock_4x4", Multiblock4x4ModelGenerator::new),
            Map.entry("ar", ARModelGenerator::new),
            Map.entry("r4", R4ModelGenerator::new),
            Map.entry("v4", V4ModelGenerator::new),
            Map.entry("v9", V9ModelGenerator::new),
            Map.entry("v16", V16ModelGenerator::new));

    public CompatModelProvider(PackOutput output) { super(output, ChiselCompat.MODID); }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        for (var family : CompatData.families()) {
            var definition = family.getDefinition();
            var modelFamily = new VariantFamily(definition.name());
            for (var entry : definition.variants()) {
                if (!entry.generateModel()) continue;
                var id = ChiselCompat.prefix(entry.name());
                if (!BuiltInRegistries.BLOCK.containsKey(id))
                    throw new IllegalStateException("Datagen block is not registered: " + id);
                var variant = new Variant(entry.name(), () -> BuiltInRegistries.BLOCK.getValue(id), modelFamily, entry.modelType())
                        .setTextures(definition.textures().getOrDefault(entry.name(), Map.of()));
                var generator = GENERATORS.get(entry.modelType().getSerializedName());
                if (generator == null) throw new IllegalStateException("Missing model generator: " + entry.modelType().getSerializedName());
                generator.get().generate(variant, blockModels);
            }
        }
    }

    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(ChiselCompat.MODID))
                .map(Block::builtInRegistryHolder);
    }
}
