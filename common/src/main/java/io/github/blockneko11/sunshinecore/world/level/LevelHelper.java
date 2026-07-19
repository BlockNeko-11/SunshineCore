package io.github.blockneko11.sunshinecore.world.level;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

public final class LevelHelper {
    public static boolean isServer(Level level) {
        return level instanceof ServerLevel;
    }

    public static void runInServer(Level level, Consumer<ServerLevel> consumer) {
        if (isServer(level)) {
            consumer.accept((ServerLevel) level);
        }
    }

    public static boolean isClient(Level level) {
        return level instanceof ClientLevel;
    }

    public static void runInClient(Level level, Consumer<ClientLevel> consumer) {
        if (isClient(level)) {
            consumer.accept((ClientLevel) level);
        }
    }

    private LevelHelper() {
    }
}
