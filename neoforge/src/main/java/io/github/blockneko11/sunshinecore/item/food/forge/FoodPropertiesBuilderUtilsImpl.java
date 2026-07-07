package io.github.blockneko11.sunshinecore.item.food.forge;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import java.util.function.Supplier;

public final class FoodPropertiesBuilderUtilsImpl {
    public static FoodProperties.Builder applyEffect(FoodProperties.Builder builder, Supplier<MobEffectInstance> effect, float chance) {
        return builder.effect(effect, chance);
    }
}
