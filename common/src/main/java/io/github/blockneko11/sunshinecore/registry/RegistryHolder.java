package io.github.blockneko11.sunshinecore.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

/**
 * A registry holder, which is used to hold a registry entry.
 * @param holder a {@link Holder} of the registry entry
 * @param registry a {@link Registry} instance. See {@link BuiltInRegistries}
 * @param key a {@link ResourceKey} of the registry entry
 * @param <R> the type of the registry
 * @param <T> the type of the registry entry
 */
@SuppressWarnings("unchecked")
public record RegistryHolder<R, T extends R>(Holder<R> holder, Registry<R> registry, ResourceKey<R> key) implements Supplier<T> {
    /**
     * Get the registry entry. Equivalent to {@link Holder#value()}.
     * @return the registry entry
     */
    @Override
    public T get() {
        return (T) this.holder.value();
    }

    /**
     * Get the in-game id of the registry entry.
     * @return the in-game id
     */
    public ResourceLocation id() {
        return this.key.location();
    }
}
