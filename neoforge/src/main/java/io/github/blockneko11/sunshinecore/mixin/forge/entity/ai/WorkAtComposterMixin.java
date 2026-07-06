package io.github.blockneko11.sunshinecore.mixin.forge.entity.ai;

import com.llamalad7.mixinextras.sugar.Local;
import io.github.blockneko11.sunshinecore.entity.villager.forge.VillagerInteractionRegistryImpl;
import net.minecraft.world.entity.ai.behavior.WorkAtComposter;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(WorkAtComposter.class)
public abstract class WorkAtComposterMixin {
    @Unique
    private static final Compostable sc$dummy = new Compostable(0.0f, true);

    @ModifyVariable(
            method = "compostItems",
            at = @At("STORE"),
            name = "compostable"
    )
    private Compostable sc$composItems(Compostable compostable, @Local(name = "itemstack") ItemStack itemstack) {
        if (compostable != null) {
            return compostable;
        }

        if (VillagerInteractionRegistryImpl.COMPOSTABLE_ITEMS.contains(itemstack.getItem())) {
            return sc$dummy;
        }

        return null;
    }
}
