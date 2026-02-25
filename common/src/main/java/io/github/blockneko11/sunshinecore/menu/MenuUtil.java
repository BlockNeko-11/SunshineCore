package io.github.blockneko11.sunshinecore.menu;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public final class MenuUtil {
    public static void openSimple(ServerPlayer player, MenuProvider provider) {
        player.openMenu(provider);
    }

    @ExpectPlatform
    public static void openExtended(ServerPlayer player, ExtendedMenuProvider provider) {
        throw new AssertionError();
    }

    public static <T extends AbstractContainerMenu> MenuType<T> simpleType(SimpleMenuTypeFactory<T> factory) {
        return new MenuType<>(factory::create, FeatureFlags.VANILLA_SET);
    }

    @ExpectPlatform
    public static <T extends AbstractContainerMenu> MenuType<T> extendedType(ExtendedMenuTypeFactory<T> factory) {
        throw new AssertionError();
    }

    private MenuUtil() {
    }

    @FunctionalInterface
    public interface SimpleMenuTypeFactory<T extends AbstractContainerMenu> {
        T create(int i, Inventory inventory);
    }

    @FunctionalInterface
    public interface ExtendedMenuTypeFactory<T extends AbstractContainerMenu> {
        T create(int i, Inventory inventory, FriendlyByteBuf buf);
    }
}
