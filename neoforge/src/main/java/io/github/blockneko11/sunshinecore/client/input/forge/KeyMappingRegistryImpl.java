package io.github.blockneko11.sunshinecore.client.input.forge;

import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;

import java.util.ArrayList;
import java.util.List;

@OnlyIn(Dist.CLIENT)
public final class KeyMappingRegistryImpl {
    private static final List<KeyMapping> KEY_MAPPINGS = new ArrayList<>();

    public static void register(KeyMapping mapping) {
        if (Minecraft.getInstance().options != null) {
            throw new IllegalStateException("GameOptions has already initialized");
        }

        KEY_MAPPINGS.add(mapping);
    }

    @SubscribeEvent
    public static void onRegister(RegisterKeyMappingsEvent e) {
        KEY_MAPPINGS.forEach(e::register);
    }

    private KeyMappingRegistryImpl() {
    }
}
