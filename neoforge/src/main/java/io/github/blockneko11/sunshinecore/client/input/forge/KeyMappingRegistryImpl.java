package io.github.blockneko11.sunshinecore.client.input.forge;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import java.util.ArrayList;
import java.util.List;

public class KeyMappingRegistryImpl {
    private static final List<KeyMapping> KEY_MAPPINGS = new ArrayList<>();

    public static void register(KeyMapping mapping) {
        if (Minecraft.getInstance().options != null) {
            throw new IllegalStateException("GameOptions has already been initialised");
        }

        KEY_MAPPINGS.add(mapping);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent e) {
        KEY_MAPPINGS.forEach(e::register);
    }
}
