package io.github.blockneko11.sunshinecore.entity.villager;

import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;

import java.util.Optional;

public record SimpleVillagerTrade(ItemCost baseCost, Optional<ItemCost> additionalCost, ItemStack result, int maxUses, int xp, float priceMultiplier) implements VillagerTrades.ItemListing {
    public SimpleVillagerTrade(ItemCost cost, ItemStack result, int maxUses, int xp, float priceMultiplier) {
        this(cost, Optional.empty(), result, maxUses, xp, priceMultiplier);
    }

    @Override
    public MerchantOffer getOffer(Entity trader, RandomSource random) {
        return new MerchantOffer(this.baseCost, this.additionalCost, this.result, this.maxUses, this.xp, this.priceMultiplier);
    }
}
