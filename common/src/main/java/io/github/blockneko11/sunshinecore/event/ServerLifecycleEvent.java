package io.github.blockneko11.sunshinecore.event;

import net.minecraft.server.MinecraftServer;

/**
 * Game's lifecycle event on the server side.
 */
public abstract class ServerLifecycleEvent extends Event {
    protected final MinecraftServer server;

    public ServerLifecycleEvent(MinecraftServer server) {
        this.server = server;
    }

    public MinecraftServer getServer() {
        return this.server;
    }

    public static final class BeforeStart extends ServerLifecycleEvent {
        public BeforeStart(MinecraftServer server) {
            super(server);
        }
    }

    public static final class Starting extends ServerLifecycleEvent {
        public Starting(MinecraftServer server) {
            super(server);
        }
    }

    public static final class Started extends ServerLifecycleEvent {
        public Started(MinecraftServer server) {
            super(server);
        }
    }

    public static final class Stopping extends ServerLifecycleEvent {
        public Stopping(MinecraftServer server) {
            super(server);
        }
    }

    public static final class Stopped extends ServerLifecycleEvent {
        public Stopped(MinecraftServer server) {
            super(server);
        }
    }

    public static final class PreTick extends ServerLifecycleEvent {
        public PreTick(MinecraftServer server) {
            super(server);
        }
    }

    public static final class PostTick extends ServerLifecycleEvent {
        public PostTick(MinecraftServer server) {
            super(server);
        }
    }
}
