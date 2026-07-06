package io.github.blockneko11.sunshinecore.block.extension;

import io.github.blockneko11.sunshinecore.block.FlammableEntry;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.ApiStatus;

public interface BlockBehaviourPropertiesExtension {
    default BlockBehaviour.Properties sc$self() {
        return (BlockBehaviour.Properties) this;
    }

    default BlockBehaviour.Properties sc$flammable(int burnAbility, int spreadSpeed) {
        return sc$flammable(new FlammableEntry(burnAbility, spreadSpeed));
    }

    default BlockBehaviour.Properties sc$flammable(FlammableEntry entry) {
        throw new AssertionError();
    }

    @ApiStatus.Internal
    default FlammableEntry sc$getFlammable() {
        throw new AssertionError();
    }
}
