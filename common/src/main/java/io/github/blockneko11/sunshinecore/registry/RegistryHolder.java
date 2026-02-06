package io.github.blockneko11.sunshinecore.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class RegistryHolder<R, T extends R> implements Supplier<T> {
    private final Supplier<T> delegate;
    private final ResourceKey<R> key;
    private final ResourceLocation identifier;

    public RegistryHolder(Supplier<T> delegate, ResourceKey<R> key, ResourceLocation identifier) {
        this.delegate = delegate;
        this.key = key;
        this.identifier = identifier;
    }

    @Override
    public T get() {
        return this.delegate.get();
    }

    public ResourceKey<R> getKey() {
        return this.key;
    }

    public ResourceLocation getIdentifier() {
        return this.identifier;
    }
}
