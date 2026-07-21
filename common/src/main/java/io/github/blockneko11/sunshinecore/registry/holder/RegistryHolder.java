package io.github.blockneko11.sunshinecore.registry.holder;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/**
 * A registry holder, which is used to hold a registry entry.
 * @param <R> the type of the registry
 * @param <T> the type of the registry entry
 */
@SuppressWarnings("unchecked")
public class RegistryHolder<R, T extends R> implements Supplier<T> {
    private final Holder<R> holder;
    private final Registry<R> registry;
    private final ResourceKey<R> key;

    public RegistryHolder(Holder<R> holder, Registry<R> registry, ResourceKey<R> key) {
        this.holder = holder;
        this.registry = registry;
        this.key = key;
    }

    protected RegistryHolder(RegistryHolder<R, T> delegate) {
        this(delegate.holder, delegate.registry, delegate.key);
    }

    /**
     * Get the {@link Holder} instance of the registry entry.
     * @return a {@link Holder} instance
     */
    public Holder<R> holder() {
        return this.holder;
    }

    /**
     * Get the {@link Registry} which the registry entry has been registered with.
     * @return a {@link Registry} instance
     */
    public Registry<R> registry() {
        return this.registry;
    }

    /**
     * Get the {@link ResourceKey} of the registry entry.
     * @return a {@link ResourceKey} instance
     */
    public ResourceKey<R> key() {
        return this.key;
    }

    /**
     * Get the in-game id of the registry entry.
     * @return the in-game id
     */
    public ResourceLocation id() {
        return this.key.location();
    }

    /**
     * Get the registry entry. Equivalent to {@link Holder#value()}.
     * @return the registry entry
     */
    @Override
    public T get() {
        return (T) this.holder.value();
    }
}
