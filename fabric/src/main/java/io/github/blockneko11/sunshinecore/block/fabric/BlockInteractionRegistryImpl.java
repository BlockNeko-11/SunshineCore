package io.github.blockneko11.sunshinecore.block.fabric;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Collection;

public final class BlockInteractionRegistryImpl {
    public static void registerFlammable(int flameAbility, int spreadSpeed, Collection<Block> blocks) {
        for (Block block : blocks) {
            FlammableBlockRegistry.getDefaultInstance().add(block, flameAbility, spreadSpeed);
        }
    }

    public static void registerFlammable(int burn, int spread, TagKey<Block> tag) {
        FlammableBlockRegistry.getDefaultInstance().add(tag, burn, spread);
    }

    public static void registerComposting(float chance, Collection<Item> items) {
        for (Item item : items) {
            CompostingChanceRegistry.INSTANCE.add(item, chance);
        }
    }

    public static void registerComposting(float chance, TagKey<Item> tag) {
        CompostingChanceRegistry.INSTANCE.add(tag, chance);
    }

    public static void registerFuel(int burnTick, Collection<Item> items) {
        for (Item item : items) {
            FuelRegistry.INSTANCE.add(item, burnTick);
        }
    }

    public static void registerFuel(int burnTick, TagKey<Item> tag) {
        FuelRegistry.INSTANCE.add(tag, burnTick);
    }

    private BlockInteractionRegistryImpl() {
    }
}
