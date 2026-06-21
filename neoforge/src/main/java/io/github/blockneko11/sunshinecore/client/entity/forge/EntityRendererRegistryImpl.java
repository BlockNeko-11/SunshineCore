package io.github.blockneko11.sunshinecore.client.entity.forge;

import io.github.blockneko11.sunshinecore.util.forge.EventBusUtils;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import java.util.HashMap;
import java.util.Map;

@OnlyIn(Dist.CLIENT)
public final class EntityRendererRegistryImpl {
    private static final Map<EntityType<?>, EntityRendererProvider<?>> PROVIDERS = new HashMap<>();

    public static <T extends Entity> void register(EntityType<? extends T> type, EntityRendererProvider<T> provider) {
        PROVIDERS.put(type, provider);
    }

    static {
        EventBusUtils.SC().addListener(EntityRendererRegistryImpl::onRegister);
    }

    private static void onRegister(EntityRenderersEvent.RegisterRenderers e) {
        PROVIDERS.forEach((type, provider) -> {
            e.registerEntityRenderer((EntityType) type, provider);
        });
    }

    private EntityRendererRegistryImpl() {
    }
}
