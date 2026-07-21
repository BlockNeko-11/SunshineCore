package io.github.blockneko11.sunshinecore.registry.holder;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

public class ItemHolder<I extends Item> extends RegistryHolder<Item, I> implements ItemLike {
    public ItemHolder(Holder<Item> holder, ResourceKey<Item> key) {
        super(holder, BuiltInRegistries.ITEM, key);
    }

    public ItemHolder(RegistryHolder<Item, I> delegate) {
        super(delegate);
    }

    @Override
    public Item asItem() {
        return this.get();
    }
}
