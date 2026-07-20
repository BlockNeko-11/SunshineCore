package io.github.blockneko11.sunshinecore.data;

import io.github.blockneko11.sunshinecore.data.api.DataProviderFactory;
import io.github.blockneko11.sunshinecore.data.api.RegistryDependentDataProviderFactory;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import org.jetbrains.annotations.ApiStatus;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

/**
 * SDataGeneration API, which is used for data generation.
 * <h1>NOTE: Don't forget to call {@link #run()} after adding all the providers. Otherwise, the data generator will not generate any files.</h1>
 */
public final class SDataGeneration {
    private final String modId;
    private final boolean validate;
    private final DataGenerator.PackGenerator pack;
    private final CompletableFuture<HolderLookup.Provider> lookupProvider;

    private boolean hasRun = false;

    private final List<DataProviderFactory<?>> providers = new ArrayList<>();
    private final List<RegistryDependentDataProviderFactory<?>> registryDependentProviders = new ArrayList<>();

    @ApiStatus.Internal
    public SDataGeneration(String modId, boolean validate, DataGenerator.PackGenerator pack, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        this.modId = modId;
        this.validate = validate;
        this.pack = pack;
        this.lookupProvider = lookupProvider;
    }

    /**
     * Add data provider to the generator.
     * The provider won't be run unless you call {@link #run()} after adding it.
     * @param provider the provider to add
     * @param <T> the type of the provider
     */
    public <T extends DataProvider> void addProvider(DataProviderFactory<T> provider) {
        this.providers.add(provider);
    }

    /**
     * Add registry-dependent data provider to the generator.
     * The provider won't be run unless you call {@link #run()} after adding it.
     * @param provider the provider to add
     * @param <T> the type of the provider
     */
    public <T extends DataProvider> void addProvider(RegistryDependentDataProviderFactory<T> provider) {
        this.registryDependentProviders.add(provider);
    }

    /**
     * Make all providers added to the generator. Just call it once in your mod's data generation entrypoint.
     */
    public void run() {
        if (this.hasRun) {
            throw new IllegalStateException("Cannot run data generation twice for the mod " + this.modId);
        }

        this.hasRun = true;

        this.providers.forEach(provider -> {
            this.pack.addProvider(output -> provider.create(this.modId, this.validate, output));
        });

        this.registryDependentProviders.forEach(provider -> {
            this.pack.addProvider(output -> provider.create(this.modId, this.validate, output, this.lookupProvider));
        });
    }
}
