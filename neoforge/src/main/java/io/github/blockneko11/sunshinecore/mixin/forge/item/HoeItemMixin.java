package io.github.blockneko11.sunshinecore.mixin.forge.item;

import com.mojang.datafixers.util.Pair;
import io.github.blockneko11.sunshinecore.item.forge.ItemInteractionRegistryImpl;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Consumer;
import java.util.function.Predicate;

@Mixin(HoeItem.class)
public abstract class HoeItemMixin {
    @Inject(
            method = "useOn",
            at = @At("RETURN"),
            slice = @Slice(
                    from = @At(
                            value = "INVOKE",
                            target = "Lcom/mojang/datafixers/util/Pair;of(Ljava/lang/Object;Ljava/lang/Object;)Lcom/mojang/datafixers/util/Pair;"
                    ),
                    to = @At(
                            value = "INVOKE",
                            target = "Lcom/mojang/datafixers/util/Pair;getFirst()Ljava/lang/Object;"
                    )
            ),
            cancellable = true
    )
    private void modifyState$registerTillables(UseOnContext context, CallbackInfoReturnable<InteractionResult> cir) {
        Level level = context.getLevel();
        BlockPos pos = context.getClickedPos();
        Player player = context.getPlayer();
        Block base = level.getBlockState(pos).getBlock();
        Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> pair = ItemInteractionRegistryImpl.TILLABLES.get(base);

        if (pair == null) {
            cir.setReturnValue(InteractionResult.PASS);
            return;
        }

        if (pair.getFirst().test(context)) {
            level.playSound(player, pos, SoundEvents.HOE_TILL, SoundSource.BLOCKS, 1.0F, 1.0F);
            if (level.isClientSide()) {
                cir.setReturnValue(InteractionResult.SUCCESS);
                return;
            }

            pair.getSecond().accept(context);
            if (player != null) {
                context.getItemInHand().hurtAndBreak(1, player, LivingEntity.getSlotForHand(context.getHand()));
            }
            cir.setReturnValue(InteractionResult.CONSUME);
            return;
        }

        cir.setReturnValue(InteractionResult.SUCCESS);
    }
}
