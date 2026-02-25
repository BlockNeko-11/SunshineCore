package io.github.blockneko11.sunshinecore.registry.fabric;

import io.github.blockneko11.sunshinecore.registry.Registrar;
import io.github.blockneko11.sunshinecore.registry.RegistryHolder;
import io.github.blockneko11.sunshinecore.universal.IdentifierUtil;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;

import java.util.function.Supplier;

public class RegistrarImpl extends Registrar {
    private RegistrarImpl(String modId) {
        super(modId);
    }

    public static Registrar create(String modId) {
        return new RegistrarImpl(modId);
    }

    @Override
    public <R, T extends R> RegistryHolder<R, T> register(Registry<R> registry, String id, Supplier<T> entry) {
        ResourceLocation identifier = IdentifierUtil.id(this.modId, id);
        ResourceKey<R> key = ResourceKey.create(registry.key(), identifier);
        T registered = Registry.register(registry, key, entry.get());
        return new RegistryHolder<>(() -> registered, key, identifier);
    }

    @Override
    protected void bootstrap() {
    }
}
