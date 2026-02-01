package io.github.blockneko11.sunshinecore.event.level.player;

import io.github.blockneko11.sunshinecore.event.Event;
import net.minecraft.world.entity.player.Player;

public abstract class PlayerEvent<T extends Player> extends Event {
    protected final T player;

    public PlayerEvent(T player) {
        this.player = player;
    }

    public T getPlayer() {
        return this.player;
    }

    public static final class PreTick extends PlayerEvent<Player> {
        public PreTick(Player player) {
            super(player);
        }
    }

    public static final class PostTick extends PlayerEvent<Player> {
        public PostTick(Player player) {
            super(player);
        }
    }
}
