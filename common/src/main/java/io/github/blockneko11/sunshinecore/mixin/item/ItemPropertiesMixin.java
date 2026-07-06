package io.github.blockneko11.sunshinecore.mixin.item;

import io.github.blockneko11.sunshinecore.item.extension.ItemPropertiesExtension;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.function.Supplier;

@Mixin(Item.Properties.class)
public abstract class ItemPropertiesMixin implements ItemPropertiesExtension {
    @Unique
    @Nullable
    private Supplier<CreativeModeTab> sc$tabSupplier;

    @Unique
    @Nullable
    private CreativeModeTab sc$tab;

    @Unique
    @Nullable
    private ResourceKey<CreativeModeTab> sc$tabKey;

    @Unique
    private int sc$burnTick = 0;

    @Unique
    private float sc$compostableChance = 0.0f;

    @Unique
    private void sc$resetTabs() {
        this.sc$tabSupplier = null;
        this.sc$tab = null;
        this.sc$tabKey = null;
    }

    @Override
    public Item.Properties sc$tab(Supplier<CreativeModeTab> tab) {
        this.sc$resetTabs();
        this.sc$tabSupplier = tab;
        return sc$self();
    }

    @Override
    public Supplier<CreativeModeTab> sc$getTabSupplier() {
        return this.sc$tabSupplier;
    }

    @Override
    public Item.Properties sc$tab(CreativeModeTab tab) {
        this.sc$resetTabs();
        this.sc$tab = tab;
        return sc$self();
    }

    @Override
    public CreativeModeTab sc$getTab() {
        return this.sc$tab;
    }

    @Override
    public Item.Properties sc$tab(ResourceKey<CreativeModeTab> tab) {
        this.sc$resetTabs();
        this.sc$tabKey = tab;
        return sc$self();
    }

    @Override
    public ResourceKey<CreativeModeTab> sc$getTabKey() {
        return this.sc$tabKey;
    }

    @Override
    public Item.Properties sc$fuel(int burnTick) {
        this.sc$burnTick = burnTick;
        return sc$self();
    }

    @Override
    public int sc$getBurnTick() {
        return this.sc$burnTick;
    }

    @Override
    public Item.Properties sc$compostable(float chance) {
        this.sc$compostableChance = chance;
        return sc$self();
    }

    @Override
    public float sc$getCompostingChance() {
        return this.sc$compostableChance;
    }
}
