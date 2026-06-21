package io.github.blockneko11.sunshinecore.client.rendering.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

@Environment(EnvType.CLIENT)
public final class ColorHandlerRegistryImpl {
    public static void registerItem(ItemColor color, Item... items) {
        ColorProviderRegistry.ITEM.register(color, items);
    }

    public static void registerBlock(BlockColor color, Block... blocks) {
        ColorProviderRegistry.BLOCK.register(color, blocks);
    }

    private ColorHandlerRegistryImpl() {
    }
}
