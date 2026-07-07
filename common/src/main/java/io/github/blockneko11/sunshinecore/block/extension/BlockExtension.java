package io.github.blockneko11.sunshinecore.block.extension;

import io.github.blockneko11.sunshinecore.block.FlammableEntry;
import net.minecraft.world.level.block.Block;

public interface BlockExtension {
    default Block sc$self() {
        return (Block) this;
    }

    default FlammableEntry sc$getFlammability() {
        return null;
    }
}
