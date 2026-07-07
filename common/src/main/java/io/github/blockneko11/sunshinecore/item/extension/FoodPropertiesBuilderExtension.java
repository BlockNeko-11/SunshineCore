package io.github.blockneko11.sunshinecore.item.extension;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;

import java.util.function.Supplier;

public interface FoodPropertiesBuilderExtension {
    default FoodProperties.Builder sc$self() {
        return (FoodProperties.Builder) this;
    }

    default FoodProperties.Builder sc$effect(MobEffectInstance effect, float chance) {
        return sc$effect(() -> effect, chance);
    }

    default FoodProperties.Builder sc$effect(Supplier<MobEffectInstance> effect, float chance) {
        throw new AssertionError();
    }
}
