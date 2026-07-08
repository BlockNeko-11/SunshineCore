package io.github.blockneko11.sunshinecore.item.extension;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface ItemExtension {
    default Item sc$self() {
        return (Item) this;
    }

    default int sc$getBurnTick() {
        return 0;
    }

    default float sc$getCompostingChance() {
        return 0.0f;
    }

    default void sc$armorTick(ItemStack stack, Player player) {
    }
}
