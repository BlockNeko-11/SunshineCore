package io.github.blockneko11.sunshinecore.data.provider;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public abstract class SBlockLootTableProvider extends BlockLootSubProvider implements DataProvider {
    private final String modId;
    private final boolean validate;
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> lookupProvider;

    private final Set<ResourceLocation> validateExcludes = new HashSet<>();

    public SBlockLootTableProvider(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags(), lookupProvider.join());
        this.modId = modId;
        this.validate = validate;
        this.output = output;
        this.lookupProvider = lookupProvider;
    }

    public void excludeValidate(Block block) {
        this.validateExcludes.add(BuiltInRegistries.BLOCK.getKey(block));
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> exporter) {
        this.generate();
        for (Map.Entry<ResourceKey<LootTable>, LootTable.Builder> entry : this.map.entrySet()) {
            ResourceKey<LootTable> key = entry.getKey();

            if (key == BuiltInLootTables.EMPTY) {
                continue;
            }

            exporter.accept(key, entry.getValue());
        }

        if (this.validate) {
            Set<ResourceLocation> missing = new HashSet<>();

            for (ResourceLocation blockId : BuiltInRegistries.BLOCK.keySet()) {
                if (blockId.getNamespace().equals(this.modId)) {
                    ResourceKey<LootTable> blockLootTableId = BuiltInRegistries.BLOCK.get(blockId).getLootTable();

                    if (blockLootTableId.location().getNamespace().equals(this.modId)) {
                        if (!this.map.containsKey(blockLootTableId)) {
                            missing.add(blockId);
                        }
                    }
                }
            }

            missing.removeAll(this.validateExcludes);

            if (!missing.isEmpty()) {
                throw new IllegalStateException("Missing block loot table for " + missing);
            }
        }
    }

    @NotNull
    @Override
    public CompletableFuture<?> run(CachedOutput writer) {
        Map<ResourceLocation, LootTable> builders = new HashMap<>();
        return this.lookupProvider.thenCompose(lookup -> {
            this.generate((key, builder) -> {
                ResourceLocation id = key.location();
                if (builders.containsKey(id)) {
                    throw new IllegalStateException("Duplicate loot table definition for " + key);
                }

                builders.put(id, builder.setParamSet(LootContextParamSets.BLOCK).build());
            });

            CompletableFuture<?>[] futures = builders.entrySet()
                    .stream()
                    .map(e -> {
                        JsonObject json = (JsonObject) LootTable.DIRECT_CODEC.encodeStart(lookup.createSerializationContext(JsonOps.INSTANCE), e.getValue()).getOrThrow();
                        return DataProvider.saveStable(writer, json,
                                this.output.createPathProvider(PackOutput.Target.DATA_PACK, "loot_tables").json(e.getKey()));
                    }).toArray(CompletableFuture<?>[]::new);

            return CompletableFuture.allOf(futures);
        });
    }

    @NotNull
    @Override
    public final String getName() {
        return "Sunshine Core Block Loot Table Provider";
    }
}
