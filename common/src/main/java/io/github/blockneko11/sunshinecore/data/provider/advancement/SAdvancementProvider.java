package io.github.blockneko11.sunshinecore.data.provider.advancement;

import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public abstract class SAdvancementProvider implements DataProvider {
    private final PackOutput output;
    private final CompletableFuture<HolderLookup.Provider> lookupProvider;

    public SAdvancementProvider(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        this.output = output;
        this.lookupProvider = lookupProvider;
    }

    public abstract void generate(HolderLookup.Provider lookup, Consumer<AdvancementHolder> exporter);

    @NotNull
    @Override
    public CompletableFuture<?> run(CachedOutput writer) {
        return this.lookupProvider.thenCompose(lookup -> this.run(writer, lookup));
    }

    private CompletableFuture<?> run(CachedOutput writer, HolderLookup.Provider lookup) {
        Set<ResourceLocation> ids = new HashSet<>();
        Set<AdvancementHolder> advancements = new HashSet<>();

        this.generate(lookup, advancements::add);

        RegistryOps<JsonElement> ops = lookup.createSerializationContext(JsonOps.INSTANCE);
        List<CompletableFuture<?>> futures = new ArrayList<>();

        for (AdvancementHolder advancement : advancements) {
            ResourceLocation id = advancement.id();
            if (ids.contains(id)) {
                throw new IllegalStateException("Duplicate advancement definition for " + id);
            }

            ids.add(id);
            futures.add(DataProvider.saveStable(writer,
                    Advancement.CODEC.encodeStart(ops, advancement.value()).getOrThrow(),
                    this.output.createRegistryElementsPathProvider(Registries.ADVANCEMENT).json(id)));
        }

        return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
    }

    @NotNull
    @Override
    public final String getName() {
        return "Sunshine Core Advancement Provider";
    }
}
