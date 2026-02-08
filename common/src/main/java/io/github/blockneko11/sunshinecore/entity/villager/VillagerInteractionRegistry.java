package io.github.blockneko11.sunshinecore.entity.villager;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

import java.util.List;
import java.util.function.Consumer;

public final class VillagerInteractionRegistry {
    @ExpectPlatform
    public static void registerTrade(VillagerProfession profession, TradeLevel level, Consumer<List<VillagerTrades.ItemListing>> factory) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerTradeWandening(WandeningTradeType type, Consumer<List<VillagerTrades.ItemListing>> factory) {
        throw new AssertionError();
    }

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
}
