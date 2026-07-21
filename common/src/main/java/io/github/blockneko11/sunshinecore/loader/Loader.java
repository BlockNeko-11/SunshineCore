package io.github.blockneko11.sunshinecore.loader;

public enum Loader {
    FABRIC,
    FORGE,
    NEOFORGE;

    public boolean isFabric() {
        return this == FABRIC;
    }

    public boolean isForgeLike() {
        return this == FORGE || this == NEOFORGE;
    }
}
