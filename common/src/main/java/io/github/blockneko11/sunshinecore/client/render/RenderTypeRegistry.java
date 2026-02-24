package io.github.blockneko11.sunshinecore.client.render;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

@Environment(EnvType.CLIENT)
public final class RenderTypeRegistry {
    @ExpectPlatform
    public static void register(RenderType type, Block... blocks) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static void register(RenderType type, Fluid... fluids) {
        throw new AssertionError();
    }

    private RenderTypeRegistry() {
    }
}
