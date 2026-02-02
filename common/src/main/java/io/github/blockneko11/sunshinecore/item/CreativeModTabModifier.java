package io.github.blockneko11.sunshinecore.item;

import net.minecraft.world.item.CreativeModeTab;

@FunctionalInterface
public interface CreativeModTabModifier {
    // TODO: Use custom Output implementation
    void modify(CreativeModeTab.Output output, boolean isOP);
}
