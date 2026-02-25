package io.github.blockneko11.sunshinecore.client.event.level;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.level.LevelEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;

/**
 * Level's lifecycle events on the client side.
 */
@Environment(EnvType.CLIENT)
public class ClientLevelEvents {
    public static final Event<LevelEvent<ClientLevel>> LOAD = LevelEvent.create();
    public static final Event<LevelEvent<ClientLevel>> PRE_TICK = LevelEvent.create();
    public static final Event<LevelEvent<ClientLevel>> POST_TICK = LevelEvent.create();

    private ClientLevelEvents() {
    }
}
