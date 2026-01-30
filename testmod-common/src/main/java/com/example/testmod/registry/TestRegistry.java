package com.example.testmod.registry;

import com.example.testmod.TestMod;
import io.github.blockneko11.sunshinecore.registry.Registrar;
import net.minecraft.world.item.Item;

import java.util.function.Supplier;

public final class TestRegistry {
    public static final Registrar REGISTRAR = Registrar.create(TestMod.MOD_ID);
    public static final Supplier<Item> TEST_ITEM = REGISTRAR.simpleItem("test_item");

    public static void init() {
        REGISTRAR.register();
    }
}
