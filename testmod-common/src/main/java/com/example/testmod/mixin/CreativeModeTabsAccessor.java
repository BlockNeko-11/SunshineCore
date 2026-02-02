package com.example.testmod.mixin;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(CreativeModeTabs.class)
public interface CreativeModeTabsAccessor {
    @Accessor("BUILDING_BLOCKS")
    static ResourceKey<CreativeModeTab> getBuildingBlocks() {
        throw new AssertionError();
    }
}
