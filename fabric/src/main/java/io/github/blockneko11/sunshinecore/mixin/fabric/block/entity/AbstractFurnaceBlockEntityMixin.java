package io.github.blockneko11.sunshinecore.mixin.fabric.block.entity;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFurnaceBlockEntity.class)
public abstract class AbstractFurnaceBlockEntityMixin {
    @Inject(
            method = "isFuel",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void sc$isFuel(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Item item = stack.getItem();

        if (item.sc$getBurnTick() <= 0) {
            return;
        }

        cir.setReturnValue(true);
        cir.cancel();
    }

    @Inject(
            method = "getBurnDuration",
            at = @At("TAIL"),
            cancellable = true
    )
    private static void sc$getBurnDuration(ItemStack fuel, CallbackInfoReturnable<Integer> cir) {
        Item item = fuel.getItem();
        if (item.sc$getBurnTick() <= 0) {
            return;
        }

        cir.setReturnValue(item.sc$getBurnTick());
        cir.cancel();
    }
}
