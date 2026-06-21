package io.github.blockneko11.sunshinecore.client.rendering;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public final class ColorHandlerRegistry {
    @ExpectPlatform
    public static void registerItem(ItemColor color, Item... items) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerBlock(BlockColor color, Block... blocks) {
        throw new AssertionError();
    }

    private ColorHandlerRegistry() {
    }
}
