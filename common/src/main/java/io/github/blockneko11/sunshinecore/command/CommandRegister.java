package io.github.blockneko11.sunshinecore.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

/**
 * Command register API.
 * @param <S> the type of command source
 */
@FunctionalInterface
public interface CommandRegister<S> {
    /**
     * Register commands.
     * @param dispatcher the command dispatcher
     * @param registryAccess the registry access
     * @param isDedicated whether the server is dedicated
     */
    void register(CommandDispatcher<S> dispatcher, CommandBuildContext registryAccess, boolean isDedicated);
}
