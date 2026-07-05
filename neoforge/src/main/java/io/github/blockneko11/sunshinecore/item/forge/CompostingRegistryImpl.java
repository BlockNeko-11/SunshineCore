package io.github.blockneko11.sunshinecore.item.forge;

import io.github.blockneko11.sunshinecore.registry.RegistryUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class CompostingRegistryImpl {
    private static final Map<Item, Float> COMPOSTABLES = new HashMap<>();
    private static final Map<TagKey<Item>, Float> COMPOSTABLE_TAGS = new HashMap<>();
    private static Map<Item, Float> COMPUTED_COMPOSTABLES = null;

    public static void register(float chance, Collection<Item> items) {
        COMPUTED_COMPOSTABLES = null;
        for (Item item : items) {
            COMPOSTABLES.put(item, chance);
        }
    }

    public static void register(float chance, TagKey<Item> tag) {
        COMPUTED_COMPOSTABLES = null;
        COMPOSTABLE_TAGS.put(tag, chance);
    }

    @ApiStatus.Internal
    public static Map<Item, Float> getCompostables() {
        if (COMPUTED_COMPOSTABLES != null) {
            return COMPUTED_COMPOSTABLES;
        }

        Map<Item, Float> computed = new HashMap<>();
        for (Map.Entry<TagKey<Item>, Float> entry : COMPOSTABLE_TAGS.entrySet()) {
            for (Item item : RegistryUtils.getEntries(BuiltInRegistries.ITEM, entry.getKey())) {
                computed.put(item, entry.getValue());
            }
        }

        computed.putAll(COMPOSTABLES);
        COMPUTED_COMPOSTABLES = computed;
        return COMPUTED_COMPOSTABLES;
    }

    @ApiStatus.Internal
    public static void onUpdateTags() {
        COMPUTED_COMPOSTABLES = null;
    }

    private CompostingRegistryImpl() {
    }
}
