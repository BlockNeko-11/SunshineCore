package io.github.blockneko11.sunshinecore.item.forge;

import com.mojang.datafixers.util.Pair;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class ItemInteractionRegistryImpl {
    public static final Map<Block, Block> STRIPPABLES = new HashMap<>();
    public static final Map<Block, BlockState> FLATTENABLES = new HashMap<>();
    public static final Map<Block, Pair<Predicate<UseOnContext>, Consumer<UseOnContext>>> TILLABLES = new HashMap<>();

    public static void registerStrippable(Block before, Block after) {
        if (!before.getStateDefinition().getProperties().contains(BlockStateProperties.AXIS)) {
            throw new IllegalArgumentException("block before stripping requires a \"axis\" property");
        }

        if (!after.getStateDefinition().getProperties().contains(BlockStateProperties.AXIS)) {
            throw new IllegalArgumentException("block after stripping requires a \"axis\" property");
        }

        STRIPPABLES.put(before, after);
    }

    public static void registerFlattenable(Block before, BlockState after) {
        FLATTENABLES.put(before, after);
    }

    public static void registerTillable(Block input, Predicate<UseOnContext> predicate, Consumer<UseOnContext> action) {
        TILLABLES.put(input, Pair.of(predicate, action));
    }

//    public static void onBlockToolModification(BlockEvent.BlockToolModificationEvent e) {
//        UseOnContext context = e.getContext();
//        ItemAbility ability = e.getItemAbility();
//        ItemStack held = e.getHeldItemStack();
//        BlockState beforeState = e.getState();
//        Block before = beforeState.getBlock();
//        LevelAccessor level = e.getLevel();
//
//        if (level.isClientSide()) {
//            return;
//        }
//
//        if (ability == ItemAbilities.AXE_STRIP &&
//                held.canPerformAction(ItemAbilities.AXE_STRIP)) {
//            Block after = STRIPPABLES.get(before);
//
//            if (after != null) {
//                Direction.Axis axis = beforeState.getValue(BlockStateProperties.AXIS);
//                e.setFinalState(after.defaultBlockState().setValue(BlockStateProperties.AXIS, axis));
//            }
//
//            return;
//        }
//
//        if (ability == ItemAbilities.SHOVEL_FLATTEN &&
//                held.canPerformAction(ItemAbilities.SHOVEL_FLATTEN)) {
//            BlockState afterState = FLATTENABLES.get(before);
//
//            if (afterState != null) {
//                e.setFinalState(afterState);
//            }
//
//            return;
//        }
//
//        if (ability == ItemAbilities.HOE_TILL &&
//                held.canPerformAction(ItemAbilities.HOE_TILL)) {
//            Pair<Predicate<UseOnContext>, Consumer<UseOnContext>> tillable = TILLABLES.get(before);
//
//            if (tillable != null && tillable.getFirst().test(context)) {
//                tillable.getSecond().accept(context);
//            }
//
//            return;
//        }
//    }
}
