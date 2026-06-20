package io.github.blockneko11.sunshinecore.networking;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

@FunctionalInterface
public interface PayloadReceiver<T extends CustomPacketPayload> {
    void receive(T payload, PayloadContext ctx);
}
