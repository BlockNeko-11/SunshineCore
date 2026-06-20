package io.github.blockneko11.sunshinecore.command.fabric;

import io.github.blockneko11.sunshinecore.command.CommandRegister;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

public final class CommandRegistryImpl {
    public static void registerCommand(CommandRegister<CommandSourceStack> register) {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            register.register(dispatcher, registryAccess, environment == Commands.CommandSelection.DEDICATED);
        });
    }

    private CommandRegistryImpl() {
    }
}
