package io.github.blockneko11.sunshinecore.command.forge;

import io.github.blockneko11.sunshinecore.command.CommandRegister;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.RegisterCommandsEvent;

import java.util.ArrayList;
import java.util.List;

public final class CommandRegistryImpl {
    private static final List<CommandRegister> REGISTERS = new ArrayList<>();

    public static void registerCommand(CommandRegister register) {
        REGISTERS.add(register);
    }

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onCommandRegister(RegisterCommandsEvent e) {
        REGISTERS.forEach(r -> r.register(e.getDispatcher(), e.getBuildContext(), e.getCommandSelection()));
    }
}
