package io.github.chiselteam.chiselcompat.datagen;

import io.github.chiselteam.chiselcompat.ChiselCompat;
import io.github.chiselteam.chiselcompat.datagen.lang.LangConversionHelper;
import io.github.chiselteam.chiselcompat.datagen.lang.LangFormatSplitter;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public final class CompatLanguageProvider extends LanguageProvider {
    private final boolean upsideDown;
    public CompatLanguageProvider(PackOutput output, String locale) {
        super(output, ChiselCompat.MODID, locale);
        upsideDown = locale.equals("en_ud");
    }
    @Override
    protected void addTranslations() {
        translate("itemGroup.chiselcompat", "Chisel Compat");
        for (var family : CompatData.families()) {
            family.getDefinition().translations().forEach((name, translation) -> {
                String key = "block.chiselcompat." + name;
                translate(key, translation.blockName());
                translate(key + ".desc", translation.description());
            });
        }
    }
    private void translate(String key, String value) {
        add(key, upsideDown ? LangConversionHelper.convertComponents(LangFormatSplitter.split(value)) : value);
    }
}
