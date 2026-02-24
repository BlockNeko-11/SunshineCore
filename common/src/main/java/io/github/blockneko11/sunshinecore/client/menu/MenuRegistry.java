package io.github.blockneko11.sunshinecore.client.menu;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

@Environment(EnvType.CLIENT)
public final class MenuRegistry {
    @ExpectPlatform
    public static <M extends AbstractContainerMenu, S extends Screen & MenuAccess<M>> void register(MenuType<M> type, ScreenFactory<M, S> factory) {
        throw new AssertionError();
    }

    private MenuRegistry() {
    }

    @FunctionalInterface
    public interface ScreenFactory<M extends AbstractContainerMenu, S extends Screen & MenuAccess<M>> {
        S create(M menu, Inventory inventory, Component component);
    }
}
