package io.github.blockneko11.sunshinecore.item.extension;

import net.minecraft.world.item.Item;

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
}
