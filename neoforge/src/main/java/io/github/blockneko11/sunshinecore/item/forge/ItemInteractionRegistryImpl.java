package io.github.blockneko11.sunshinecore.item.forge;

import com.mojang.datafixers.util.Pair;
import io.github.blockneko11.sunshinecore.mixin.forge.item.AxeItemMixin;
import io.github.blockneko11.sunshinecore.mixin.forge.item.ShovelItemMixin;
import io.github.blockneko11.sunshinecore.util.CollectionUtil;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ItemInteractionRegistryImpl {
    public static final Map<Block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>> TILLABLES = new HashMap<>();

    public static void registerStrippable(Block before, Block after) {
        if (!before.getStateDefinition().getProperties().contains(BlockStateProperties.AXIS)) {
            throw new IllegalArgumentException("block " + before.builtInRegistryHolder().getRegisteredName() + " requires a \"axis\" property");
        }

        if (!after.getStateDefinition().getProperties().contains(BlockStateProperties.AXIS)) {
            throw new IllegalArgumentException("block " + after.builtInRegistryHolder().getRegisteredName() + " requires a \"axis\" property");
        }

        CollectionUtil.toMutable(AxeItemMixin::getStrippables, AxeItemMixin::setStrippables);
        AxeItemMixin.getStrippables().put(before, after);
    }

    public static void registerFlattenable(Block before, BlockState after) {
        CollectionUtil.toMutable(ShovelItemMixin::getFlattenables, ShovelItemMixin::setFlattenables);
        ShovelItemMixin.getFlattenables().put(before, after);
    }

    // TODO: Use Forge's event
    public static void registerTillable(Block input, Predicate<UseOnContext> predicate, Consumer<UseOnContext> action) {
        TILLABLES.put(input, Pair.of(predicate, action));
    }

//        if (ability == ItemAbilities.HOE_TILL &&
//                held.canPerformAction(ItemAbilities.HOE_TILL)) {
//            Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> tillable = TILLABLES.get(block);
//
//            if (tillable != null && tillable.getFirst().test(context)) {
//                tillable.getSecond().accept(context);
//            }
//        }
//    }
}
