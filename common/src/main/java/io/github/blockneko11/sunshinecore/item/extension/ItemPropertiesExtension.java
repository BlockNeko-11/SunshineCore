package io.github.blockneko11.sunshinecore.item.extension;

import io.github.blockneko11.sunshinecore.registry.RegistryHolder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public interface ItemPropertiesExtension {
    default Item.Properties sc$self() {
        return (Item.Properties) this;
    }

    @ApiStatus.Experimental
    default Item.Properties sc$tab(Supplier<CreativeModeTab> tab) {
        return sc$self();
    }

    @ApiStatus.Internal
    @Nullable
    default Supplier<CreativeModeTab> sc$getTabSupplier() {
        return null;
    }

    @ApiStatus.Experimental
    default Item.Properties sc$tab(CreativeModeTab tab) {
        return sc$self();
    }

    @ApiStatus.Internal
    @Nullable
    default CreativeModeTab sc$getTab() {
        return null;
    }

    default Item.Properties sc$tab(RegistryHolder<CreativeModeTab, CreativeModeTab> tab) {
        return this.sc$tab(tab.key());
    }

    default Item.Properties sc$tab(ResourceKey<CreativeModeTab> tab) {
        return sc$self();
    }

    @ApiStatus.Internal
    @Nullable
    default ResourceKey<CreativeModeTab> sc$getTabKey() {
        return null;
    }

    default Item.Properties sc$fuel(int burnTick) {
        return sc$self();
    }

    @ApiStatus.Internal
    default int sc$getBurnTick() {
        return 0;
    }

    default Item.Properties sc$compostable(float chance) {
        return sc$self();
    }

    @ApiStatus.Internal
    default float sc$getCompostingChance() {
        return 0.0f;
    }
}
