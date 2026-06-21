package io.github.blockneko11.sunshinecore.mixin.data;

import io.github.blockneko11.sunshinecore.data.provider.model.SModelProvider;
import net.minecraft.data.models.ModelProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModelProvider.class)
public abstract class ModelProviderMixin {
    @Inject(
            method = "getName",
            at = @At("HEAD"),
            cancellable = true
    )
    private void getName(CallbackInfoReturnable<String> cir) {
        if (((ModelProvider) (Object) this) instanceof SModelProvider) {
            cir.setReturnValue("Sunshine Core Model Provider");
            cir.cancel();
        }
    }
}
