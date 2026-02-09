package io.github.blockneko11.sunshinecore.data;

import io.github.blockneko11.sunshinecore.data.api.DataProviderFactory;
import io.github.blockneko11.sunshinecore.data.api.RegistryDependentDataProviderFactory;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public final class SDataGeneration {
    private final String modId;
    private final boolean validate;
    private final DataGenerator.PackGenerator pack;
    private final CompletableFuture<HolderLookup.Provider> lookupProvider;

    private final List<DataProviderFactory<?>> providers = new ArrayList<>();
    private final List<RegistryDependentDataProviderFactory<?>> registryDependentProviders = new ArrayList<>();

    public SDataGeneration(String modId, boolean validate, DataGenerator.PackGenerator pack, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        this.modId = modId;
        this.validate = validate;
        this.pack = pack;
        this.lookupProvider = lookupProvider;
    }

    public <T extends DataProvider> void addProvider(DataProviderFactory<T> provider) {
        this.providers.add(provider);
    }

    public <T extends DataProvider> void addProvider(RegistryDependentDataProviderFactory<T> provider) {
        this.registryDependentProviders.add(provider);
    }

    public void run() {
        this.providers.forEach(provider -> {
            this.pack.addProvider(output -> provider.create(this.modId, this.validate, output));
        });

        this.registryDependentProviders.forEach(provider -> {
            this.pack.addProvider(output -> provider.create(this.modId, this.validate, output, this.lookupProvider));
        });
    }
}
