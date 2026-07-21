package com.example.testmod.registry;

import com.example.testmod.TestMod;
import com.example.testmod.item.TestCompostableItem;
import io.github.blockneko11.sunshinecore.item.tab.CreativeModeTabUtils;
import io.github.blockneko11.sunshinecore.registry.Registrar;
import io.github.blockneko11.sunshinecore.registry.holder.BlockHolder;
import io.github.blockneko11.sunshinecore.registry.holder.ItemHolder;
import io.github.blockneko11.sunshinecore.registry.holder.RegistryHolder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

// CAUTION: do not call Supplier#get() before registration finished
public final class TestRegistry {
    // 1. create the Registrar instance
    private static final Registrar REGISTRAR = Registrar.create(TestMod.MOD_ID);

    // 2. register blocks and items
    // register blocks first, then items (includes BlockItems), and then other registries

    public static final BlockHolder<Block> TEST_BLOCK = REGISTRAR.simpleBlock("test_block");
    public static final BlockHolder<Block> TEST_BLOCK_FLATTENED = REGISTRAR.simpleBlock("test_block_flattened");
    public static final BlockHolder<Block> TEST_BLOCK_FLAMMABLE = REGISTRAR.simpleBlock("test_block_flammable", BlockBehaviour.Properties.of().sc$flammable(5, 5));
    public static final TagKey<Block> TEST_BLOCKS = REGISTRAR.tag(BuiltInRegistries.BLOCK, "test_blocks");

    // register BlockItems via #blockItem(String, Supplier, Item.Properties)
    public static final ItemHolder<BlockItem> TEST_BLOCK_ITEM = REGISTRAR.blockItem("test_block", TEST_BLOCK, new Item.Properties().sc$fuel(50).sc$compostable(0.4f));

    public static final RegistryHolder<CreativeModeTab, CreativeModeTab> TEST_TAB = REGISTRAR.register(
            BuiltInRegistries.CREATIVE_MODE_TAB, "test_tab", () -> CreativeModeTabUtils.create(
                    Component.literal("Test Tab"), () -> new ItemStack(TEST_BLOCK_ITEM.get())));

    // register BlockItems via Registrar#register(Registry, String, Supplier)
    public static final ItemHolder<BlockItem> TEST_BLOCK_FLATTENED_ITEM = REGISTRAR.simpleBlockItem("test_block_flattened", TEST_BLOCK_FLATTENED);

    public static final ItemHolder<BlockItem> TEST_BLOCK_FLAMMABLE_ITEM = REGISTRAR.blockItem("test_block_flammable", TEST_BLOCK_FLAMMABLE, new Item.Properties().sc$tab(TEST_TAB.key()));

    public static final ItemHolder<Item> TEST_COMPOSTABLE_ITEM = REGISTRAR.item("test_compostable_item", TestCompostableItem::new);
    public static final TagKey<Item> TEST_BLOCK_ITEMS = REGISTRAR.tag(BuiltInRegistries.ITEM, "test_block_items");

    public static void init() {
        REGISTRAR.register();

        CreativeModeTabUtils.modify(TEST_TAB, (output, isOP) -> {
            output.accept(TEST_BLOCK_ITEM);
            output.accept(TEST_BLOCK_FLATTENED_ITEM);
            output.accept(TEST_COMPOSTABLE_ITEM);
//            output.accept(TEST_BLOCK_FLAMMABLE_ITEM);
        });
    }
}
