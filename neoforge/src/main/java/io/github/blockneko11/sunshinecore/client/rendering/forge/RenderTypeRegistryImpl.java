package io.github.blockneko11.sunshinecore.client.rendering.forge;

import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class RenderTypeRegistryImpl {
    public static void register(RenderType type, Block... blocks) {
        for (Block block : blocks) {
            ItemBlockRenderTypes.setRenderLayer(block, type);
        }
    }

    public static void register(RenderType type, Fluid... fluids) {
        for (Fluid fluid : fluids) {
            ItemBlockRenderTypes.setRenderLayer(fluid, type);
        }
    }

    private RenderTypeRegistryImpl() {
    }
}
