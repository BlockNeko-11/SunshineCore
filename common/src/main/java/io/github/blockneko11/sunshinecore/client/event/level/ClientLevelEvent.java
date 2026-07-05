package io.github.blockneko11.sunshinecore.client.event.level;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.level.LevelEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;

@Environment(EnvType.CLIENT)
public interface ClientLevelEvent {
    Event<LevelEvent<ClientLevel>> LOAD = LevelEvent.create();
    Event<LevelEvent<ClientLevel>> PRE_TICK = LevelEvent.create();
    Event<LevelEvent<ClientLevel>> POST_TICK = LevelEvent.create();
}
