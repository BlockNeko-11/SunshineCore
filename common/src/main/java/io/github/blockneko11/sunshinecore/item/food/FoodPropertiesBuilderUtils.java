package io.github.blockneko11.sunshinecore.item.food;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import java.util.function.Supplier;

public final class FoodPropertiesBuilderUtils {
    @ExpectPlatform
    public static FoodProperties.Builder applyEffect(FoodProperties.Builder builder, Supplier<MobEffectInstance> effect, float chance) {
        throw new AssertionError();
    }

    private FoodPropertiesBuilderUtils() {
    }
}
