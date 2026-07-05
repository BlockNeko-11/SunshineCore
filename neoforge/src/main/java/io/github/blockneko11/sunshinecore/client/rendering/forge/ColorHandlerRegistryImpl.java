package io.github.blockneko11.sunshinecore.client.rendering.forge;

import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public final class ColorHandlerRegistryImpl {
    private static final Map<ItemColor, List<Item>> ITEM_COLORS = new HashMap<>();
    private static final Map<BlockColor, List<Block>> BLOCK_COLORS = new HashMap<>();

    public static void registerItem(ItemColor color, Item... items) {
        ITEM_COLORS.put(color, Arrays.asList(items));
    }

    public static void registerBlock(BlockColor color, Block... blocks) {
        BLOCK_COLORS.put(color, Arrays.asList(blocks));
    }

    @SubscribeEvent
    public static void onRegisterItem(RegisterColorHandlersEvent.Item e) {
        ITEM_COLORS.forEach((color, items) -> {
            items.forEach(item -> e.register(color, item));
        });
    }

    @SubscribeEvent
    public static void onRegisterBlock(RegisterColorHandlersEvent.Block e) {
        BLOCK_COLORS.forEach((color, blocks) -> {
            blocks.forEach(block -> e.register(color, block));
        });
    }

    private ColorHandlerRegistryImpl() {
    }
}
