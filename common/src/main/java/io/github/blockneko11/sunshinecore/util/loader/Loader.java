package io.github.blockneko11.sunshinecore.util.loader;

public enum Loader {
    FABRIC,
    FORGE,
    NEOFORGE;

    public boolean isForgeLike() {
        return this == FORGE || this == NEOFORGE;
    }
}
