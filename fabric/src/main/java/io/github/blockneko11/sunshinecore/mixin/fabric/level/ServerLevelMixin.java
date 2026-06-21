package io.github.blockneko11.sunshinecore.mixin.fabric.level;

import io.github.blockneko11.sunshinecore.server.event.level.ServerLevelEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.ProgressListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerLevel.class)
public abstract class ServerLevelMixin {
    // TODO: use more correct injection point
    @Inject(
            method = "save",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/server/level/ServerChunkCache;save(Z)V",
                    shift = At.Shift.AFTER
            )
    )
    private void serverLevelSave(ProgressListener progress, boolean flush, boolean skipSave, CallbackInfo ci) {
        ServerLevelEvents.SAVE.invoker().handle((ServerLevel) (Object) this);
    }
}
