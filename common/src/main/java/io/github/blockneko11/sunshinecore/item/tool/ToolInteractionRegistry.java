package io.github.blockneko11.sunshinecore.item.tool;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.blockneko11.sunshinecore.event.initialize.SetupEvent;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * NOTE: Please use these methods with {@link SetupEvent#EVENT}.</h1>
 */
public final class ToolInteractionRegistry {
    @ExpectPlatform
    public static void registerStrippable(Block before, Block after) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerFlattenable(Block before, BlockState after) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerTillable(Block input, Predicate<UseOnContext> predicate, Consumer<UseOnContext> action) {
        throw new AssertionError();
    }
}
