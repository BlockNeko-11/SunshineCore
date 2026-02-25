package io.github.blockneko11.sunshinecore.event.level;

import io.github.blockneko11.sunshinecore.event.api.Event;
import net.minecraft.world.level.Level;

/**
 * Level event.
 * @param <T> the type of level (e.g. client-sided, server-sided)
 */
public interface LevelEvent<T extends Level> {
    void handle(T level);

    static <T extends Level> Event<LevelEvent<T>> create() {
        return Event.create(handlers -> level -> {
            for (LevelEvent<T> handler : handlers) {
                handler.handle(level);
            }
        });
    }
}
