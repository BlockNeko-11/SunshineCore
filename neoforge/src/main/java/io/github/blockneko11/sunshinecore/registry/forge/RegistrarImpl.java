package io.github.blockneko11.sunshinecore.registry.forge;

import io.github.blockneko11.sunshinecore.registry.CreativeModeTabSupplier;
import io.github.blockneko11.sunshinecore.registry.Registrar;
import io.github.blockneko11.sunshinecore.util.forge.ModEventBuses;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

public class RegistrarImpl extends Registrar {
    private final Map<ResourceLocation, DeferredRegister<?>> registers = new LinkedHashMap<>();
    private final IEventBus modBus;

    private RegistrarImpl(String modId) {
        super(modId);
        this.modBus = ModEventBuses.get(modId);
    }

    public static Registrar create(String modId) {
        return new RegistrarImpl(modId);
    }

    @Override
    public <R, T extends R> Supplier<T> register(Registry<R> registry, String id, Supplier<T> entry) {
        ResourceLocation registryId = registry.key().location();
        DeferredRegister<R> register = (DeferredRegister<R>) this.registers.computeIfAbsent(
                registryId,
                key -> DeferredRegister.create(key, this.modId));

        return register.register(id, entry);
    }

    public CreativeModeTabSupplier registerTab(String id, Supplier<CreativeModeTab> tab) {
        ResourceLocation registryId = Registries.CREATIVE_MODE_TAB.location();
        DeferredRegister<CreativeModeTab> register = (DeferredRegister<CreativeModeTab>) this.registers.computeIfAbsent(
                registryId,
                key -> DeferredRegister.create(key, this.modId));

        DeferredHolder<CreativeModeTab, CreativeModeTab> holder = register.register(id, tab);
        return new CreativeModeTabSupplier(holder, holder.getKey());
    }

    @Override
    protected void bootstrap() {
        this.registers.values().forEach(r -> r.register(this.modBus));
    }
}
