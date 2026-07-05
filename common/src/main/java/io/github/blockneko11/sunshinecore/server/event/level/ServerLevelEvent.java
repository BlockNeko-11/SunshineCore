package io.github.blockneko11.sunshinecore.server.event.level;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.level.LevelEvent;
import net.minecraft.server.level.ServerLevel;

public interface ServerLevelEvent {
    Event<LevelEvent<ServerLevel>> LOAD = LevelEvent.create();
    Event<LevelEvent<ServerLevel>> UNLOAD = LevelEvent.create();
    Event<LevelEvent<ServerLevel>> SAVE = LevelEvent.create();
    Event<LevelEvent<ServerLevel>> PRE_TICK = LevelEvent.create();
    Event<LevelEvent<ServerLevel>> POST_TICK = LevelEvent.create();
}
