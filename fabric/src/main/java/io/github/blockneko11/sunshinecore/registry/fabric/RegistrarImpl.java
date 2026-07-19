package io.github.blockneko11.sunshinecore.registry.fabric;

import io.github.blockneko11.sunshinecore.registry.Registrar;
import io.github.blockneko11.sunshinecore.registry.RegistryHolder;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;

import java.util.function.Supplier;

public final class RegistrarImpl extends Registrar {
    private RegistrarImpl(String modId) {
        super(modId);
    }

    public static Registrar create(String modId) {
        return new RegistrarImpl(modId);
    }

    @Override
    public <R, T extends R> RegistryHolder<R, T> register(Registry<R> registry, String id, Supplier<T> entry) {
        ResourceKey<R> key = regKey(registry, id);
        T value = entry.get();
        Holder.Reference<R> holder = Registry.registerForHolder(registry, key, value);
        return new RegistryHolder<>(holder, registry, key);
    }

    @Override
    protected void bootstrap() {
    }
}
