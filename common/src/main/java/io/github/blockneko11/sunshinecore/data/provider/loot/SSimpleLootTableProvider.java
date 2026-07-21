package io.github.blockneko11.sunshinecore.data.provider.loot;

import com.google.gson.JsonObject;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSet;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public abstract class SSimpleLootTableProvider implements LootTableSubProvider, DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> lookupProvider;
    private final LootContextParamSet lootContextType;

    public SSimpleLootTableProvider(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, LootContextParamSet lootContextType) {
        this.output = output;
        this.lookupProvider = lookupProvider;
        this.lootContextType = lootContextType;
    }

    @NotNull
    @Override
    public CompletableFuture<?> run(CachedOutput writer) {
        return this.lookupProvider.thenCompose(lookup -> this.run(writer, lookup));
    }

    private CompletableFuture<?> run(CachedOutput writer, HolderLookup.Provider lookup) {
        Map<ResourceLocation, LootTable> builders = new HashMap<>();
        this.generate((key, builder) -> {
            ResourceLocation id = key.location();
            if (builders.containsKey(id)) {
                throw new IllegalStateException("Duplicate loot table definition for " + key);
            }

            builders.put(id, builder.setParamSet(this.lootContextType).build());
        });

        CompletableFuture<?>[] futures = builders.entrySet()
                .stream()
                .map(e ->
                        DataProvider.saveStable(writer,
                        LootTable.DIRECT_CODEC.encodeStart(lookup.createSerializationContext(JsonOps.INSTANCE), e.getValue()).getOrThrow(),
                        this.output.createPathProvider(PackOutput.Target.DATA_PACK, "loot_tables").json(e.getKey()))).toArray(CompletableFuture<?>[]::new);

        return CompletableFuture.allOf(futures);
    }

    @NotNull
    @Override
    public final String getName() {
        return "Sunshine Core Simple Loot Table Provider for " + LootContextParamSets.REGISTRY.inverse().get(this.lootContextType);
    }
}
