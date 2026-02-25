package io.github.blockneko11.sunshinecore.client.event;

import io.github.blockneko11.sunshinecore.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

/**
 * Game's lifecycle event on the client side.
 */
@Deprecated(since = "1.3.0")
@Environment(EnvType.CLIENT)
public abstract class ClientEvent extends Event {
    protected final Minecraft client;

    public ClientEvent(Minecraft client) {
        this.client = client;
    }

    public Minecraft getClient() {
        return this.client;
    }

    public static final class Started extends ClientEvent {
        public Started(Minecraft client) {
            super(client);
        }
    }

    public static final class Stopping extends ClientEvent {
        public Stopping(Minecraft client) {
            super(client);
        }
    }

    public static final class PreTick extends ClientEvent {
        public PreTick(Minecraft client) {
            super(client);
        }
    }

    public static final class PostTick extends ClientEvent {
        public PostTick(Minecraft client) {
            super(client);
        }
    }
}
