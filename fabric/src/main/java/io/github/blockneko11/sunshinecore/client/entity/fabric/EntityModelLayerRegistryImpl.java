package io.github.blockneko11.sunshinecore.client.entity.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class EntityModelLayerRegistryImpl {
    public static void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        EntityModelLayerRegistry.registerModelLayer(location, definition::get);
    }

    private EntityModelLayerRegistryImpl() {
    }
}
