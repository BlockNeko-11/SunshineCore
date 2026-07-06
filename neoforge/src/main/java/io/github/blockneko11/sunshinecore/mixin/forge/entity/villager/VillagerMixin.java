package io.github.blockneko11.sunshinecore.mixin.forge.entity.villager;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.blockneko11.sunshinecore.entity.villager.forge.VillagerInteractionRegistryImpl;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(Villager.class)
public abstract class VillagerMixin {
    @Inject(
            method = "wantsToPickUp",
            at = @At("TAIL"),
            cancellable = true
    )
    private void sc$wantsToPickUp(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        Item item = stack.getItem();
        boolean registered = VillagerInteractionRegistryImpl.WANTED_ITEMS.contains(item);
        if (!registered) {
            return;
        }

        boolean bl = ((Villager) (Object) this).getInventory().canAddItem(stack);
        cir.setReturnValue(bl && registered);
        cir.cancel();
    }

    @Inject(
            method = "countFoodPointsInInventory",
            at = @At("TAIL"),
            cancellable = true
    )
    private void sc$countFoodPointsInInventory(CallbackInfoReturnable<Integer> cir, @Local(name = "simplecontainer") SimpleContainer simplecontainer) {
        int baseSum = cir.getReturnValueI();
        int registeredSum = VillagerInteractionRegistryImpl.FOODS.entrySet()
                .stream()
                .mapToInt(e -> simplecontainer.countItem(e.getKey()) * e.getValue())
                .sum();

        cir.setReturnValue(baseSum + registeredSum);
        cir.cancel();
    }

    @Redirect(
            method = "eatUntilFull",
            at = @At(
                    value = "INVOKE",
                    target = "Ljava/util/Map;get(Ljava/lang/Object;)Ljava/lang/Object;"
            )
    )
    private <V> V sc$eatUntilFull(Map<Item, Integer> instance, Object o) {
        if (instance.containsKey(o)) {
            return (V) instance.get(o);
        }

        if (!VillagerInteractionRegistryImpl.FOODS.containsKey(o)) {
            return null;
        }

        return (V) VillagerInteractionRegistryImpl.FOODS.get(o);
    }
}
