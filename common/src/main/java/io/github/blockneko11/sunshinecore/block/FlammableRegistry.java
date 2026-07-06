package io.github.blockneko11.sunshinecore.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.Collection;

public final class FlammableRegistry {
    public static void register(int flameAbility, int spreadSpeed, Block... blocks) {
        register(new FlammableEntry(flameAbility, spreadSpeed), Arrays.asList(blocks));
    }

    public static void register(int flameAbility, int spreadSpeed, Collection<Block> blocks) {
        register(new FlammableEntry(flameAbility, spreadSpeed), blocks);
    }

    public static void register(FlammableEntry entry, Block... blocks) {
        register(entry, Arrays.asList(blocks));
    }

    @ExpectPlatform
    public static void register(FlammableEntry entry, Collection<Block> blocks) {
        throw new AssertionError();
    }

    public static void register(int flameAbility, int spreadSpeed, TagKey<Block> tag) {
        register(new FlammableEntry(flameAbility, spreadSpeed), tag);
    }

    @ExpectPlatform
    public static void register(FlammableEntry entry, TagKey<Block> tag) {
        throw new AssertionError();
    }

    private FlammableRegistry() {
    }
}
