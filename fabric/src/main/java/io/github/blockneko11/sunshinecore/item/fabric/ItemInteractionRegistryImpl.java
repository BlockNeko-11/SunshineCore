package io.github.blockneko11.sunshinecore.item.fabric;

import net.fabricmc.fabric.api.registry.FlattenableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.fabricmc.fabric.api.registry.TillableBlockRegistry;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

import java.util.function.Consumer;
import java.util.function.Predicate;

// TODO: Add ItemAbility to Fabric
public final class ItemInteractionRegistryImpl {
    public static void registerStrippable(Block before, Block after) {
        StrippableBlockRegistry.register(before, after);
    }

    public static void registerFlattenable(Block before, BlockState after) {
        FlattenableBlockRegistry.register(before, after);
    }

    public static void registerTillable(Block input, Predicate<UseOnContext> predicate, Consumer<UseOnContext> action) {
        TillableBlockRegistry.register(input, predicate, action);
    }
}
