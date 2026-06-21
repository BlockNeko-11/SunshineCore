package io.github.blockneko11.sunshinecore.networking.forge;

import io.github.blockneko11.sunshinecore.networking.PayloadContext;
import io.github.blockneko11.sunshinecore.networking.PayloadReceiver;
import io.github.blockneko11.sunshinecore.util.forge.EventBusUtils;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

public final class SNetworkingImpl {
    public static <T extends CustomPacketPayload> void registerS2CType(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec) {
        EventBusUtils.SC().<RegisterPayloadHandlersEvent>addListener(e ->
                e.registrar(type.id().getNamespace())
                        .playToClient(type, codec, (payload, context) -> {}));
    }

    public static <T extends CustomPacketPayload> void registerC2SReceiver(CustomPacketPayload.Type<T> type, StreamCodec<? super RegistryFriendlyByteBuf, T> codec, PayloadReceiver<T> receiver) {
        EventBusUtils.SC().<RegisterPayloadHandlersEvent>addListener(e ->
                e.registrar(type.id().getNamespace())
                        .playToClient(type, codec, (payload, context) ->
                                receiver.receive(payload, new PayloadContext.Server() {
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

    private SNetworkingImpl() {
    }
}
