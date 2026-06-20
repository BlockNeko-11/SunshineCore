package io.github.blockneko11.sunshinecore.menu.forge;

import io.github.blockneko11.sunshinecore.menu.ExtendedMenuProvider;
import io.github.blockneko11.sunshinecore.menu.MenuUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;

public final class MenuUtilImpl {
    public static void openExtended(ServerPlayer player, ExtendedMenuProvider provider) {
        player.openMenu(provider, provider::saveExtraData);
    }

    public static <T extends AbstractContainerMenu> MenuType<T> extendedType(MenuUtil.ExtendedMenuTypeFactory<T> factory) {
        return IMenuTypeExtension.create(factory::create);
    }

    private MenuUtilImpl() {
    }
}
