package io.github.blockneko11.sunshinecore.event.level;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.api.EventFactory;
import net.minecraft.world.level.Level;

/**
 * Level event.
 * @param <T> the type of level (e.g. ClientLevel, ServerLevel)
 */
public interface LevelEvent<T extends Level> {
    void handle(T level);

    static <T extends Level> Event<LevelEvent<T>> create() {
        return EventFactory.createNonReturn();
    }
}
