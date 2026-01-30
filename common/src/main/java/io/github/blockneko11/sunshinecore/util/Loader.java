package io.github.blockneko11.sunshinecore.util;

public enum Loader {
    FABRIC,
    FORGE,
    NEOFORGE;

    public boolean isForgeLike() {
        return this == FORGE || this == NEOFORGE;
    }
}
