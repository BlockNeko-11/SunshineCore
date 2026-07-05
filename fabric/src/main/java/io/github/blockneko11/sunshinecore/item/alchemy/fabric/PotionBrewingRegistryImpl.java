package io.github.blockneko11.sunshinecore.item.alchemy.fabric;

import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder;
import net.minecraft.world.item.alchemy.PotionBrewing;

import java.util.function.Consumer;

public final class PotionBrewingRegistryImpl {
    public static void register(Consumer<PotionBrewing.Builder> builder) {
        FabricBrewingRecipeRegistryBuilder.BUILD.register(builder::accept);
    }

    private PotionBrewingRegistryImpl() {
    }
}
