package io.github.blockneko11.sunshinecore.item.forge;

import io.github.blockneko11.sunshinecore.registry.RegistryUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class FuelRegistryImpl {
    private static final Map<Item, Integer> FUELS = new HashMap<>();
    private static final Map<TagKey<Item>, Integer> FUEL_TAGS = new HashMap<>();
    private static Map<Item, Integer> COMPUTED_FUELS = null;

    public static void register(int burnTick, Collection<Item> items) {
        COMPUTED_FUELS = null;
        for (Item i : items) {
            FUELS.put(i, burnTick);
        }
    }

    public static void register(int burnTick, TagKey<Item> tag) {
        COMPUTED_FUELS = null;
        FUEL_TAGS.put(tag, burnTick);
    }

    private static Map<Item, Integer> getFuels() {
        if (COMPUTED_FUELS != null) {
            return COMPUTED_FUELS;
        }

        Map<Item, Integer> computed = new HashMap<>();
        for (Map.Entry<TagKey<Item>, Integer> entry : FUEL_TAGS.entrySet()) {
            for (Item item : RegistryUtils.getTagEntries(BuiltInRegistries.ITEM, entry.getKey())) {
                computed.put(item, entry.getValue());
            }
        }

        computed.putAll(FUELS);
        COMPUTED_FUELS = computed;
        return COMPUTED_FUELS;
    }

    @ApiStatus.Internal
    public static void onUpdateTags() {
        COMPUTED_FUELS = null;
    }

    @SubscribeEvent
    public static void onEvent(FurnaceFuelBurnTimeEvent e) {
        Item item = e.getItemStack().getItem();

        if (item.sc$getBurnTick() > 0) {
            e.setBurnTime(item.sc$getBurnTick());
            return;
        }

        if (!getFuels().containsKey(item)) {
            return;
        }

        e.setBurnTime(getFuels().get(item));
    }

    private FuelRegistryImpl() {
    }
}
