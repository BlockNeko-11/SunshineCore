package io.github.blockneko11.sunshinecore.block;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.blockneko11.sunshinecore.event.initialize.SetupEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.level.block.Block;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * Block interaction registry API, which is used for registering interactions for some specific blocks.
 * <h1>NOTE: Please use these methods with {@link SetupEvent#EVENT}.</h1>
 */
public final class BlockInteractionRegistry {
    public static void registerFlammable(int flameAbility, int spreadSpeed, Block... blocks) {
        registerFlammable(flameAbility, spreadSpeed, Arrays.asList(blocks));
    }

    @ExpectPlatform
    public static void registerFlammable(int flameAbility, int spreadSpeed, Collection<Block> blocks) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerFlammable(int burn, int spread, TagKey<Block> tag) {
        throw new AssertionError();
    }

    public static void registerComposting(float chance, Item... items) {
        registerComposting(chance, Arrays.asList(items));
    }

    @ExpectPlatform
    public static void registerComposting(float chance, Collection<Item> items) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerComposting(float chance, TagKey<Item> tag) {
        throw new AssertionError();
    }

    public static void registerFuel(int burnTick, Item... items) {
        registerFuel(burnTick, Arrays.asList(items));
    }

    @ExpectPlatform
    public static void registerFuel(int burnTick, Collection<Item> items) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerFuel(int burnTick, TagKey<Item> tag) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerBrewingRecipe(Consumer<PotionBrewing.Builder> builder) {
        throw new AssertionError();
    }

    private BlockInteractionRegistry() {
    }
}
