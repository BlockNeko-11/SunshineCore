package io.github.blockneko11.sunshinecore.item;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.blockneko11.sunshinecore.block.BlockInteractionRegistry;
import io.github.blockneko11.sunshinecore.event.SetupEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Collection;

/**
 * Fuel registry API, which is used for registering items as fuel.
 * <h1>NOTE: Please call these methods in the event subscriber of {@link SetupEvent}.</h1>
 * @deprecated Use {@link BlockInteractionRegistry} instead.
 */
@Deprecated
public final class FuelRegistry {
    public static void register(int burnTick, Item... items) {
        BlockInteractionRegistry.registerFuel(burnTick, items);
    }

    public static void register(int burnTick, Collection<Item> items) {
        BlockInteractionRegistry.registerFuel(burnTick, items);
    }

//    public static void register(int burnTick, TagKey<Item> tag) {
//        BlockInteractionRegistry.registerFuel(burnTick, tag);
//    }

    private FuelRegistry() {
    }
}
