package io.github.blockneko11.sunshinecore.mixin.forge.block;

import io.github.blockneko11.sunshinecore.block.forge.BlockInteractionRegistryImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FireBlock.class)
public abstract class FireBlockMixin {
    @ModifyVariable(
            method = "checkBurnOut",
            at = @At("STORE"),
            ordinal = 2
    )
    private int getFlammability$registerFlammable(int baseValue, Level level, BlockPos pos) {
        Block block = level.getBlockState(pos).getBlock();
        BlockInteractionRegistryImpl.FlammableEntry entry = BlockInteractionRegistryImpl.FLAMMABLES.get(block);

        if (entry == null) {
            return baseValue;
        }

        return entry.getFlameAbility();
    }

    @Inject(
            method = "getIgniteOdds(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;)I",
            at = @At("TAIL"),
            cancellable = true
    )
    private void getIgniteOdds$registerFlammable(LevelReader level, BlockPos pos, CallbackInfoReturnable<Integer> cir) {
        Block block = level.getBlockState(pos).getBlock();
        BlockInteractionRegistryImpl.FlammableEntry entry = BlockInteractionRegistryImpl.FLAMMABLES.get(block);

        if (entry == null) {
            return;
        }

        int speed = entry.getSpreadSpeed();
        cir.setReturnValue(Math.max(cir.getReturnValue(), speed));
    }
}
