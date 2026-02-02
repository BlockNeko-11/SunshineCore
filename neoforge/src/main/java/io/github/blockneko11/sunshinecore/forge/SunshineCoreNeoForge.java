package io.github.blockneko11.sunshinecore.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.client.SunshineCoreClient;
import io.github.blockneko11.sunshinecore.command.forge.CommandRegistryImpl;
import io.github.blockneko11.sunshinecore.event.forge.ClientEventHandlers;
import io.github.blockneko11.sunshinecore.event.forge.EventHandlers;
import io.github.blockneko11.sunshinecore.item.forge.CreativeModeTabRegistryImpl;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SunshineCore.MOD_ID)
public final class SunshineCoreNeoForge {
    public SunshineCoreNeoForge(IEventBus bus, Dist dist) {
        SunshineCore.init();
        NeoForge.EVENT_BUS.register(EventHandlers.class);
        NeoForge.EVENT_BUS.register(CommandRegistryImpl.class);
        bus.register(CreativeModeTabRegistryImpl.class);

        if (dist.isClient()) {
            SunshineCoreClient.initClient();
            NeoForge.EVENT_BUS.register(ClientEventHandlers.class);
        }
    }
}
