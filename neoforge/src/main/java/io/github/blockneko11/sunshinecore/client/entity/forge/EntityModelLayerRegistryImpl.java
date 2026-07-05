package io.github.blockneko11.sunshinecore.client.entity.forge;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

@OnlyIn(Dist.CLIENT)
public final class EntityModelLayerRegistryImpl {
    private static final Map<ModelLayerLocation, Supplier<LayerDefinition>> DEFINITIONS = new HashMap<>();

    public static void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        DEFINITIONS.put(location, definition);
    }

    @SubscribeEvent
    public static void onRegister(EntityRenderersEvent.RegisterLayerDefinitions e) {
        DEFINITIONS.forEach((location, definition) -> {
            e.registerLayerDefinition(location, definition);
        });
    }

    private EntityModelLayerRegistryImpl() {
    }
}
