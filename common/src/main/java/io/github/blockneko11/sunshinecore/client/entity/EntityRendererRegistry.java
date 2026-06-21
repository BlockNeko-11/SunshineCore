package io.github.blockneko11.sunshinecore.client.entity;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;

@Environment(EnvType.CLIENT)
public final class EntityRendererRegistry {
    @ExpectPlatform
    public static <T extends Entity> void register(EntityType<? extends T> type, EntityRendererProvider<T> provider) {
        throw new AssertionError();
    }

    private EntityRendererRegistry() {
    }
}
