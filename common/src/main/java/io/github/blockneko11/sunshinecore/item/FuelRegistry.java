package io.github.blockneko11.sunshinecore.item;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

public final class FuelRegistry {
    public static void register(int burnTick, Item... items) {
        register(burnTick, Arrays.asList(items));
    }

    public static void register(int burnTicks, Stream<Item> items) {
        register(burnTicks, items.toList());
    }

    @ExpectPlatform
    public static void register(int burnTick, Collection<Item> items) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void register(int burnTick, TagKey<Item> tag) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static int get(ItemStack stack) {
        throw new AssertionError();
    }

    private FuelRegistry() {
    }
}
