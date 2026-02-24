package io.github.blockneko11.sunshinecore.client.block.entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

@Environment(EnvType.CLIENT)
public final class BlockEntityRendererRegistry {
    public static <T extends BlockEntity> void register(BlockEntityType<T> type, BlockEntityRendererProvider<? super T> provider) {
        BlockEntityRenderers.register(type, provider);
    }

    private BlockEntityRendererRegistry() {
    }
}
