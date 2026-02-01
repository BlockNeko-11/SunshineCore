package io.github.blockneko11.sunshinecore.command;

import dev.architectury.injectables.annotations.ExpectPlatform;

public final class CommandRegistry {
    @ExpectPlatform
    public static void register(CommandRegister register) {
        throw new AssertionError();
    }
}
