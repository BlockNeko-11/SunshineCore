package io.github.blockneko11.sunshinecore.item.extension;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.ApiStatus;

import java.util.function.Supplier;

public interface ItemPropertiesExtension {
    default Item.Properties sc$self() {
        return (Item.Properties) this;
    }

    default Item.Properties sc$tab(Supplier<CreativeModeTab> tab) {
        throw new AssertionError();
    }

    @ApiStatus.Internal
    default Supplier<CreativeModeTab> sc$getTabSupplier() {
        throw new AssertionError();
    }

    default Item.Properties sc$tab(CreativeModeTab tab) {
        throw new AssertionError();
    }

    @ApiStatus.Internal
    default CreativeModeTab sc$getTab() {
        throw new AssertionError();
    }

    default Item.Properties sc$tab(ResourceKey<CreativeModeTab> tab) {
        throw new AssertionError();
    }

    @ApiStatus.Internal
    default ResourceKey<CreativeModeTab> sc$getTabKey() {
        throw new AssertionError();
    }

    default Item.Properties sc$fuel(int burnTick) {
        throw new AssertionError();
    }

    @ApiStatus.Internal
    default int sc$getBurnTick() {
        throw new AssertionError();
    }

    default Item.Properties sc$compostable(float chance) {
        throw new AssertionError();
    }

    @ApiStatus.Internal
    default float sc$getCompostingChance() {
        throw new AssertionError();
    }
}
