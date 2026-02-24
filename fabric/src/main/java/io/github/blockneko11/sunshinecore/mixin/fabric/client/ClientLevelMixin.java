package io.github.blockneko11.sunshinecore.mixin.fabric.client;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.client.event.level.ClientLevelEvent;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceKey;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Supplier;

@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin {
    @Inject(
            method = "<init>",
            at = @At("RETURN")
    )
    private void onConstruct$clientLevelLoad(ClientPacketListener connection, ClientLevel.ClientLevelData clientLevelData, ResourceKey dimension, Holder dimensionType, int viewDistance, int serverSimulationDistance, Supplier profiler, LevelRenderer levelRenderer, boolean isDebug, long biomeZoomSeed, CallbackInfo ci) {
        SunshineCore.BUS.post(new ClientLevelEvent.Load((ClientLevel) (Object) this));
    }
}
