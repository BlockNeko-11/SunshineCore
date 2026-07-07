package io.github.blockneko11.sunshinecore.mixin.forge.block;

import io.github.blockneko11.sunshinecore.item.forge.CompostingRegistryImpl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.ComposterBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ComposterBlock.class)
public abstract class ComposterBlockMixin {
    @Inject(
            method = "getValue",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void getCompostingChance$registerComposting(ItemStack stack, CallbackInfoReturnable<Float> cir) {
        if (stack.isEmpty()) {
            return;
        }

        Item item = stack.getItem();
        if (item.sc$getCompostingChance() > 0.0f) {
            cir.setReturnValue(item.sc$getCompostingChance());
            cir.cancel();
            return;
        }

        float chance = CompostingRegistryImpl.getCompostables().getOrDefault(item, 0.0f);
        if (chance <= 0.0f) {
            return;
        }

        cir.setReturnValue(chance);
        cir.cancel();
    }
}
