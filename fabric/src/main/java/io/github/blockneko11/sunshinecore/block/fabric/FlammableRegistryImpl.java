package io.github.blockneko11.sunshinecore.block.fabric;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Collection;

public final class FlammableRegistryImpl {
    public static void register(int flameAbility, int spreadSpeed, Collection<Block> blocks) {
        for (Block block : blocks) {
            FlammableBlockRegistry.getDefaultInstance().add(block, flameAbility, spreadSpeed);
        }
    }

    public static void register(int burn, int spread, TagKey<Block> tag) {
        FlammableBlockRegistry.getDefaultInstance().add(tag, burn, spread);
    }

    private FlammableRegistryImpl() {
    }
}
