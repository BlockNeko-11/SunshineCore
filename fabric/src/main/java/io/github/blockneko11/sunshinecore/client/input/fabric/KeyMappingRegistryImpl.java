package io.github.blockneko11.sunshinecore.client.input.fabric;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;

public class KeyMappingRegistryImpl {
    public static void register(KeyMapping mapping) {
        KeyBindingHelper.registerKeyBinding(mapping);
    }
}
