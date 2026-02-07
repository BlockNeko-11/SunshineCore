package io.github.blockneko11.sunshinecore.mixin.forge.block;

import io.github.blockneko11.sunshinecore.block.forge.BlockInteractionRegistryImpl;
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
        float chance = BlockInteractionRegistryImpl.COMPOSTABLES.getOrDefault(item, -1.0f);
        cir.setReturnValue(chance);
    }
}
