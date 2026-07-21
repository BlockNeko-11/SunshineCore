package io.github.blockneko11.sunshinecore.data.forge;

import io.github.blockneko11.sunshinecore.data.SDataGeneration;
import net.neoforged.neoforge.data.event.GatherDataEvent;

public final class SDataGenerationImpl {
    public static SDataGeneration create(GatherDataEvent e) {
        String modId = e.getModContainer().getModId();
        return new SDataGeneration(modId,
                e.validate(),
                e.getGenerator().getVanillaPack(true),
                e.getLookupProvider());
    }

    private SDataGenerationImpl() {
    }
}
