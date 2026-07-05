package io.github.blockneko11.sunshinecore.item;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Arrays;
import java.util.Collection;

public final class CompostingRegistry {
    public static void register(float chance, Item... items) {
        register(chance, Arrays.asList(items));
    }

    @ExpectPlatform
    public static void register(float chance, Collection<Item> items) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void register(float chance, TagKey<Item> tag) {
        throw new AssertionError();
    }

    private CompostingRegistry() {
    }
}
