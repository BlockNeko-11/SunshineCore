package io.github.blockneko11.sunshinecore.item.container;

import net.minecraft.core.Direction;
import net.minecraft.world.WorldlyContainer;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.stream.IntStream;

@FunctionalInterface
public interface ListBackendSidedContainer extends ListBackendContainer, WorldlyContainer {
    @Override
    default int[] getSlotsForFace(Direction side) {
        return IntStream.of(getList().size()).toArray();
    }

    @Override
    default boolean canPlaceItemThroughFace(int index, ItemStack itemStack, @Nullable Direction direction) {
        return true;
    }

    @Override
    default boolean canTakeItemThroughFace(int index, ItemStack stack, Direction direction) {
        return direction == Direction.DOWN;
    }
}
