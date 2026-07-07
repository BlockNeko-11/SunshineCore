package io.github.blockneko11.sunshinecore.mixin.fabric.block;

import net.minecraft.core.Direction;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net.minecraft.world.level.block.ComposterBlock$InputContainer")
public abstract class ComposterBlockInputContainerMixin {
    @Shadow
    private boolean changed;

    @Inject(
            method = "canPlaceItemThroughFace",
            at = @At("HEAD"),
            cancellable = true
    )
    private void sc$canPlaceItemThroughFace(int index, ItemStack itemStack, Direction direction, CallbackInfoReturnable<Boolean> cir) {
        Item item = itemStack.getItem();

        if (!changed && direction == Direction.UP // vanilla logic
                && item.sc$getCompostingChance() > 0.0f) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
