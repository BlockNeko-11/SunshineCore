package io.github.blockneko11.sunshinecore.mixin.fabric.entity;

import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Deprecated(since = "1.3.0")
@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void playerTickPre(CallbackInfo ci) {
    }

    @Inject(
            method = "tick",
            at = @At("RETURN")
    )
    private void playerTickPost(CallbackInfo ci) {
    }
}
