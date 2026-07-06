package io.github.blockneko11.sunshinecore.entity.villager.forge;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootTable;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;

public final class VillagerInteractionRegistryImpl {
    @ApiStatus.Internal
    public static final Set<Item> WANTED_ITEMS = new HashSet<>();

    @ApiStatus.Internal
    public static final List<Item> COMPOSTABLE_ITEMS = new ArrayList<>();

    @ApiStatus.Internal
    public static final Map<Item, Integer> FOODS = new HashMap<>();

    @ApiStatus.Internal
    public static final Map<VillagerProfession, ResourceKey<LootTable>> GIFT_LOOT_TABLES = new HashMap<>();

    public static void registerWanted(Collection<Item> items) {
        WANTED_ITEMS.addAll(items);
    }

    public static void registerCompostable(Collection<Item> items) {
        COMPOSTABLE_ITEMS.addAll(items);
    }

    public static void registerFood(int foodValue, Collection<Item> items) {
        for (Item item : items) {
            FOODS.put(item, foodValue);
        }
    }

    public static void registerGiftLootTable(VillagerProfession profession, ResourceKey<LootTable> lootTable) {
        GIFT_LOOT_TABLES.put(profession, lootTable);
    }

    private VillagerInteractionRegistryImpl() {
    }
}
