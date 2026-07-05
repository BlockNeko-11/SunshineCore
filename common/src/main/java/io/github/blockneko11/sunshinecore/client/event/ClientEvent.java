package io.github.blockneko11.sunshinecore.client.event;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.api.EventFactory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

@Environment(EnvType.CLIENT)
@FunctionalInterface
public interface ClientEvent {
    Event<ClientEvent> STARTED = EventFactory.createNonReturn();
    Event<ClientEvent> STOPPING = EventFactory.createNonReturn();
    Event<ClientEvent> PRE_TICK = EventFactory.createNonReturn();
    Event<ClientEvent> POST_TICK = EventFactory.createNonReturn();

    void handle(Minecraft client);
}
