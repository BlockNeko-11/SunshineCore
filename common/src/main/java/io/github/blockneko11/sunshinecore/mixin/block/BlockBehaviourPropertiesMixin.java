package io.github.blockneko11.sunshinecore.mixin.block;

import io.github.blockneko11.sunshinecore.block.FlammableEntry;
import io.github.blockneko11.sunshinecore.block.extension.BlockBehaviourPropertiesExtension;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(BlockBehaviour.Properties.class)
public class BlockBehaviourPropertiesMixin implements BlockBehaviourPropertiesExtension {
    @Unique
    private FlammableEntry sc$flammable;

    @Override
    public BlockBehaviour.Properties sc$flammable(FlammableEntry entry) {
        this.sc$flammable = entry;
        return sc$self();
    }

    @Override
    public FlammableEntry sc$getFlammable() {
        return this.sc$flammable;
    }
}
