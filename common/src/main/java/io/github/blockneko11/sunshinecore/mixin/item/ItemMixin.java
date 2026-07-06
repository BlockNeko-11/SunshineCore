package io.github.blockneko11.sunshinecore.mixin.item;

import io.github.blockneko11.sunshinecore.item.CompostingRegistry;
import io.github.blockneko11.sunshinecore.item.FuelRegistry;
import io.github.blockneko11.sunshinecore.item.extension.ItemExtension;
import io.github.blockneko11.sunshinecore.item.tab.CreativeModeTabUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(Item.class)
public abstract class ItemMixin implements ItemExtension {
    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void sc$onItemCtor(Item.Properties props, CallbackInfo ci) {
        Supplier<CreativeModeTab> tabSupplier = props.sc$getTabSupplier();
        if (tabSupplier != null) {
            CreativeModeTabUtils.append(tabSupplier, sc$self());
        }

        CreativeModeTab tab = props.sc$getTab();
        if (tab != null) {
            CreativeModeTabUtils.append(tab, sc$self());
        }

        ResourceKey<CreativeModeTab> tabKey = props.sc$getTabKey();
        if (tabKey != null) {
            CreativeModeTabUtils.append(tabKey, sc$self());
        }

        int burnTick = props.sc$getBurnTick();
        if (burnTick > 0) {
            FuelRegistry.register(burnTick, sc$self());
        }

        float compostableChance = props.sc$getCompostingChance();
        if (compostableChance > 0.0f) {
            CompostingRegistry.register(compostableChance, sc$self());
        }
    }
}
