package io.github.blockneko11.sunshinecore.mixin.fabric.data;

import net.fabricmc.fabric.impl.datagen.FabricDataGenHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = FabricDataGenHelper.class, remap = false)
public abstract class FabricDataGenHelperMixin {
    @Inject(
            method = "run",
            at = @At(value = "INVOKE",
                    target = "Lnet/fabricmc/fabric/impl/datagen/FabricDataGenHelper;runInternal()V",
                    shift = At.Shift.AFTER
            )
    )
    private static void onRun$fixDataGenRun(CallbackInfo ci) {
        System.exit(0);
    }
}
