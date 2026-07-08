package io.github.blockneko11.sunshinecore.mixin.entity.player;

import net.minecraft.core.NonNullList;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Inventory.class)
public abstract class InventoryMixin {
    @Shadow
    @Final
    public NonNullList<ItemStack> armor;

    @Shadow
    @Final
    public Player player;

    @Inject(
            method = "tick",
            at = @At("RETURN")
    )
    private void sc$tick(CallbackInfo ci) {
        for (ItemStack stack : armor) {
            Item item = stack.getItem();
            item.sc$armorTick(stack, player);
        }
    }
}
