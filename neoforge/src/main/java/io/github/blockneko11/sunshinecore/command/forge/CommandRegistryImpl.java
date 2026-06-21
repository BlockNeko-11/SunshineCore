package io.github.blockneko11.sunshinecore.command.forge;

import io.github.blockneko11.sunshinecore.command.CommandRegister;
import io.github.blockneko11.sunshinecore.util.forge.EventBusUtils;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.ArrayList;
import java.util.List;

public final class CommandRegistryImpl {
    private static final List<CommandRegister<CommandSourceStack>> REGISTERS = new ArrayList<>();

    public static void registerCommand(CommandRegister<CommandSourceStack> register) {
        REGISTERS.add(register);
    }

    static {
        EventBusUtils.FML().addListener(CommandRegistryImpl::onRegister);
    }

    private static void onRegister(RegisterCommandsEvent e) {
        REGISTERS.forEach(r -> {
            r.register(e.getDispatcher(), e.getBuildContext(), e.getCommandSelection() == Commands.CommandSelection.DEDICATED);
        });
    }

    private CommandRegistryImpl() {
    }
}
