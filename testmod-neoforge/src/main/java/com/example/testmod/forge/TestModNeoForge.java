package com.example.testmod.forge;

import com.example.testmod.TestMod;
import io.github.blockneko11.sunshinecore.util.forge.ModEventBuses;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;

@Mod(TestMod.MOD_ID)
public final class TestModNeoForge {
    public TestModNeoForge(IEventBus bus) {
        ModEventBuses.register(TestMod.MOD_ID, bus);

//        bus.addListener(this::onGatherData);
        TestMod.init();
    }

//    private void onGatherData(GatherDataEvent e) {
//        TestModDataGeneration.initDataGen(new NekoDataGenerationForge(e));
//    }
}
