package io.github.blockneko11.sunshinecore.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.client.SunshineCoreClient;
import io.github.blockneko11.sunshinecore.client.menu.forge.MenuRegistryImpl;
import io.github.blockneko11.sunshinecore.command.forge.CommandRegistryImpl;
import io.github.blockneko11.sunshinecore.entity.villager.forge.VillagerInteractionRegistryImpl;
import io.github.blockneko11.sunshinecore.event.initialize.SetupEvent;
import io.github.blockneko11.sunshinecore.item.tab.forge.CreativeModeTabUtilImpl;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;

@Mod(SunshineCore.MOD_ID)
public final class SunshineCoreNeoForge {
    public SunshineCoreNeoForge(IEventBus bus, Dist dist) {
        SunshineCore.init();
        bus.addListener(this::onCommonSetup);
        NeoForge.EVENT_BUS.register(CommandRegistryImpl.class);
        bus.register(CreativeModeTabUtilImpl.class);
        NeoForge.EVENT_BUS.register(VillagerInteractionRegistryImpl.class);

        if (dist.isClient()) {
            SunshineCoreClient.initClient();
            bus.register(MenuRegistryImpl.class);
        }
    }

    private void onCommonSetup(FMLCommonSetupEvent e) {
        SetupEvent.EVENT.invoker().onSetup();
    }
}
