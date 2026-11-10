package io.github.chiselteam.chiselcompat.content.compat;

import io.github.chiselteam.chiselcompat.content.ChiselFamily;
import io.github.chiselteam.chiselcompat.content.compat.allthemods.*;
import io.github.chiselteam.chiselcompat.content.compat.appliedenergistics.*;
import io.github.chiselteam.chiselcompat.content.compat.dyenamics.*;
import io.github.chiselteam.chiselcompat.content.compat.moddedmetal.*;
import io.github.chiselteam.chiselcompat.content.compat.neovitae.*;
import io.github.chiselteam.chiselcompat.content.compat.thaumcraft.*;
import io.github.chiselteam.chiselcompat.content.family.stone.RoseQuartzFamily;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public final class CompatFamilies {
    private CompatFamilies() {}
    public static final List<CompatModule> MODULES = List.of(
            new CompatModule(List.of("allthemodium"), () -> List.of(AtmAncientStoneFamily.FAMILY)),
            new CompatModule(List.of("ae2"), () -> List.of(Ae2CertusFamily.FAMILY, Ae2FluixFamily.FAMILY, Ae2SkyStoneFamily.FAMILY)),
            new CompatModule(List.of("create"), () -> List.of(RoseQuartzFamily.FAMILY)),
            new CompatModule(List.of("dyenamics"), () -> Stream.of(DnConcreteFamily.FAMILY, DnStainedGlassFamily.FAMILY, DnOakFramedStainedGlassFamily.FAMILY, DnSteelFramedStainedGlassFamily.FAMILY).flatMap(List::stream).toList()),
            new CompatModule(List.of("forbidden_arcanus", "thaumcraft"), () -> List.of(TCArcaneStoneFamily.FAMILY)),
            new CompatModule(List.of("thaumcraft"), () -> List.of(TCThaumiumFamily.FAMILY)),
            new CompatModule(List.of("thaumcraft", "occultism"), () -> List.of(TCTallowFamily.FAMILY)),
            new CompatModule(List.of("neovitae"), () -> List.of(NvRuneFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "ftbmaterials", "immersiveengineering"), () -> List.of(AluminumFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "gtceu", "modern_industrialization"), () -> List.of(AntimonyFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "create", "ftbmaterials", "gtceu", "modern_industrialization"), () -> List.of(BrassFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "ftbmaterials", "mekanism", "modern_industrialization", "thermal"), () -> List.of(BronzeFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "justdirethings"), () -> List.of(CharcoalFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "gtceu", "modern_industrialization"), () -> List.of(ChromiumFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "immersiveengineering", "railcraft"), () -> List.of(CoalCokeFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "gtceu", "tconstruct"), () -> List.of(CobaltFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "immersiveengineering", "thermal"), () -> List.of(ConstantanFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "immersiveengineering", "thermal"), () -> List.of(ElectrumFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "thermal"), () -> List.of(EnderiumFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "mekanism"), () -> List.of(FluoriteFamily.FAMILY)),
            new CompatModule(List.of("bigreactors", "extremereactors", "ftbmaterials", "modern_industrialization"), () -> List.of(GraphiteFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "ftbmaterials", "railcraft", "thermal"), () -> List.of(InvarFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "gtceu", "modern_industrialization"), () -> List.of(IridiumFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "ftbmaterials", "immersiveengineering", "mekanism", "railcraft", "thermal"), () -> List.of(LeadFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "thermal"), () -> List.of(LumiumFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "ftbmaterials", "immersiveengineering", "oritech", "railcraft", "thermal"), () -> List.of(NickelFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "ftbmaterials", "mekanism"), () -> List.of(OsmiumFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "projectred_core", "silentgems"), () -> List.of(PeridotFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "ftbmaterials", "oritech", "thermal"), () -> List.of(PlatinumFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "gtceu", "mekanism", "modern_industrialization"), () -> List.of(PlutoniumFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "gtceu", "modern_industrialization", "projectred_core", "silentgems"), () -> List.of(RubyFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "mekanism", "mekanismadditions"), () -> List.of(SaltFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "gtceu", "modern_industrialization", "projectred_core", "silentgems"), () -> List.of(SapphireFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "thermal"), () -> List.of(SignalumFamily.FAMILY)),
            new CompatModule(List.of("ae2", "ftbmaterials", "gtceu", "modern_industrialization"), () -> List.of(SiliconFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "ftbmaterials", "immersiveengineering", "occultism", "railcraft", "silentgems", "thermal"), () -> List.of(SilverFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "gtceu", "modern_industrialization"), () -> List.of(StainlessSteelFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "energizedpower", "ftbmaterials", "immersiveengineering", "mekanism", "oritech", "railcraft"), () -> List.of(SteelFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "energizedpower", "ftbmaterials", "mekanism", "modern_industrialization", "railcraft", "thermal"), () -> List.of(TinFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "gtceu", "modern_industrialization"), () -> List.of(TitaniumFamily.FAMILY)),
            new CompatModule(List.of("ftbmaterials", "gtceu", "modern_industrialization"), () -> List.of(TungstenFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "ftbmaterials", "immersiveengineering", "mekanism"), () -> List.of(UraniumFamily.FAMILY)),
            new CompatModule(List.of("alltheores", "create", "ftbmaterials", "gtceu", "modern_industrialization"), () -> List.of(ZincFamily.FAMILY)));

    public static List<ChiselFamily> enabledFamilies(boolean production, Predicate<String> isLoaded) {
        return enabledFamilies(MODULES, production, isLoaded);
    }

    static List<ChiselFamily> enabledFamilies(List<CompatModule> modules, boolean production, Predicate<String> isLoaded) {
        return modules.stream().filter(module -> module.isEnabled(production, isLoaded))
                .flatMap(module -> module.families().get().stream()).distinct().toList();
    }
}
