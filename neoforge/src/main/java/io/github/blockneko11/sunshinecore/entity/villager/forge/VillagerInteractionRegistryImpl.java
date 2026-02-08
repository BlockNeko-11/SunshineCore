package io.github.blockneko11.sunshinecore.entity.villager.forge;

import io.github.blockneko11.sunshinecore.entity.villager.VillagerInteractionRegistry;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public final class VillagerInteractionRegistryImpl {
    private static final Map<VillagerProfession, List<TradeEntry>> TRADES = new HashMap<>();
    private static final List<Consumer<List<VillagerTrades.ItemListing>>> WANDERING_TRADE_GENERIC = new ArrayList<>();
    private static final List<Consumer<List<VillagerTrades.ItemListing>>> WANDERING_TRADE_RARE = new ArrayList<>();

    public static void registerTrade(VillagerProfession profession, VillagerInteractionRegistry.TradeLevel level, Consumer<List<VillagerTrades.ItemListing>> factory) {
        TRADES.computeIfAbsent(profession, k -> new ArrayList<>())
                .add(new TradeEntry(level, factory));
    }

    public static void registerTradeWandening(VillagerInteractionRegistry.WandeningTradeType type, Consumer<List<VillagerTrades.ItemListing>> factory) {
        switch (type) {
            case GENERIC -> WANDERING_TRADE_GENERIC.add(factory);
            case RARE -> WANDERING_TRADE_RARE.add(factory);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onVillagerTrade(VillagerTradesEvent e) {
        List<TradeEntry> entries = TRADES.get(e.getType());
        if (entries == null) {
            return;
        }

        Int2ObjectMap<List<VillagerTrades.ItemListing>> allTrades = e.getTrades();
        for (TradeEntry entry : entries) {
            List<VillagerTrades.ItemListing> trades = allTrades.computeIfAbsent(entry.level.getLevel(), k -> new ArrayList<>());
            entry.factory.accept(trades);
        }
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onWardeningTrade(WandererTradesEvent e) {
        for (Consumer<List<VillagerTrades.ItemListing>> factory : WANDERING_TRADE_GENERIC) {
            factory.accept(e.getGenericTrades());
        }

        for (Consumer<List<VillagerTrades.ItemListing>> factory : WANDERING_TRADE_RARE) {
            factory.accept(e.getRareTrades());
        }
    }

    private record TradeEntry(VillagerInteractionRegistry.TradeLevel level, Consumer<List<VillagerTrades.ItemListing>> factory) {
    }
}
