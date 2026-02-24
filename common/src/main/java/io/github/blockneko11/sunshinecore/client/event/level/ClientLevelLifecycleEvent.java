package io.github.blockneko11.sunshinecore.client.event.level;

import io.github.blockneko11.sunshinecore.event.level.LevelEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;

/**
 * Level's lifecycle event on the client side.
 */
@Environment(EnvType.CLIENT)
public abstract class ClientLevelLifecycleEvent extends LevelEvent<ClientLevel> {
    public ClientLevelLifecycleEvent(ClientLevel level) {
        super(level);
    }

    public static final class Load extends ClientLevelLifecycleEvent {
        public Load(ClientLevel level) {
            super(level);
        }
    }

    public static final class PreTick extends ClientLevelLifecycleEvent {
        public PreTick(ClientLevel level) {
            super(level);
        }
    }

    public static final class PostTick extends ClientLevelLifecycleEvent {
        public PostTick(ClientLevel level) {
            super(level);
        }
    }
}
