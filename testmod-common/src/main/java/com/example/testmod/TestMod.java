package com.example.testmod;

import com.example.testmod.registry.TestRegistry;
import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.util.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TestMod {
    public static final String MOD_ID = "testmod";
    public static final String MOD_NAME = "Test Mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        LOGGER.info("Hello from Test Mod!");

        LOGGER.info("Loader: {}, Side: {}, Dev mode: {}", Platform.getLoader(), Platform.getSide(), Platform.isDev());

        LOGGER.info("Sunshine Core mod loaded? {}", Platform.isModLoaded(SunshineCore.MOD_ID));
        LOGGER.info("Test Mod version: {}", Platform.getMod(MOD_ID).getVersion());

        TestRegistry.init();
    }
}
