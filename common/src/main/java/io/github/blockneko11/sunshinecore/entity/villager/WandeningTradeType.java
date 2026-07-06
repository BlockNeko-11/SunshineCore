package io.github.blockneko11.sunshinecore.entity.villager;

public enum WandeningTradeType {
    GENERIC(1),
    RARE(2);

    private final int index;

    WandeningTradeType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return this.index;
    }
}
