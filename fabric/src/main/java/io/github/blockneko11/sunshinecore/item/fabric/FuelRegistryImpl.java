package io.github.blockneko11.sunshinecore.item.fabric;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Collection;

public final class FuelRegistryImpl {
    public static void register(int burnTick, Collection<Item> items) {
        for (Item item : items) {
            FuelRegistry.INSTANCE.add(item, burnTick);
        }
    }

    public static void register(int burnTick, TagKey<Item> tag) {
        FuelRegistry.INSTANCE.add(tag, burnTick);
    }

    private FuelRegistryImpl() {
    }
}
