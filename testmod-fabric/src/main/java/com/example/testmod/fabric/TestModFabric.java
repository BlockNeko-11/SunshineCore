package com.example.testmod.fabric;

import com.example.testmod.TestMod;
import net.fabricmc.api.ModInitializer;

public final class TestModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        TestMod.init();
    }
}
