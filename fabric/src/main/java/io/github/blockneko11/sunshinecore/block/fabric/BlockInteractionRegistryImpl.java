package io.github.blockneko11.sunshinecore.block.fabric;

import net.fabricmc.fabric.api.registry.CompostingChanceRegistry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Collection;

public final class BlockInteractionRegistryImpl {
    public static void registerFlammable(int flameAbility, int spreadSpeed, Collection<Block> blocks) {
        for (Block block : blocks) {
            if (flameAbility <= 0 || spreadSpeed <= 0) {
                FlammableBlockRegistry.getDefaultInstance().remove(block);
            } else {
                FlammableBlockRegistry.getDefaultInstance().add(block, flameAbility, spreadSpeed);
            }
        }
    }

    public static void registerComposting(float chance, Collection<Item> items) {
        for (Item item : items) {
            if (chance <= 0.0f || chance > 1.0f) {
                CompostingChanceRegistry.INSTANCE.remove(item);
            } else {
                CompostingChanceRegistry.INSTANCE.add(item, chance);
            }
        }
    }

    public static void registerFuel(int burnTick, Collection<Item> items) {
        for (Item item : items) {
            if (burnTick <= 0) {
                FuelRegistry.INSTANCE.remove(item);
            } else {
                FuelRegistry.INSTANCE.add(item, burnTick);
            }
        }
    }

    private BlockInteractionRegistryImpl() {
    }
}
