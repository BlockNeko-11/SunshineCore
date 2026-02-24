package io.github.blockneko11.sunshinecore.mixin.forge.client;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.client.event.ClientEvent;
import net.minecraft.client.Minecraft;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public abstract class MinecraftMixin {
    @Inject(
            method = "run",
            at = @At(
                    value = "FIELD",
                    target = "Lnet/minecraft/client/Minecraft;gameThread:Ljava/lang/Thread;",
                    shift = At.Shift.AFTER,
                    ordinal = 0,
                    opcode = Opcodes.PUTFIELD
            )
    )
    private void onRun$clientStarted(CallbackInfo ci) {
        SunshineCore.BUS.post(new ClientEvent.Started((Minecraft) (Object) this));
    }

    @Inject(
            method = "destroy",
            at = @At(
                    value = "INVOKE",
                    target = "Lorg/slf4j/Logger;info(Ljava/lang/String;)V",
                    shift = At.Shift.AFTER,
                    remap = false
            )
    )
    private void onDestroy$clientStopping(CallbackInfo ci) {
        SunshineCore.BUS.post(new ClientEvent.Stopping((Minecraft) (Object) this));
    }
}
