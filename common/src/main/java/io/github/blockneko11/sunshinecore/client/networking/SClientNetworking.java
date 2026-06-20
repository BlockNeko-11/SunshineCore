package io.github.blockneko11.sunshinecore.client.networking;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.blockneko11.sunshinecore.networking.PayloadReceiver;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerCommonPacketListener;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

import java.util.Objects;

@Environment(EnvType.CLIENT)
public final class SClientNetworking {
    @ExpectPlatform
    public static <T extends CustomPacketPayload> void registerC2SType(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends CustomPacketPayload> void registerS2CReceiver(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, PayloadReceiver<T> receiver) {
        throw new AssertionError();
    }

    @ExpectPlatform
    public static <T extends CustomPacketPayload> Packet<ServerCommonPacketListener> toC2SPacket(T payload) {
        throw new AssertionError();
    }

    public static <T extends CustomPacketPayload> void sendToServer(T payload) {
        Objects.requireNonNull(Minecraft.getInstance().getConnection(), "Not in a server game").send(toC2SPacket(payload));
    }

    private SClientNetworking() {
    }
}
