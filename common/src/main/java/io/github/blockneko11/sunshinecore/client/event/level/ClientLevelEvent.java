package io.github.blockneko11.sunshinecore.client.event.level;

import io.github.blockneko11.sunshinecore.event.level.LevelEvent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;

/**
 * Level's lifecycle event on the client side.
 */
@Environment(EnvType.CLIENT)
public abstract class ClientLevelEvent extends LevelEvent<ClientLevel> {
    public ClientLevelEvent(ClientLevel level) {
        super(level);
    }

    public static final class Load extends ClientLevelEvent {
        public Load(ClientLevel level) {
            super(level);
        }
    }

    public static final class PreTick extends ClientLevelEvent {
        public PreTick(ClientLevel level) {
            super(level);
        }
    }

    public static final class PostTick extends ClientLevelEvent {
        public PostTick(ClientLevel level) {
            super(level);
        }
    }
}
