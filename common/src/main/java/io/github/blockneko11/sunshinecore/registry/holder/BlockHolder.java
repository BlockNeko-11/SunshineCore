package io.github.blockneko11.sunshinecore.registry.holder;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class BlockHolder<B extends Block> extends RegistryHolder<Block, B> implements ItemLike {
    public BlockHolder(Holder<Block> holder, ResourceKey<Block> key) {
        super(holder, BuiltInRegistries.BLOCK, key);
    }

    public BlockHolder(RegistryHolder<Block, B> delegate) {
        super(delegate);
    }

    @Override
    public Item asItem() {
        return this.get().asItem();
    }
}
