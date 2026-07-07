package io.github.blockneko11.sunshinecore.mixin.item;

import io.github.blockneko11.sunshinecore.item.extension.FoodPropertiesBuilderExtension;
import io.github.blockneko11.sunshinecore.item.food.FoodPropertiesBuilderUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.food.FoodProperties;
import org.spongepowered.asm.mixin.Mixin;

import java.util.function.Supplier;

@Mixin(FoodProperties.Builder.class)
public abstract class FoodPropertiesBuilderMixin implements FoodPropertiesBuilderExtension {
    @Override
    public FoodProperties.Builder sc$effect(Supplier<MobEffectInstance> effect, float chance) {
        return FoodPropertiesBuilderUtils.applyEffect(sc$self(), effect, chance);
    }
}
