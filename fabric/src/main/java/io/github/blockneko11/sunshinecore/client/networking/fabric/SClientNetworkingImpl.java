package io.github.blockneko11.sunshinecore.client.networking.fabric;

import io.github.blockneko11.sunshinecore.networking.PayloadContext;
import io.github.blockneko11.sunshinecore.networking.PayloadReceiver;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerCommonPacketListener;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

public final class SClientNetworkingImpl {
    public static <T extends CustomPacketPayload> void registerC2SType(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        PayloadTypeRegistry.playC2S().register(type, codec);
    }

    public static <T extends CustomPacketPayload> void registerS2CReceiver(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, PayloadReceiver<T> receiver) {
        registerC2SType(type, codec);  // to be the same behavior as NeoForge's
        ClientPlayNetworking.registerGlobalReceiver(type, (payload, context) ->
                receiver.receive(payload, new PayloadContext.Client() {
                    @Override
                    public Player getPlayer() {
                        return context.player();
                    }

                    @Override
                    public void execute(Runnable runnable) {
                        context.client().execute(runnable);
                    }
                }));
    }

    public static <T extends CustomPacketPayload> Packet<ServerCommonPacketListener> toC2SPacket(T payload) {
        return ClientPlayNetworking.createC2SPacket(payload);
    }

    private SClientNetworkingImpl() {
    }
}
