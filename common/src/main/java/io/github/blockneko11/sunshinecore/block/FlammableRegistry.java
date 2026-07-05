package io.github.blockneko11.sunshinecore.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.Collection;

public final class FlammableRegistry {
    public static void register(int flameAbility, int spreadSpeed, Block... blocks) {
        register(flameAbility, spreadSpeed, Arrays.asList(blocks));
    }

    @ExpectPlatform
    public static void register(int flameAbility, int spreadSpeed, Collection<Block> blocks) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void register(int burn, int spread, TagKey<Block> tag) {
        throw new AssertionError();
    }

    private FlammableRegistry() {
    }
}
