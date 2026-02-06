package io.github.blockneko11.sunshinecore.mixin.forge;

import io.github.blockneko11.sunshinecore.item.forge.ItemInteractionRegistryImpl;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.ItemAbility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ShovelItem.class)
public abstract class ShovelItemMixin {
    @Redirect(
            method = "useOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;getToolModifiedState(Lnet/minecraft/world/item/context/UseOnContext;Lnet/neoforged/neoforge/common/ItemAbility;Z)Lnet/minecraft/world/level/block/state/BlockState;",
                    ordinal = 0
            )
    )
    private BlockState modifyState$registerFlattenables(BlockState base, UseOnContext context, ItemAbility ability, boolean simulate) {
        BlockState modified$sc = ItemInteractionRegistryImpl.FLATTENABLES.get(base.getBlock());
        if (modified$sc != null) {
            return modified$sc;
        }

        return base.getToolModifiedState(context, ability, simulate);
    }
}
