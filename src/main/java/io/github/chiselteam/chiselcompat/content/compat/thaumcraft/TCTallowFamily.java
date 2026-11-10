package io.github.chiselteam.chiselcompat.content.compat.thaumcraft;

import io.github.chiselteam.chiselcompat.ChiselCompat;
import io.github.chiselteam.chisel.api.model.ChiselModelHandlers;
import io.github.chiselteam.chiselcompat.content.ChiselFamily;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public final class TCTallowFamily {
    public static final ChiselFamily FAMILY = ChiselFamily.build("tc_tallow", builder -> builder
            .defaults(v -> v.properties(BlockBehaviour.Properties.ofFullCopy(Blocks.WOOL.white())).blockName("Tallow"))
            .variant("tallow_block", v -> v.description("Block").model(ChiselModelHandlers.TBS)
                    .textureAlias("bottom", "top")
                    .texture("top", ChiselCompat.prefix("block/tc_tallow/tallow_smooth")))
            .variant("tallow_faces", v -> v.description("Pareidolia"))
            .variant("tallow_smooth", v -> v.description("Smooth")));

    private TCTallowFamily() {
    }
}
