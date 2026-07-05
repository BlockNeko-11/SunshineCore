package io.github.blockneko11.sunshinecore.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

@FunctionalInterface
public interface ExtendedMenuTypeFactory<T extends AbstractContainerMenu> {
    T create(int i, Inventory inventory, FriendlyByteBuf buf);
}
