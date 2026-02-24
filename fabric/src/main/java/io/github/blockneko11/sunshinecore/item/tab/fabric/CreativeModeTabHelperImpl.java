package io.github.blockneko11.sunshinecore.item.tab.fabric;

import io.github.blockneko11.sunshinecore.item.tab.CreativeModTabModifier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

public final class CreativeModeTabHelperImpl {
    public static CreativeModeTab.Builder createBuilder() {
        return FabricItemGroup.builder();
    }

    public static void modify(ResourceKey<CreativeModeTab> tab, CreativeModTabModifier modifier) {
        ItemGroupEvents.modifyEntriesEvent(tab).register(e -> {
            modifier.modify(e, e.shouldShowOpRestrictedItems());
        });
    }
}
