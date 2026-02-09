package io.github.blockneko11.sunshinecore.data.fabric;

import io.github.blockneko11.sunshinecore.data.SDataGeneration;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class SDataGenerationImpl {
    public static SDataGeneration create(FabricDataGenerator generator) {
        return new SDataGeneration(generator.getModId(),
                generator.isStrictValidationEnabled(),
                generator.createPack(),
                generator.getRegistries());
    }
}
