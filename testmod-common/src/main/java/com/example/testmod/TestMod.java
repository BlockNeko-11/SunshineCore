package com.example.testmod;

import com.example.testmod.data.TestTagProvider;
import com.example.testmod.data.TestTranslationProvider;
import com.example.testmod.registry.TestRegistry;
import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.command.CommandRegistry;
import io.github.blockneko11.sunshinecore.data.SDataGeneration;
import io.github.blockneko11.sunshinecore.server.event.level.ServerLevelEvent;
import io.github.blockneko11.sunshinecore.loader.Platform;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TestMod {
    public static final String MOD_ID = "testmod";
    public static final String MOD_NAME = "Test Mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        LOGGER.info("Hello from Test Mod!");

        LOGGER.info("Loader: {}, Env: {}, Dev mode: {}", Platform.getLoader(), Platform.getSide(), Platform.isDev());

        LOGGER.info("Sunshine Core mod loaded? {}", Platform.isModLoaded(SunshineCore.MOD_ID));
        LOGGER.info("Test Mod version: {}", Platform.getMod(MOD_ID).getVersion());

        TestRegistry.init();
        ServerLevelEvent.LOAD.register(TestMod::onLevelLoad);

        CommandRegistry.registerCommand((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("test")
                    .executes(ctx -> {
                        ctx.getSource().sendSuccess(() -> Component.literal("Hello"), true);
                        return 1;
                    }));
        });
    }

    private static void onLevelLoad(ServerLevel level) {
        LOGGER.info("Level loaded: {}", level.dimension().location());
    }

    public static void initDataGen(SDataGeneration gen) {
        gen.addProvider(TestTagProvider.Block::new);
        gen.addProvider(TestTagProvider.Item::new);
        gen.addProvider(TestTranslationProvider::new);

        gen.run();
    }
}
