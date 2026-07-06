package io.github.blockneko11.sunshinecore.entity.villager;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

import java.util.List;
import java.util.function.Consumer;

public final class VillagerTradeRegistry {
    @ExpectPlatform
    public static void register(VillagerProfession profession, TradeLevel level, Consumer<List<VillagerTrades.ItemListing>> factory) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void registerWandening(WandeningTradeType type, Consumer<List<VillagerTrades.ItemListing>> factory) {
        throw new AssertionError();
    }
}
