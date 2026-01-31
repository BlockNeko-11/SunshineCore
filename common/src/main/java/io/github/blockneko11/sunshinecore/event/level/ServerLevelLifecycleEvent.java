package io.github.blockneko11.sunshinecore.event.level;

import net.minecraft.server.level.ServerLevel;

public abstract class ServerLevelLifecycleEvent extends LevelEvent<ServerLevel> {
    public ServerLevelLifecycleEvent(ServerLevel level) {
        super(level);
    }

    public static final class Load extends ServerLevelLifecycleEvent {
        public Load(ServerLevel level) {
            super(level);
        }
    }

    public static final class Unload extends ServerLevelLifecycleEvent {
        public Unload(ServerLevel level) {
            super(level);
        }
    }

    public static final class Save extends ServerLevelLifecycleEvent {
        public Save(ServerLevel level) {
            super(level);
        }
    }

    public static final class PreTick extends ServerLevelLifecycleEvent {
        public PreTick(ServerLevel level) {
            super(level);
        }
    }

    public static final class PostTick extends ServerLevelLifecycleEvent {
        public PostTick(ServerLevel level) {
            super(level);
        }
    }
}
