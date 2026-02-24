package io.github.blockneko11.sunshinecore.block.extension;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public interface FlattenableBlock {
    default BlockState getFlattenedState() {
        return ((Block) this).defaultBlockState();
    }
}
