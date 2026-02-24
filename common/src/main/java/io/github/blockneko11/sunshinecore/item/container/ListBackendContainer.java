package io.github.blockneko11.sunshinecore.item.container;

import net.minecraft.core.NonNullList;
import net.minecraft.world.Container;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

@FunctionalInterface
public interface ListBackendContainer extends Container {
    NonNullList<ItemStack> getList();

    @Override
    default int getContainerSize() {
        return getList().size();
    }

    @Override
    default boolean isEmpty() {
        for (int i = 0; i < getContainerSize(); i++) {
            ItemStack stack = getItem(i);
            if (!stack.isEmpty()) {
                return false;
            }
        }

        return true;
    }

    @Override
    default ItemStack getItem(int slot) {
        return getList().get(slot);
    }

    @Override
    default ItemStack removeItem(int slot, int amount) {
        ItemStack result = ContainerHelper.removeItem(getList(), slot, amount);
        if (!result.isEmpty()) {
            setChanged();
        }

        return result;
    }

    @Override
    default ItemStack removeItemNoUpdate(int slot) {
        return ContainerHelper.takeItem(getList(), slot);
    }

    @Override
    default void setItem(int slot, ItemStack stack) {
        getList().set(slot, stack);
        if (stack.getCount() > getMaxStackSize()) {
            stack.setCount(getMaxStackSize());
        }
    }

    @Override
    default void setChanged() {
    }

    @Override
    default boolean stillValid(Player player) {
        return true;
    }

    @Override
    default void clearContent() {
        getList().clear();
    }
}
