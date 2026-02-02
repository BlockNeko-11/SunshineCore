package io.github.blockneko11.sunshinecore.item.forge;

import io.github.blockneko11.sunshinecore.util.StreamUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class FuelRegistryImpl {
    private static final Map<Item, Integer> FUELS = new HashMap<>();

    public static void register(int burnTick, Collection<Item> items) {
        for (Item i : items) {
            if (burnTick < 0) {
                FUELS.remove(i);
            } else {
                FUELS.put(i, burnTick);
            }
        }
    }

    public static void register(int burnTick, TagKey<Item> tag) {
        register(burnTick,
                StreamUtil.toStream(BuiltInRegistries.ITEM.getTagOrEmpty(tag))
                .map(Holder::value)
                .toList());
    }

    public static int get(ItemStack stack) {
        return stack.getBurnTime(null);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onFuelRegister(FurnaceFuelBurnTimeEvent e) {
        if (e.getItemStack().isEmpty()) {
            return;
        }

        int ticks = FUELS.getOrDefault(e.getItemStack().getItem(), Integer.MIN_VALUE);
        if (ticks != Integer.MIN_VALUE) {
            e.setBurnTime(ticks);
        }
    }
}
