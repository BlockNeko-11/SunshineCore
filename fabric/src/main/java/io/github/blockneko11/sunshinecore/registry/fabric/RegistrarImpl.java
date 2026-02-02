package io.github.blockneko11.sunshinecore.registry.fabric;

import io.github.blockneko11.sunshinecore.registry.CreativeModeTabSupplier;
import io.github.blockneko11.sunshinecore.registry.Registrar;
import io.github.blockneko11.sunshinecore.universal.SIdentifier;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

public class RegistrarImpl extends Registrar {
    private RegistrarImpl(String modId) {
        super(modId);
    }

    public static Registrar create(String modId) {
        return new RegistrarImpl(modId);
    }

    @Override
    public <R, T extends R> Supplier<T> register(Registry<R> registry, String id, Supplier<T> entry) {
        T registered = Registry.register(registry, SIdentifier.id(this.modId, id), entry.get());
        return () -> registered;
    }

    @Override
    public CreativeModeTabSupplier registerTab(String id, Supplier<CreativeModeTab> tab) {
        ResourceKey<CreativeModeTab> key = ResourceKey.create(Registries.CREATIVE_MODE_TAB, SIdentifier.id(this.modId, id));
        Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, key, tab.get());
        return new CreativeModeTabSupplier(tab, key);
    }

    @Override
    protected void bootstrap() {
    }
}
