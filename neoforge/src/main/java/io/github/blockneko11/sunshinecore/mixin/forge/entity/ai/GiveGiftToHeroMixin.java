package io.github.blockneko11.sunshinecore.mixin.forge.entity.ai;

import io.github.blockneko11.sunshinecore.entity.villager.forge.VillagerInteractionRegistryImpl;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.behavior.GiveGiftToHero;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(GiveGiftToHero.class)
public abstract class GiveGiftToHeroMixin {
    @Inject(
            method = "getItemToThrow",
            at = @At("TAIL"),
            cancellable = true
    )
    private void sc$getItemToThrow(Villager villager, CallbackInfoReturnable<List<ItemStack>> cir) {
        ResourceKey<LootTable> lootKey = VillagerInteractionRegistryImpl.GIFT_LOOT_TABLES.get(villager.getVillagerData().getProfession());

        if (lootKey == null) {
            return;
        }

        LootTable lootTable = villager.level().getServer().reloadableRegistries().getLootTable(lootKey);
        LootParams lootParams = (new LootParams.Builder((ServerLevel)villager.level())).withParameter(LootContextParams.ORIGIN, villager.position()).withParameter(LootContextParams.THIS_ENTITY, villager).create(LootContextParamSets.GIFT);
        cir.setReturnValue(lootTable.getRandomItems(lootParams));
        cir.cancel();
    }
}
