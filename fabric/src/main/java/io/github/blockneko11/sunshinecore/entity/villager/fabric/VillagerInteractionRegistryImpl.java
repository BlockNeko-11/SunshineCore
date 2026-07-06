package io.github.blockneko11.sunshinecore.entity.villager.fabric;

import net.fabricmc.fabric.api.registry.VillagerInteractionRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collection;

public final class VillagerInteractionRegistryImpl {
    public static void registerWanted(Collection<Item> items) {
        for (Item item : items) {
            VillagerInteractionRegistries.registerCollectable(item);
        }
    }

    public static void registerCompostable(Collection<Item> items) {
        for (Item item : items) {
            VillagerInteractionRegistries.registerCompostable(item);
        }
    }

    public static void registerFood(int foodValue, Collection<Item> items) {
        for (Item item : items) {
            VillagerInteractionRegistries.registerFood(item, foodValue);
        }
    }

    public static void registerGiftLootTable(VillagerProfession profession, ResourceKey<LootTable> lootTable) {
        VillagerInteractionRegistries.registerGiftLootTable(profession, lootTable);
    }

    private VillagerInteractionRegistryImpl() {
    }
}
