package io.github.blockneko11.sunshinecore.mixin.forge.block.entity;

import io.github.blockneko11.sunshinecore.item.forge.FuelRegistryImpl;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {
    @Inject(
            method = "getBurnDuration",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getBurnDuration$registerFuel(ItemStack fuel, CallbackInfoReturnable<Integer> cir) {
        if (fuel.isEmpty()) {
            return;
        }

        int i = FuelRegistryImpl.getFuels().getOrDefault(fuel.getItem(), 0);
        if (i <= 0) {
            return;
        }

        cir.setReturnValue(i);
    }
}
