package io.github.blockneko11.sunshinecore.client.entity;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public final class EntityModelLayerRegistry {
    @ExpectPlatform
    public static void register(ModelLayerLocation location, Supplier<LayerDefinition> definition) {
        throw new AssertionError();
    }

    private EntityModelLayerRegistry() {
    }
}
