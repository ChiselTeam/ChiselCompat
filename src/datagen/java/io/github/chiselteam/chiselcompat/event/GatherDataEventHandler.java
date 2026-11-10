package io.github.chiselteam.chiselcompat.event;

import io.github.chiselteam.chiselcompat.ChiselCompat;
import io.github.chiselteam.chiselcompat.datagen.*;
import io.github.chiselteam.chiselcompat.datagen.model.CompatModelProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ChiselCompat.MODID)
public final class GatherDataEventHandler {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(CompatModelProvider::new);
        event.createProvider(output -> new CompatLanguageProvider(output, "en_us"));
        event.createProvider(output -> new CompatLanguageProvider(output, "en_ud"));
        event.createProvider(CompatTagsProvider::new);
        event.createProvider(CompatLootProvider::new);
    }
}
