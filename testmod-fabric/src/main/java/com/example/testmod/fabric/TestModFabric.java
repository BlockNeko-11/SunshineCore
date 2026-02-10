package com.example.testmod.fabric;

import com.example.testmod.TestMod;
import io.github.blockneko11.sunshinecore.data.fabric.SDataGenerationImpl;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class TestModFabric implements ModInitializer, DataGeneratorEntrypoint {
    @Override
    public void onInitialize() {
        TestMod.init();
    }

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        TestMod.initDataGen(SDataGenerationImpl.create(fabricDataGenerator));
    }
}
