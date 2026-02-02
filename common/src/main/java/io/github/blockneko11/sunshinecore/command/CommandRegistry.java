package io.github.blockneko11.sunshinecore.command;

import com.mojang.brigadier.arguments.ArgumentType;
import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.synchronization.ArgumentTypeInfo;
import net.minecraft.commands.synchronization.ArgumentTypeInfos;

public final class CommandRegistry {
    @ExpectPlatform
    public static void registerCommand(CommandRegister<CommandSourceStack> register) {
        throw new AssertionError();
    }

    public static <A extends ArgumentType<?>, T extends ArgumentTypeInfo.Template<A>>
            void registerArgumentType(Class<? extends A> clazz, ArgumentTypeInfo<A, T> serializer) {
        ArgumentTypeInfos.BY_CLASS.put(clazz, serializer);
    }

    private CommandRegistry() {
    }
}
