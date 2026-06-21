package io.github.blockneko11.sunshinecore.server.event.level;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.level.LevelEvent;
import net.minecraft.server.level.ServerLevel;

/**
 * Level's lifecycle events on the server side.
 */
public final class ServerLevelEvents {
    public static final Event<LevelEvent<ServerLevel>> LOAD = LevelEvent.create();
    public static final Event<LevelEvent<ServerLevel>> UNLOAD = LevelEvent.create();
    public static final Event<LevelEvent<ServerLevel>> SAVE = LevelEvent.create();
    public static final Event<LevelEvent<ServerLevel>> PRE_TICK = LevelEvent.create();
    public static final Event<LevelEvent<ServerLevel>> POST_TICK = LevelEvent.create();

    private ServerLevelEvents() {
    }
}
