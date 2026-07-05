package io.github.blockneko11.sunshinecore.item.alchemy;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.item.alchemy.PotionBrewing;

import java.util.function.Consumer;

public final class PotionBrewingRegistry {
    @ExpectPlatform
    public static void register(Consumer<PotionBrewing.Builder> builder) {
        throw new AssertionError();
    }

    private PotionBrewingRegistry() {
    }
}
