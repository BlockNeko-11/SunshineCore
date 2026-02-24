package io.github.blockneko11.sunshinecore.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.block.forge.BlockInteractionRegistryImpl;
import io.github.blockneko11.sunshinecore.client.SunshineCoreClient;
import io.github.blockneko11.sunshinecore.command.forge.CommandRegistryImpl;
import io.github.blockneko11.sunshinecore.entity.villager.forge.VillagerInteractionRegistryImpl;
import io.github.blockneko11.sunshinecore.event.SetupEvent;
import io.github.blockneko11.sunshinecore.event.forge.ClientEventHandlers;
import io.github.blockneko11.sunshinecore.event.forge.EventHandlers;
import io.github.blockneko11.sunshinecore.item.forge.CreativeModeTabRegistryImpl;
//import io.github.blockneko11.sunshinecore.item.forge.FuelRegistryImpl;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SunshineCore.MOD_ID)
public final class SunshineCoreNeoForge {
    public SunshineCoreNeoForge(IEventBus bus, Dist dist) {
        SunshineCore.init();
        NeoForge.EVENT_BUS.register(EventHandlers.class);
        bus.addListener(this::onCommonSetup);
        NeoForge.EVENT_BUS.register(CommandRegistryImpl.class);
        bus.register(CreativeModeTabRegistryImpl.class);
//        NeoForge.EVENT_BUS.register(FuelRegistryImpl.class);
        NeoForge.EVENT_BUS.register(BlockInteractionRegistryImpl.class);
        NeoForge.EVENT_BUS.register(VillagerInteractionRegistryImpl.class);

        if (dist.isClient()) {
            SunshineCoreClient.initClient();
            NeoForge.EVENT_BUS.register(ClientEventHandlers.class);
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent e) {
        SunshineCore.BUS.post(new SetupEvent());
    }
}
