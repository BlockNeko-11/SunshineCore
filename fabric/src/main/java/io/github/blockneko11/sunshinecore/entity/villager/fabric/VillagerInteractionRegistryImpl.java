package io.github.blockneko11.sunshinecore.entity.villager.fabric;

import io.github.blockneko11.sunshinecore.entity.villager.VillagerInteractionRegistry;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;

import java.util.List;
import java.util.function.Consumer;

public final class VillagerInteractionRegistryImpl {
    public static void registerTrade(VillagerProfession profession, VillagerInteractionRegistry.TradeLevel level, Consumer<List<VillagerTrades.ItemListing>> factory) {
        TradeOfferHelper.registerVillagerOffers(profession, level.getLevel(), factory);
    }

    public static void registerTradeWandening(VillagerInteractionRegistry.WandeningTradeType type, Consumer<List<VillagerTrades.ItemListing>> factory) {
        TradeOfferHelper.registerWanderingTraderOffers(type.getIndex(), factory);
    }
}
