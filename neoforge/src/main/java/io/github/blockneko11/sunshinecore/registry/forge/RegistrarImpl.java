package io.github.blockneko11.sunshinecore.registry.forge;

import io.github.blockneko11.sunshinecore.registry.Registrar;
import io.github.blockneko11.sunshinecore.registry.RegistryHolder;
import io.github.blockneko11.sunshinecore.util.forge.EventBusUtils;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public final class RegistrarImpl extends Registrar {
    private final Map<ResourceLocation, DeferredRegister<?>> registers = new LinkedHashMap<>();
    private final IEventBus modBus;

    private RegistrarImpl(String modId) {
        super(modId);
        this.modBus = EventBusUtils.get(modId);
    }

    public static Registrar create(String modId) {
        return new RegistrarImpl(modId);
    }

    @Override
    public <R, T extends R> RegistryHolder<R, T> register(Registry<R> registry, String id, Supplier<T> entry) {
        // to be compactible with Forge :(
        ResourceLocation registryId = registry.key().location();
        DeferredRegister<R> register = (DeferredRegister<R>) this.registers.computeIfAbsent(
                registryId,
                key -> DeferredRegister.create(key, this.modId));

        DeferredHolder<R, T> holder = register.register(id, entry);
        return new RegistryHolder<>(holder, holder.getKey(), holder.getId());
    }

    @Override
    protected void bootstrap() {
        this.registers.values().forEach(r -> r.register(this.modBus));
    }
}
