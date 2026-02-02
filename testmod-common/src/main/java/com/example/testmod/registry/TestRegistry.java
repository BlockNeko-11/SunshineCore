package com.example.testmod.registry;

import com.example.testmod.TestMod;
import com.example.testmod.mixin.CreativeModeTabsAccessor;
import io.github.blockneko11.sunshinecore.item.CreativeModeTabRegistry;
import io.github.blockneko11.sunshinecore.registry.CreativeModeTabSupplier;
import io.github.blockneko11.sunshinecore.registry.Registrar;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public final class TestRegistry {
    public static final Registrar REGISTRAR = Registrar.create(TestMod.MOD_ID);
    public static final Supplier<Item> TEST_ITEM = REGISTRAR.simpleItem("test_item");

    public static final CreativeModeTabSupplier TEST_TAB = REGISTRAR.registerTab(
            "test_tab", () -> CreativeModeTabRegistry.create(
                    Component.literal("Test Tab"), () -> new ItemStack(TEST_ITEM.get())));

    public static void init() {
        REGISTRAR.register();

        CreativeModeTabRegistry.modify(CreativeModeTabsAccessor.getBuildingBlocks(),
                (output, isOP) -> output.accept(TEST_ITEM.get()));

        CreativeModeTabRegistry.modify(TEST_TAB.getKey(),
                (output, isOP) -> output.accept(TEST_ITEM.get()));
    }
}
