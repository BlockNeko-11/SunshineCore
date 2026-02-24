package com.example.testmod.registry;

import com.example.testmod.TestMod;
import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.block.BlockInteractionRegistry;
import io.github.blockneko11.sunshinecore.event.SetupEvent;
import io.github.blockneko11.sunshinecore.item.CreativeModeTabRegistry;
import io.github.blockneko11.sunshinecore.item.ItemInteractionRegistry;
import io.github.blockneko11.sunshinecore.registry.Registrar;
import io.github.blockneko11.sunshinecore.registry.RegistryHolder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;

public final class TestRegistry {
    public static final Registrar REGISTRAR = Registrar.create(TestMod.MOD_ID);

    public static final Supplier<Block> TEST_BLOCK = REGISTRAR.simpleBlock("test_block");
    public static final Supplier<Block> TEST_BLOCK_FLATTENED = REGISTRAR.simpleBlock("test_block_flattened");
    public static final TagKey<Block> TEST_BLOCKS = REGISTRAR.tag(BuiltInRegistries.BLOCK, "test_blocks");

    public static final Supplier<Item> TEST_BLOCK_ITEM = REGISTRAR.blockItem("test_block", TEST_BLOCK);
    public static final Supplier<Item> TEST_BLOCK_FLATTENED_ITEM = REGISTRAR.blockItem("test_block_flattened", TEST_BLOCK_FLATTENED);
    public static final TagKey<Item> TEST_BLOCK_ITEMS = REGISTRAR.tag(BuiltInRegistries.ITEM, "test_block_items");

    public static final RegistryHolder<CreativeModeTab, CreativeModeTab> TEST_TAB = REGISTRAR.register(
            BuiltInRegistries.CREATIVE_MODE_TAB, "test_tab", () -> CreativeModeTabRegistry.create(
                    Component.literal("Test Tab"), () -> new ItemStack(TEST_BLOCK_ITEM.get())));

    public static void init() {
        REGISTRAR.register();

        CreativeModeTabRegistry.modify(TEST_TAB.getKey(), (output, isOP) -> {
            output.accept(TEST_BLOCK_ITEM.get());
            output.accept(TEST_BLOCK_FLATTENED_ITEM.get());
        });

        SunshineCore.BUS.register(SetupEvent.class, e -> {
            ItemInteractionRegistry.registerFlattenable(TEST_BLOCK.get(), TEST_BLOCK_FLATTENED.get().defaultBlockState());
            ItemInteractionRegistry.registerTillable(TEST_BLOCK.get(),
                    HoeItem::onlyIfAirAbove,
                    ctx -> Block.popResourceFromFace(ctx.getLevel(), ctx.getClickedPos(), ctx.getClickedFace(), new ItemStack(Items.REDSTONE)));

            BlockInteractionRegistry.registerFuel(50, TEST_BLOCK_ITEM.get());
            BlockInteractionRegistry.registerComposting(0.4f, TEST_BLOCK_ITEM.get());
        });
    }
}
