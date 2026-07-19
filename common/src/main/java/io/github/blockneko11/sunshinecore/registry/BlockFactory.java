package io.github.blockneko11.sunshinecore.registry;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

@FunctionalInterface
public interface BlockFactory<B extends Block> extends Function<BlockBehaviour.Properties, B> {
}
