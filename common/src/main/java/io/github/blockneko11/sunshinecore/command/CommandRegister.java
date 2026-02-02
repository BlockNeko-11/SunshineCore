package io.github.blockneko11.sunshinecore.command;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandBuildContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;

@FunctionalInterface
public interface CommandRegister<S> {
    void register(CommandDispatcher<S> dispatcher, CommandBuildContext registryAccess, boolean isDedicated);
}
