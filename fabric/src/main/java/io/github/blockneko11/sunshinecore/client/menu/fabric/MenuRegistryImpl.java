package io.github.blockneko11.sunshinecore.client.menu.fabric;

import io.github.blockneko11.sunshinecore.client.menu.MenuRegistry;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public final class MenuRegistryImpl {
    public static <M extends AbstractContainerMenu, S extends Screen & MenuAccess<M>> void register(MenuType<M> type, MenuRegistry.ScreenFactory<M, S> factory) {
        MenuScreens.register(type, factory::create);
    }
}
