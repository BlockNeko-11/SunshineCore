package io.github.blockneko11.sunshinecore.client.event.level;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.multiplayer.ClientLevel;

/**
 * Level's lifecycle event on the client side.
 */
@Deprecated(since = "1.3.0")
@Environment(EnvType.CLIENT)
public abstract class ClientLevelEvent {
    public ClientLevelEvent(ClientLevel level) {
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
