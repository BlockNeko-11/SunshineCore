package io.github.blockneko11.sunshinecore.item.fabric;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Collection;

@Deprecated
public final class FuelRegistryImpl {
    public static void register(int burnTick, Collection<Item> items) {
        items.forEach(i -> {
            if (burnTick < 0) {
                FuelRegistry.INSTANCE.remove(i);
            } else {
                FuelRegistry.INSTANCE.add(i, burnTick);
            }
        });
    }

    public static void register(int burnTick, TagKey<Item> tag) {
        FuelRegistry.INSTANCE.add(tag, burnTick);
    }
}
