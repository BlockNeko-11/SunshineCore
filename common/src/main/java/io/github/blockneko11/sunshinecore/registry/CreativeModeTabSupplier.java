package io.github.blockneko11.sunshinecore.registry;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import java.util.function.Supplier;

public class CreativeModeTabSupplier implements Supplier<CreativeModeTab> {
    private final Supplier<CreativeModeTab> delegate;
    private final ResourceKey<CreativeModeTab> key;

    public CreativeModeTabSupplier(Supplier<CreativeModeTab> delegate, ResourceKey<CreativeModeTab> key) {
        this.delegate = delegate;
        this.key = key;
    }

    @Override
    public CreativeModeTab get() {
        return this.delegate.get();
    }

    public ResourceKey<CreativeModeTab> getKey() {
        return this.key;
    }
}
