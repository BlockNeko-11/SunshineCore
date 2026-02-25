package io.github.blockneko11.sunshinecore.event.level;

import net.minecraft.server.level.ServerLevel;

/**
 * Level's lifecycle event on the server side.
 */
@Deprecated(since = "1.3.0")
public abstract class ServerLevelEvent {
    public ServerLevelEvent(ServerLevel level) {
    }

    public static final class Load extends ServerLevelEvent {
        public Load(ServerLevel level) {
            super(level);
        }
    }

    public static final class Unload extends ServerLevelEvent {
        public Unload(ServerLevel level) {
            super(level);
        }
    }

    public static final class Save extends ServerLevelEvent {
        public Save(ServerLevel level) {
            super(level);
        }
    }

    public static final class PreTick extends ServerLevelEvent {
        public PreTick(ServerLevel level) {
            super(level);
        }
    }

    public static final class PostTick extends ServerLevelEvent {
        public PostTick(ServerLevel level) {
            super(level);
        }
    }
}
