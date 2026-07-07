package com.example.testmod.item;

import net.minecraft.world.item.Item;

public class TestCompostableItem extends Item {
    public TestCompostableItem() {
        super(new Properties());
    }

    @Override
    public float sc$getCompostingChance() {
        return 0.8f;
    }
}
