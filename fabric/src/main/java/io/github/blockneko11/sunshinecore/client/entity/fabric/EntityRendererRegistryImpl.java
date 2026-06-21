package io.github.blockneko11.sunshinecore.client.entity.fabric;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

@Environment(EnvType.CLIENT)
public final class EntityRendererRegistryImpl {
    public static <T extends Entity> void register(EntityType<? extends T> type, EntityRendererProvider<T> provider) {
        EntityRendererRegistry.register(type, provider);
    }

    private EntityRendererRegistryImpl() {
    }
}
