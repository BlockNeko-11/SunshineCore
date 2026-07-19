package io.github.blockneko11.sunshinecore.registry;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

@SuppressWarnings("unchecked")
public record RegistryHolder<R, T extends R>(Holder<R> holder, Registry<R> registry, ResourceKey<R> key) implements Supplier<T> {
    @Override
    public T get() {
        return (T) this.holder.value();
    }

    public ResourceLocation id() {
        return this.key.location();
    }
}
