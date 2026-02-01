package io.github.blockneko11.sunshinecore.command.fabric;

import io.github.blockneko11.sunshinecore.command.CommandRegister;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;

public final class CommandRegistryImpl {
    public static void registerCommand(CommandRegister register) {
        CommandRegistrationCallback.EVENT.register(register::register);
    }
}
