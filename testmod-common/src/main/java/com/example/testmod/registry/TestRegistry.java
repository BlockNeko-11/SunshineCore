package com.example.testmod.registry;

import com.example.testmod.TestMod;
import com.example.testmod.item.TestCompostableItem;
import io.github.blockneko11.sunshinecore.block.FlammableRegistry;
import io.github.blockneko11.sunshinecore.event.initialize.SetupEvent;
import io.github.blockneko11.sunshinecore.item.CompostingRegistry;
import io.github.blockneko11.sunshinecore.item.FuelRegistry;
import io.github.blockneko11.sunshinecore.item.tab.CreativeModeTabUtils;
import io.github.blockneko11.sunshinecore.item.tool.ToolInteractionRegistry;
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
    public static final Supplier<Block> TEST_BLOCK_FLAMMABLE = REGISTRAR.simpleBlock("test_block_flammable", p -> p.sc$flammable(5, 5));
    public static final TagKey<Block> TEST_BLOCKS = REGISTRAR.tag(BuiltInRegistries.BLOCK, "test_blocks");

    public static final Supplier<Item> TEST_BLOCK_ITEM = REGISTRAR.blockItem("test_block", TEST_BLOCK, p -> p.sc$fuel(50).sc$compostable(0.4f));

    public static final RegistryHolder<CreativeModeTab, CreativeModeTab> TEST_TAB = REGISTRAR.register(
            BuiltInRegistries.CREATIVE_MODE_TAB, "test_tab", () -> CreativeModeTabUtils.create(
                    Component.literal("Test Tab"), () -> new ItemStack(TEST_BLOCK_ITEM.get())));

    public static final Supplier<Item> TEST_BLOCK_FLATTENED_ITEM = REGISTRAR.blockItem("test_block_flattened", TEST_BLOCK_FLATTENED);
    public static final Supplier<Item> TEST_BLOCK_FLAMMABLE_ITEM = REGISTRAR.blockItem("test_block_flammable", TEST_BLOCK_FLAMMABLE, p -> p.sc$tab(TEST_TAB.key()));
    public static final Supplier<Item> TEST_COMPOSTABLE_ITEM = REGISTRAR.item("test_compostable_item", TestCompostableItem::new);
    public static final TagKey<Item> TEST_BLOCK_ITEMS = REGISTRAR.tag(BuiltInRegistries.ITEM, "test_block_items");

    public static void init() {
        REGISTRAR.register();

        CreativeModeTabUtils.modify(TEST_TAB.key(), (output, isOP) -> {
            output.accept(TEST_BLOCK_ITEM.get());
            output.accept(TEST_BLOCK_FLATTENED_ITEM.get());
            output.accept(TEST_COMPOSTABLE_ITEM.get());
//            output.accept(TEST_BLOCK_FLAMMABLE_ITEM.get());
        });
    }
}
