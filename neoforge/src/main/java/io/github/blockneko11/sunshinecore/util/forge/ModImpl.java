package io.github.blockneko11.sunshinecore.util.forge;

import io.github.blockneko11.sunshinecore.util.Mod;
import net.neoforged.fml.ModContainer;

class ModImpl implements Mod {
    private final ModContainer delegate;

    public ModImpl(ModContainer delegate) {
        this.delegate = delegate;
    }

    @Override
    public String getId() {
        return this.delegate.getModId();
    }

    @Override
    public String getName() {
        return this.delegate.getModInfo().getDisplayName();
    }

    @Override
    public String getVersion() {
        return this.delegate.getModInfo().getVersion().toString();
    }

    @Override
    public String getDescription() {
        return this.delegate.getModInfo().getDescription();
    }
}
