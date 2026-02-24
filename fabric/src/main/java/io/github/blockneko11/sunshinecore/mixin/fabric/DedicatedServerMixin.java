package io.github.blockneko11.sunshinecore.mixin.fabric;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.event.ServerEvent;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DedicatedServer.class)
public abstract class DedicatedServerMixin {
    @Inject(
            method = "initServer",
            at = @At("RETURN")
    )
    private void initServer$serverStarting(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) {
            SunshineCore.BUS.post(new ServerEvent.Starting((MinecraftServer) (Object) this));
        }
    }
}
