package io.github.blockneko11.sunshinecore.mixin.forge;

import io.github.blockneko11.sunshinecore.item.forge.ItemInteractionRegistryImpl;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.common.ItemAbility;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AxeItem.class)
public abstract class AxeItemMixin {
    @Redirect(
            method = "evaluateNewBlockState",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;getToolModifiedState(Lnet/minecraft/world/item/context/UseOnContext;Lnet/neoforged/neoforge/common/ItemAbility;Z)Lnet/minecraft/world/level/block/state/BlockState;",
                    ordinal = 0
            )
    )
    private BlockState modifyState$registerStrippables(BlockState base, UseOnContext context, ItemAbility ability, boolean simulate) {
        Block modified$sc = ItemInteractionRegistryImpl.STRIPPABLES.get(base.getBlock());

        if (modified$sc != null) {
            return modified$sc.defaultBlockState().setValue(BlockStateProperties.AXIS, base.getValue(BlockStateProperties.AXIS));
        }

        return base.getToolModifiedState(context, ability, simulate);
    }

    // TODO: Add custom interactions
}
