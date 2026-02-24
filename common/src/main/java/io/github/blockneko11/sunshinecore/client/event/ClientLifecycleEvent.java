package io.github.blockneko11.sunshinecore.client.event;

import io.github.blockneko11.sunshinecore.event.Event;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;

/**
 * Game's lifecycle event on the client side.
 */
@Environment(EnvType.CLIENT)
public abstract class ClientLifecycleEvent extends Event {
    protected final Minecraft client;

    public ClientLifecycleEvent(Minecraft client) {
        this.client = client;
    }

    public Minecraft getClient() {
        return this.client;
    }

    public static final class Started extends ClientLifecycleEvent {
        public Started(Minecraft client) {
            super(client);
        }
    }

    public static final class Stopping extends ClientLifecycleEvent {
        public Stopping(Minecraft client) {
            super(client);
        }
    }

    public static final class PreTick extends ClientLifecycleEvent {
        public PreTick(Minecraft client) {
            super(client);
        }
    }

    public static final class PostTick extends ClientLifecycleEvent {
        public PostTick(Minecraft client) {
            super(client);
        }
    }
}
