package io.github.blockneko11.sunshinecore.server.event;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.api.EventFactory;
import net.minecraft.server.MinecraftServer;

@FunctionalInterface
public interface ServerEvent {
    Event<ServerEvent> BEFORE_START = EventFactory.createNonReturn();
    Event<ServerEvent> STARTING = EventFactory.createNonReturn();
    Event<ServerEvent> STARTED = EventFactory.createNonReturn();
    Event<ServerEvent> STOPPING = EventFactory.createNonReturn();
    Event<ServerEvent> STOPPED = EventFactory.createNonReturn();
    Event<ServerEvent> PRE_TICK = EventFactory.createNonReturn();
    Event<ServerEvent> POST_TICK = EventFactory.createNonReturn();

    void handle(MinecraftServer server);
}
