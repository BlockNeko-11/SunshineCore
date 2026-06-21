package io.github.blockneko11.sunshinecore.networking;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ClientCommonPacketListener;
import net.minecraft.network.protocol.common.ClientboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.level.ServerPlayer;

public final class SNetworking {
    @ExpectPlatform
    public static <T extends CustomPacketPayload> void registerS2CType(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends CustomPacketPayload> void registerC2SReceiver(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, PayloadReceiver<T> receiver) {
        throw new AssertionError();
    }

    public static <T extends CustomPacketPayload> Packet<ClientCommonPacketListener> toS2CPacket(T payload) {
        return new ClientboundCustomPayloadPacket(payload);
    }

    public static <T extends CustomPacketPayload> void sendToPlayer(ServerPlayer player, T payload) {
        player.connection.send(toS2CPacket(payload));
    }

    public static <T extends CustomPacketPayload> void sendToPlayers(Iterable<ServerPlayer> players, T payload) {
        for (ServerPlayer player : players) {
            sendToPlayer(player, payload);
        }
    }

    private SNetworking() {
    }
}
