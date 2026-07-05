package io.github.blockneko11.sunshinecore.item.alchemy.forge;

import net.minecraft.world.item.alchemy.PotionBrewing;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class PotionBrewingRegistryImpl {
    private static final List<Consumer<PotionBrewing.Builder>> BREWING_RECIPES = new ArrayList<>();

    public static void registerBrewingRecipe(Consumer<PotionBrewing.Builder> builder) {
        BREWING_RECIPES.add(builder);
    }

    @SubscribeEvent
    public static void onRegister(RegisterBrewingRecipesEvent e) {
        for (Consumer<PotionBrewing.Builder> consumer : BREWING_RECIPES) {
            consumer.accept(e.getBuilder());
        }
    }

    private PotionBrewingRegistryImpl() {
    }
}
