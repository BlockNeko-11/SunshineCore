package io.github.blockneko11.sunshinecore.client.menu.forge;

import io.github.blockneko11.sunshinecore.client.menu.MenuRegistry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;

import java.util.ArrayList;
import java.util.List;

public final class MenuRegistryImpl {
    private static final List<MenuEntry<?, ?>> SCREEN_FACTORIES = new ArrayList<>();

    public static <M extends AbstractContainerMenu, S extends Screen & MenuAccess<M>> void register(MenuType<M> type, MenuRegistry.ScreenFactory<M, S> factory) {
        SCREEN_FACTORIES.add(new MenuEntry<>(type, factory));
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onRegisterScreens(RegisterMenuScreensEvent e) {
        SCREEN_FACTORIES.forEach(entry -> entry.register(e));
    }

    private record MenuEntry<M extends AbstractContainerMenu, S extends Screen & MenuAccess<M>>(MenuType<M> type, MenuRegistry.ScreenFactory<M, S> factory) {
        private void register(RegisterMenuScreensEvent e) {
            e.register(this.type, this.factory::create);
        }
    }

    private MenuRegistryImpl() {
    }
}
