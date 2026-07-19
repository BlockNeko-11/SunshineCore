package io.github.blockneko11.sunshinecore.registry;

import net.minecraft.world.item.Item;

import java.util.function.Function;

@FunctionalInterface
public interface ItemFactory<I extends Item> extends Function<Item.Properties, I> {
}
