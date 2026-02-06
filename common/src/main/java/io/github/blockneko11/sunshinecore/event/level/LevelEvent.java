package io.github.blockneko11.sunshinecore.event.level;

import io.github.blockneko11.sunshinecore.event.Event;
import net.minecraft.world.level.Level;

/**
 * Level event.
 * @param <T>
 */
public abstract class LevelEvent<T extends Level> extends Event {
    protected final T level;

    public LevelEvent(T level) {
        this.level = level;
    }

    public T getLevel() {
        return this.level;
    }
}
