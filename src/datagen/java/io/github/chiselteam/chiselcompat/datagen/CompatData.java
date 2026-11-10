package io.github.chiselteam.chiselcompat.datagen;

import io.github.chiselteam.chiselcompat.content.ChiselFamily;
import io.github.chiselteam.chiselcompat.content.compat.CompatFamilies;
import java.util.List;

/** Datagen always covers every integration, regardless of installed provider mods. */
public final class CompatData {
    private CompatData() {}
    public static List<ChiselFamily> families() {
        return CompatFamilies.enabledFamilies(false, id -> false);
    }
}
