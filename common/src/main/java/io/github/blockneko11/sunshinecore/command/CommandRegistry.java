package io.github.blockneko11.sunshinecore.command;

import com.mojang.brigadier.arguments.ArgumentType;
import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.blockneko11.sunshinecore.registry.Registrar;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * Command register API.
 */
public final class CommandRegistry {
    /**
     * Register a command.
     * @param register the command register
     */
    @ExpectPlatform
    public static void registerCommand(CommandRegister<CommandSourceStack> register) {
        throw new AssertionError();
    }

    /**
     * Register a custom argument type.
     * <h1>NOTE: After calling this, you also need to use {@link Registrar} to register your argument type to {@link BuiltInRegistries#COMMAND_ARGUMENT_TYPE} registry.</h1>
     * @param clazz
     * @param serializer
     * @param <A>
     * @param <T>
     */
    public static <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>>
            void registerArgumentType(Class<? extends A> clazz, ArgumentTypeInfo<A, T> serializer) {
        ArgumentTypeInfos.BY_CLASS.put(clazz, serializer);
    }

    private CommandRegistry() {
    }
}
