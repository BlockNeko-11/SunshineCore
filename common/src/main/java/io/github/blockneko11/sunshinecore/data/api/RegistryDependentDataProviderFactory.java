package io.github.blockneko11.sunshinecore.data.api;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

@FunctionalInterface
public interface RegistryDependentDataProviderFactory<T extends DataProvider> {
    T create(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider);
}
