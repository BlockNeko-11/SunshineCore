package io.github.blockneko11.sunshinecore.mixin.fabric.entity;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.event.level.player.PlayerEvent;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {
    @Inject(
            method = "tick",
            at = @At("HEAD")
    )
    private void playerTickPre(CallbackInfo ci) {
        SunshineCore.SC_EVENT_BUS.post(new PlayerEvent.PreTick((Player) (Object) this));
    }

    @Inject(
            method = "tick",
            at = @At("RETURN")
    )
    private void playerTickPost(CallbackInfo ci) {
        SunshineCore.SC_EVENT_BUS.post(new PlayerEvent.PostTick((Player) (Object) this));
    }
}
