package io.github.blockneko11.sunshinecore.item;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.blockneko11.sunshinecore.block.FlattenableBlock;
import io.github.blockneko11.sunshinecore.event.SetupEvent;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Item interaction registry API, which is used for registering item interactions.
 * Now it's just only for tools (e.g. axe, shovel, hoe).
 * <h1>NOTE: Please call these methods in the event subscriber of {@link SetupEvent}.</h1>
 */
public final class ItemInteractionRegistry {
    @ExpectPlatform
    public static void registerStrippable(Block before, Block after) {
        throw new AssertionError();
    }

    public static void registerFlattenable(Block before, FlattenableBlock after) {
        registerFlattenable(before, after.getFlattenedState());
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
