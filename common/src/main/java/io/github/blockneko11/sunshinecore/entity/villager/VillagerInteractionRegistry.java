package io.github.blockneko11.sunshinecore.entity.villager;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collection;
import java.util.List;

public final class VillagerInteractionRegistry {
    public static void registerWanted(Item... items) {
        registerWanted(List.of(items));
    }

    @ExpectPlatform
    public static void registerWanted(Collection<Item> items) {
        throw new AssertionError();
    }

    public static void registerCompostable(Item... items) {
        registerCompostable(List.of(items));
    }

    @ExpectPlatform
    public static void registerCompostable(Collection<Item> items) {
        throw new AssertionError();
    }

    public static void registerFood(int foodValue, Item... items) {
        registerFood(foodValue, List.of(items));
    }

    @ExpectPlatform
    public static void registerFood(int foodValue, Collection<Item> items) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerGiftLootTable(VillagerProfession profession, ResourceKey<LootTable> lootTable) {
        throw new AssertionError();
    }

    private VillagerInteractionRegistry() {
    }
}
