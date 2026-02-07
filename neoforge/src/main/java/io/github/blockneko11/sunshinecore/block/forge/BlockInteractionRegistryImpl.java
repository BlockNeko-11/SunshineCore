package io.github.blockneko11.sunshinecore.block.forge;

import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.furnace.FurnaceFuelBurnTimeEvent;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class BlockInteractionRegistryImpl {
    public static final Map<Block, FlammableEntry> FLAMMABLES = new HashMap<>();
    public static final Map<Item, Float> COMPOSTABLES = new HashMap<>();
    private static final Map<Item, Integer> FUELS = new HashMap<>();

    public static void registerFlammable(int flameAbility, int spreadSpeed, Collection<Block> blocks) {
        for (Block block : blocks) {
            if (flameAbility <= 0 || spreadSpeed <= 0) {
                FLAMMABLES.remove(block);
            } else {
                FLAMMABLES.put(block, new FlammableEntry(flameAbility, spreadSpeed));
            }
        }
    }

    public static void registerComposting(float chance, Collection<Item> items) {
        for (Item item : items) {
            if (chance <= 0.0f || chance > 1.0f) {
                COMPOSTABLES.remove(item);
            } else {
                COMPOSTABLES.put(item, chance);
            }
        }
    }

    public static void registerFuel(int burnTick, Collection<Item> items) {
        for (Item i : items) {
            if (burnTick <= 0) {
                FUELS.remove(i);
            } else {
                FUELS.put(i, burnTick);
            }
        }
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

    public static final class FlammableEntry {
        private final int flameAbility;
        private final int spreadSpeed;

        private FlammableEntry(int flameAbility, int spreadSpeed) {
            this.flameAbility = flameAbility;
            this.spreadSpeed = spreadSpeed;
        }

        public int getFlameAbility() {
            return this.flameAbility;
        }

        public int getSpreadSpeed() {
            return this.spreadSpeed;
        }
    }
}
