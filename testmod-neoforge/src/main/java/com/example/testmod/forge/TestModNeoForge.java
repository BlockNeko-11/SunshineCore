package com.example.testmod.forge;

import com.example.testmod.TestMod;
import io.github.blockneko11.sunshinecore.data.forge.SDataGenerationImpl;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@Mod(TestMod.MOD_ID)
public final class TestModNeoForge {
    public TestModNeoForge(IEventBus bus) {
        TestMod.init();
        bus.addListener(this::onGatherData);
    }

    private void onGatherData(GatherDataEvent e) {
        TestMod.initDataGen(SDataGenerationImpl.create(e));
    }
}
