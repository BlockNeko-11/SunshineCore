package io.github.blockneko11.sunshinecore.entity.villager;

public enum TradeLevel {
    LEVEL_1(1),
    LEVEL_2(2),
    LEVEL_3(3),
    LEVEL_4(4),
    LEVEL_5(5);

    private final int level;

    TradeLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return this.level;
    }
}
