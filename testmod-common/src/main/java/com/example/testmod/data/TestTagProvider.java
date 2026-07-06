package com.example.testmod.data;

import com.example.testmod.registry.TestRegistry;
import io.github.blockneko11.sunshinecore.data.provider.tag.STagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public final class TestTagProvider {
    public static final class Block extends STagProvider.BlockProvider {
        public Block(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(modId, validate, output, lookupProvider);
        }

        @Override
        public void addTag(HolderLookup.Provider lookup) {
            this.getTagBuilder(TestRegistry.TEST_BLOCKS)
                    .add(TestRegistry.TEST_BLOCK.get())
                    .add(TestRegistry.TEST_BLOCK_FLATTENED.get())
                    .add(TestRegistry.TEST_BLOCK_FLAMMABLE.get());
        }
    }

    public static final class Item extends STagProvider.ItemProvider {
        public Item(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(modId, validate, output, lookupProvider);
        }

        @Override
        public void addTag(HolderLookup.Provider lookup) {
            this.getTagBuilder(TestRegistry.TEST_BLOCK_ITEMS)
                    .add(TestRegistry.TEST_BLOCK_ITEM.get())
                    .add(TestRegistry.TEST_BLOCK_FLATTENED_ITEM.get())
                    .add(TestRegistry.TEST_BLOCK_FLAMMABLE_ITEM.get());
        }
    }
}
