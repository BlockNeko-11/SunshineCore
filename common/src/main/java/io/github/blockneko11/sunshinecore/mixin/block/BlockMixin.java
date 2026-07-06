package io.github.blockneko11.sunshinecore.mixin.block;

import io.github.blockneko11.sunshinecore.block.FlammableEntry;
import io.github.blockneko11.sunshinecore.block.FlammableRegistry;
import io.github.blockneko11.sunshinecore.block.extension.BlockExtension;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin implements BlockExtension {
    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void sc$onBlockCtor(BlockBehaviour.Properties props, CallbackInfo ci) {
        FlammableEntry entry = props.sc$getFlammable();
        if (entry != null) {
            FlammableRegistry.register(entry, sc$self());
        }
    }
}
