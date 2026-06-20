package io.github.blockneko11.sunshinecore.client.networking.forge;

import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.networking.PayloadContext;
import io.github.blockneko11.sunshinecore.networking.PayloadReceiver;
import io.github.blockneko11.sunshinecore.util.forge.EventBusUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.common.ServerCommonPacketListener;
import net.minecraft.network.protocol.common.ServerboundCustomPayloadPacket;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class SClientNetworkingImpl {
    public static <T extends CustomPacketPayload> void registerC2SType(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        EventBusUtil.get(SunshineCore.MOD_ID).<RegisterPayloadHandlersEvent>addListener(e -> {
            e.registrar(type.id().getNamespace())
                    .playToServer(type, codec, (payload, context) -> {});
        });
    }

    public static <T extends CustomPacketPayload> void registerS2CReceiver(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, PayloadReceiver<T> receiver) {
        EventBusUtil.get(SunshineCore.MOD_ID).<RegisterPayloadHandlersEvent>addListener(e ->
                e.registrar(type.id().getNamespace()).playToServer(type, codec, (payload, context) ->
                        receiver.receive(payload, new PayloadContext.Client() {
                            @Override
                            public Player getPlayer() {
                                return context.player();
                            }

                            @Override
                            public void execute(Runnable runnable) {
                                context.enqueueWork(runnable);
                            }
                        })));
    }

    public static <T extends CustomPacketPayload> Packet<ServerCommonPacketListener> toC2SPacket(T payload) {
        return new ServerboundCustomPayloadPacket(payload);
    }

    private SClientNetworkingImpl() {
    }
}
