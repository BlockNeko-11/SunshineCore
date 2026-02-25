package io.github.blockneko11.sunshinecore.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.blockneko11.sunshinecore.event.CommonEvents;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.Collection;

/**
 * Block interaction registry API, which is used for registering interactions for some specific blocks.
 * <h1>NOTE: Please use these methods with {@link CommonEvents#SETUP}.</h1>
 */
public final class BlockInteractionRegistry {
    public static void registerFlammable(int flameAbility, int spreadSpeed, Block... blocks) {
        registerFlammable(flameAbility, spreadSpeed, Arrays.asList(blocks));
    }

    @ExpectPlatform
    public static void registerFlammable(int flameAbility, int spreadSpeed, Collection<Block> blocks) {
        throw new AssertionError();
    }

//    public static void registerFlammable(int burn, int spread, TagKey<Block> tag) {
//        registerFlammable(burn, spread, RegistryUtil.getEntries(BuiltInRegistries.BLOCK, tag));
//    }

    public static void registerComposting(float chance, Item... items) {
        registerComposting(chance, Arrays.asList(items));
    }

    @ExpectPlatform
    public static void registerComposting(float chance, Collection<Item> items) {
        throw new AssertionError();
    }

//    public static void registerComposting(float chance, TagKey<Item> tag) {
//        registerComposting(chance, RegistryUtil.getEntries(BuiltInRegistries.ITEM, tag));
//    }

    public static void registerFuel(int burnTick, Item... items) {
        registerFuel(burnTick, Arrays.asList(items));
    }

    @ExpectPlatform
    public static void registerFuel(int burnTick, Collection<Item> items) {
        throw new AssertionError();
    }

//    public static void registerFuel(int burnTick, TagKey<Item> tag) {
//        registerFuel(burnTick, RegistryUtil.getEntries(BuiltInRegistries.ITEM, tag));
//    }

    private BlockInteractionRegistry() {
    }
}
