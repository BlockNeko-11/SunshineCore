package io.github.blockneko11.sunshinecore.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.block.forge.FlammableRegistryImpl;
import io.github.blockneko11.sunshinecore.client.SunshineCoreClient;
import io.github.blockneko11.sunshinecore.client.entity.forge.EntityModelLayerRegistryImpl;
import io.github.blockneko11.sunshinecore.client.entity.forge.EntityRendererRegistryImpl;
import io.github.blockneko11.sunshinecore.client.input.forge.KeyMappingRegistryImpl;
import io.github.blockneko11.sunshinecore.client.menu.forge.MenuRegistryImpl;
import io.github.blockneko11.sunshinecore.client.rendering.forge.ColorHandlerRegistryImpl;
import io.github.blockneko11.sunshinecore.entity.mob.forge.SpawnPlacementsRegistryImpl;
import io.github.blockneko11.sunshinecore.entity.villager.forge.VillagerTradeRegistryImpl;
import io.github.blockneko11.sunshinecore.client.event.forge.ClientForgeEventHandlers;
import io.github.blockneko11.sunshinecore.event.forge.ForgeEventHandlers;
import io.github.blockneko11.sunshinecore.event.initialize.SetupEvent;
import io.github.blockneko11.sunshinecore.item.alchemy.forge.PotionBrewingRegistryImpl;
import io.github.blockneko11.sunshinecore.item.forge.CompostingRegistryImpl;
import io.github.blockneko11.sunshinecore.item.forge.FuelRegistryImpl;
import io.github.blockneko11.sunshinecore.item.tab.forge.CreativeModeTabUtilsImpl;
import io.github.blockneko11.sunshinecore.util.forge.EventBusUtils;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.event.TagsUpdatedEvent;

@Mod(SunshineCore.MOD_ID)
public final class SunshineCoreNeoForge {
    public SunshineCoreNeoForge(IEventBus bus, Dist dist) {
        SunshineCore.init();
        this.registerFMLBusHandlers();
        this.registerModBusHandlers(bus);

        if (dist.isClient()) {
            SunshineCoreClient.initClient();
            this.registerClientFMLBusHandlers();
            this.registerClientModBusHandlers(bus);
        }
    }

    private void registerFMLBusHandlers() {
        EventBusUtils.FML().register(ForgeEventHandlers.class);

        EventBusUtils.FML().<TagsUpdatedEvent>addListener(e -> {
            FlammableRegistryImpl.onUpdateTags();
            CompostingRegistryImpl.onUpdateTags();
            FuelRegistryImpl.onUpdateTags();
        });

        EventBusUtils.FML().register(PotionBrewingRegistryImpl.class);
        EventBusUtils.FML().register(VillagerTradeRegistryImpl.class);
    }

    private void registerModBusHandlers(IEventBus bus) {
        bus.<FMLCommonSetupEvent>addListener(e -> {
            SetupEvent.EVENT.invoker().onSetup();
        });

        bus.register(SpawnPlacementsRegistryImpl.class);
        bus.register(CreativeModeTabUtilsImpl.class);
    }

    @OnlyIn(Dist.CLIENT)
    private void registerClientFMLBusHandlers() {
        EventBusUtils.FML().register(ClientForgeEventHandlers.class);
    }

    @OnlyIn(Dist.CLIENT)
    private void registerClientModBusHandlers(IEventBus bus) {
        bus.register(EntityModelLayerRegistryImpl.class);
        bus.register(EntityRendererRegistryImpl.class);
        bus.register(KeyMappingRegistryImpl.class);
        bus.register(MenuRegistryImpl.class);
        bus.register(ColorHandlerRegistryImpl.class);
    }
}
