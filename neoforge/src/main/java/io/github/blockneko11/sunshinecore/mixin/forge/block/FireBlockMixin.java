package io.github.blockneko11.sunshinecore.mixin.forge.block;

import io.github.blockneko11.sunshinecore.block.forge.FlammableRegistryImpl;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(FireBlock.class)
public abstract class FireBlockMixin {
    @Redirect(
            method = "getBurnOdds",
            at = @At(
                    value = "INVOKE",
                    target = "Lit/unimi/dsi/fastutil/objects/Object2IntMap;getInt(Ljava/lang/Object;)I",
                    remap = false
            )
    )
    private int sc$getFlammability(Object2IntMap<Block> instance, Object o) {
        if (FlammableRegistryImpl.getFlammables().containsKey(o)) {
            return FlammableRegistryImpl.getFlammables().get(o).flameAbility();
        }

        return instance.getInt(o);
    }

    @Redirect(
            method = "getIgniteOdds(Lnet/minecraft/world/level/block/state/BlockState;)I",
            at = @At(
                    value = "INVOKE",
                    target = "Lit/unimi/dsi/fastutil/objects/Object2IntMap;getInt(Ljava/lang/Object;)I",
                    remap = false
            )
    )
    private int sc$getSpreadSpeed(Object2IntMap<Block> instance, Object o) {
        if (FlammableRegistryImpl.getFlammables().containsKey(o)) {
            return FlammableRegistryImpl.getFlammables().get(o).spreadSpeed();
        }

        return instance.getInt(o);
    }
}
