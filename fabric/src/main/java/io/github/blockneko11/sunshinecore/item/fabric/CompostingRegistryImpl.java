package io.github.blockneko11.sunshinecore.item.fabric;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Collection;

public final class CompostingRegistryImpl {
    public static void register(float chance, Collection<Item> items) {
        for (Item item : items) {
            CompostingChanceRegistry.INSTANCE.add(item, chance);
        }
    }

    public static void register(float chance, TagKey<Item> tag) {
        CompostingChanceRegistry.INSTANCE.add(tag, chance);
    }

    private CompostingRegistryImpl() {
    }
}
