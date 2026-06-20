package io.github.blockneko11.sunshinecore.networking.fabric;

import io.github.blockneko11.sunshinecore.networking.PayloadContext;
import io.github.blockneko11.sunshinecore.networking.PayloadReceiver;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ClientCommonPacketListener;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;

public final class SNetworkingImpl {
    public static <T extends CustomPacketPayload> void registerS2CType(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        PayloadTypeRegistry.playS2C().register(type, codec);
    }

    public static <T extends CustomPacketPayload> void registerC2SReceiver(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, PayloadReceiver<T> receiver) {
        registerS2CType(type, codec); // to be the same behavior as NeoForge's
        ServerPlayNetworking.registerGlobalReceiver(type, (payload, context) ->
                receiver.receive(payload, new PayloadContext.Server() {
                    @Override
                    public Player getPlayer() {
                        return context.player();
                    }

                    @Override
                    public void execute(Runnable runnable) {
                        context.server().execute(runnable);
                    }
                }));
    }

    public static <T extends CustomPacketPayload> Packet<ClientCommonPacketListener> toS2CPacket(T payload) {
        return ServerPlayNetworking.createS2CPacket(payload);
    }

    private SNetworkingImpl() {
    }
}
