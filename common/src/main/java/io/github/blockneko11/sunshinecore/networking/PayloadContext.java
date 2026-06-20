package io.github.blockneko11.sunshinecore.networking;

import io.github.blockneko11.sunshinecore.loader.Side;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.entity.player.Player;

public interface PayloadContext {
    Player getPlayer();

    void execute(Runnable runnable);

    Side getSide();

    default RegistryAccess getRegistryAccess() {
        return getPlayer().registryAccess();
    }

    interface Server extends PayloadContext {
        @Override
        default Side getSide() {
            return Side.SERVER;
        }
    }

    @Environment(EnvType.CLIENT)
    interface Client extends PayloadContext {
        @Override
        default Side getSide() {
            return Side.CLIENT;
        }
    }
}
