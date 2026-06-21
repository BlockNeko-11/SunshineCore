package io.github.blockneko11.sunshinecore.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.client.SunshineCoreClient;
import io.github.blockneko11.sunshinecore.event.initialize.SetupEvent;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(SunshineCore.MOD_ID)
public final class SunshineCoreNeoForge {
    public SunshineCoreNeoForge(IEventBus bus, Dist dist) {
        SunshineCore.init();
        bus.addListener(this::onCommonSetup);

        if (dist.isClient()) {
            SunshineCoreClient.initClient();
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent e) {
        SetupEvent.EVENT.invoker().onSetup();
    }
}
