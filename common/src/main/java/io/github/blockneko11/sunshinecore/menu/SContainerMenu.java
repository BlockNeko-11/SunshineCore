package io.github.blockneko11.sunshinecore.menu;

import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public abstract class SContainerMenu extends AbstractContainerMenu {
    protected final Container container;

    protected SContainerMenu(@Nullable MenuType<?> menuType, int containerId, Inventory inventory, int containerSize) {
        this(menuType, containerId, inventory, new SimpleContainer(containerSize), containerSize);
    }

    protected SContainerMenu(@Nullable MenuType<?> menuType, int containerId, Inventory inventory, Container container, int containerSize) {
        super(menuType, containerId);
        checkContainerSize(container, containerSize);

        this.container = container;
        this.container.startOpen(inventory.player);

        this.addContainerSlots();
        this.addPlayerInventory(inventory);
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack newStack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack originalStack = slot.getItem();
            newStack = originalStack.copy();
            if (index < this.container.getContainerSize()) {
                if (!this.moveItemStackTo(originalStack, this.container.getContainerSize(), this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(originalStack, 0, this.container.getContainerSize(), false)) {
                return ItemStack.EMPTY;
            }

            if (originalStack.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return newStack;
    }

    @Override
    public boolean stillValid(Player player) {
        return this.container.stillValid(player);
    }

    public abstract void addContainerSlots();

    protected void addPlayerInventory(Inventory inventory) {
        int m, l;
        for (m = 0; m < 3; ++m) {
            for (l = 0; l < 9; ++l) {
                this.addSlot(new Slot(inventory, l + m * 9 + 9, 8 + l * 18, 84 + m * 18));
            }
        }

        for (m = 0; m < 9; ++m) {
            this.addSlot(new Slot(inventory, m, 8 + m * 18, 142));
        }
    }
}
