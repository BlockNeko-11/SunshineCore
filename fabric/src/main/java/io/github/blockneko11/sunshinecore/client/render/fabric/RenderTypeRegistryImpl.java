package io.github.blockneko11.sunshinecore.client.render.fabric;

import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;

public final class RenderTypeRegistryImpl {
    public static void register(RenderType type, Block... blocks) {
        BlockRenderLayerMap.INSTANCE.putBlocks(type, blocks);
    }

    public static void register(RenderType type, Fluid... fluids) {
        BlockRenderLayerMap.INSTANCE.putFluids(type, fluids);
    }
}
