package io.github.blockneko11.sunshinecore.menu;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;

public final class MenuUtils {
    public static void openSimple(ServerPlayer player, MenuProvider provider) {
        player.openMenu(provider);
    }

    @ExpectPlatform
    public static void openExtended(ServerPlayer player, ExtendedMenuProvider provider) {
        throw new AssertionError();
    }

    public static <T extends AbstractContainerMenu> MenuType<T> simpleType(MenuType.MenuSupplier<T> supplier) {
        return new MenuType<>(supplier, FeatureFlags.VANILLA_SET);
    }

    @ExpectPlatform
    public static <T extends AbstractContainerMenu> MenuType<T> extendedType(ExtendedMenuTypeFactory<T> factory) {
        throw new AssertionError();
    }

    private MenuUtils() {
    }
}
