package io.github.blockneko11.sunshinecore.event;

import net.minecraft.server.MinecraftServer;

/**
 * Game's lifecycle event on the server side.
 */
public abstract class ServerEvent extends Event {
    protected final MinecraftServer server;

    public ServerEvent(MinecraftServer server) {
        this.server = server;
    }

    public MinecraftServer getServer() {
        return this.server;
    }

    public static final class BeforeStart extends ServerEvent {
        public BeforeStart(MinecraftServer server) {
            super(server);
        }
    }

    public static final class Starting extends ServerEvent {
        public Starting(MinecraftServer server) {
            super(server);
        }
    }

    public static final class Started extends ServerEvent {
        public Started(MinecraftServer server) {
            super(server);
        }
    }

    public static final class Stopping extends ServerEvent {
        public Stopping(MinecraftServer server) {
            super(server);
        }
    }

    public static final class Stopped extends ServerEvent {
        public Stopped(MinecraftServer server) {
            super(server);
        }
    }

    public static final class PreTick extends ServerEvent {
        public PreTick(MinecraftServer server) {
            super(server);
        }
    }

    public static final class PostTick extends ServerEvent {
        public PostTick(MinecraftServer server) {
            super(server);
        }
    }
}
