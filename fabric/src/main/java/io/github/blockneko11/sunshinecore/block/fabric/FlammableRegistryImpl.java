package io.github.blockneko11.sunshinecore.block.fabric;

import io.github.blockneko11.sunshinecore.block.FlammableEntry;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Collection;

public final class FlammableRegistryImpl {
    public static void register(FlammableEntry entry, Collection<Block> blocks) {
        for (Block block : blocks) {
            FlammableBlockRegistry.getDefaultInstance().add(block, entry.flameAbility(), entry.spreadSpeed());
        }
    }

    public static void register(FlammableEntry entry, TagKey<Block> tag) {
        FlammableBlockRegistry.getDefaultInstance().add(tag, entry.flameAbility(), entry.spreadSpeed());
    }

    private FlammableRegistryImpl() {
    }
}
