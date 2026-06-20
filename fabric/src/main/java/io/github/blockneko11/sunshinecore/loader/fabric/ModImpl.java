package io.github.blockneko11.sunshinecore.loader.fabric;

import io.github.blockneko11.sunshinecore.loader.Mod;
import net.fabricmc.loader.api.ModContainer;

final class ModImpl implements Mod {
    private final ModContainer delegate;

    ModImpl(ModContainer delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return this.delegate.getMetadata().getId();
    }

    @Override
    public String getName() {
        return this.delegate.getMetadata().getName();
    }

    @Override
    public String getVersion() {
        return this.delegate.getMetadata().getVersion().getFriendlyString();
    }

    @Override
    public String getDescription() {
        return this.delegate.getMetadata().getDescription();
    }
}
