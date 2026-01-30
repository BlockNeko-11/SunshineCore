package io.github.blockneko11.sunshinecore.registry.fabric;

import io.github.blockneko11.sunshinecore.registry.Registrar;
import io.github.blockneko11.sunshinecore.universal.SIdentifier;
import net.minecraft.core.Registry;

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
        Registry.register(registry, SIdentifier.id(this.modId, id), entry.get());
        return entry;
    }

    @Override
    protected void bootstrap() {
    }
}
