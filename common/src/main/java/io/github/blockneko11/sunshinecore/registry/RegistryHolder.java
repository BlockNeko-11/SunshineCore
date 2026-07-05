package io.github.blockneko11.sunshinecore.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public record RegistryHolder<R, T extends R>(Supplier<T> delegate, ResourceKey<R> key, ResourceLocation id) implements Supplier<T> {
    @Override
    public T get() {
        return this.delegate.get();
    }
}
