package io.github.blockneko11.sunshinecore.data.provider.tag;

import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagFile;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public abstract class STagProvider<T> implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> lookupProvider;
    private final ResourceKey<? extends Registry<T>> registryRef;
    private final Map<ResourceLocation, STagBuilder<T>> tagBuilders = new LinkedHashMap<>();

    public STagProvider(String modId,
                        boolean validate,
                        PackOutput output,
                        CompletableFuture<HolderLookup.Provider> lookupProvider,
                        ResourceKey<? extends Registry<T>> registryRef) {
        this.output = output;
        this.lookupProvider = lookupProvider;
        this.registryRef = registryRef;
    }

    public ResourceKey<T> toResourceKey(T entry) {
        // to make javac happy
        Registry<T> registry = (Registry<T>) BuiltInRegistries.REGISTRY.get((ResourceKey) this.registryRef);

        if (registry != null) {
            Optional<ResourceKey<T>> key = registry.getResourceKey(entry);

            if (key.isPresent()) {
                return key.get();
            }
        }

        throw new UnsupportedOperationException("Cannot query the registry key for type " + this.getClass().getCanonicalName());
    }

    public abstract void addTag(HolderLookup.Provider lookup);

    @NotNull
    @Override
    public CompletableFuture<?> run(CachedOutput writer) {
        return this.lookupProvider.thenApply(lookup -> {
            this.tagBuilders.clear();
            this.addTag(lookup);
            return lookup;
        }).thenCompose(lookup -> {
            HolderLookup.RegistryLookup<T> wrapper = lookup.lookupOrThrow(this.registryRef);
            return CompletableFuture.allOf(this.tagBuilders.entrySet()
                    .stream()
                    .map(e -> this.writeTag(writer, wrapper, e.getKey(), e.getValue()))
                    .toArray(CompletableFuture<?>[]::new));
        });
    }

    private CompletableFuture<?> writeTag(CachedOutput writer, HolderLookup.RegistryLookup<T> wrapper, ResourceLocation tagId, STagBuilder<T> builder) {
        List<TagEntry> entries = new ArrayList<>(builder.build()); // List#copyOf() returns an immutable list
        List<TagEntry> hasNotExist = entries.stream().filter(tagEntry -> !tagEntry.verifyIfPresent(id -> wrapper.get(ResourceKey.create(this.registryRef, id)).isPresent(), this.tagBuilders::containsKey)).toList();

        if (!hasNotExist.isEmpty()) {
            throw new IllegalArgumentException(String.format(Locale.ROOT, "Couldn't define tag %s as it is missing following references: %s", tagId, entries.stream().map(Objects::toString).collect(Collectors.joining(","))));
        }

        return DataProvider.saveStable(writer,
                TagFile.CODEC.encodeStart(JsonOps.INSTANCE, new TagFile(entries, builder.isReplace())).getOrThrow(),
                this.output.createRegistryTagsPathProvider(this.registryRef).json(tagId));
    }

    protected STagBuilder<T> getTagBuilder(TagKey<T> tag) {
        return this.tagBuilders.computeIfAbsent(tag.location(), $ -> new STagBuilder<>(this::toResourceKey));
    }

    @NotNull
    @Override
    public String getName() {
        return "Sunshine Core Tag Provider for " + this.registryRef.location();
    }

    public static abstract class BlockProvider extends STagProvider<Block> {
        public BlockProvider(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(modId, validate, output, lookupProvider, Registries.BLOCK);
        }

        @Override
        public ResourceKey<Block> toResourceKey(Block entry) {
            return entry.builtInRegistryHolder().key();
        }
    }

    public static abstract class ItemProvider extends STagProvider<Item> {
        public ItemProvider(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(modId, validate, output, lookupProvider, Registries.ITEM);
        }

        @Override
        public ResourceKey<Item> toResourceKey(Item entry) {
            return entry.builtInRegistryHolder().key();
        }

//        public void copy(TagKey<Block> blockTag, TagKey<Item> itemTag) {
//            if (this.blockTagProvider == null) {
//                throw new IllegalStateException("Cannot copy block tags to item tags without a block tag provider");
//            }
//            STagBuilder<Item> itemBuilder = this.getTagBuilder(itemTag);
//            this.blockTagProvider.getTagBuilder(blockTag)
//                    .build()
//                    .forEach(itemBuilder::add);
//        }
    }

    public static abstract class FluidProvider extends STagProvider<Fluid> {
        public FluidProvider(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(modId, validate, output, lookupProvider, Registries.FLUID);
        }

        @Override
        public ResourceKey<Fluid> toResourceKey(Fluid entry) {
            return entry.builtInRegistryHolder().key();
        }
    }

    public static abstract class BlockEntityTypeProvider extends STagProvider<BlockEntityType<?>> {
        public BlockEntityTypeProvider(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(modId, validate, output, lookupProvider, Registries.BLOCK_ENTITY_TYPE);
        }

        @Override
        public ResourceKey<BlockEntityType<?>> toResourceKey(BlockEntityType<?> entry) {
            return entry.builtInRegistryHolder().key();
        }
    }

    public static abstract class EntityTypeProvider extends STagProvider<EntityType<?>> {
        public EntityTypeProvider(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
            super(modId, validate, output, lookupProvider, Registries.ENTITY_TYPE);
        }

        @Override
        public ResourceKey<EntityType<?>> toResourceKey(EntityType<?> entry) {
            return entry.builtInRegistryHolder().key();
        }
    }
}
