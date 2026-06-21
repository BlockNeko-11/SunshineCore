package io.github.blockneko11.sunshinecore;
import io.github.blockneko11.sunshinecore.event.impl.EventInvokers;
import io.github.blockneko11.sunshinecore.loader.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class SunshineCore {
    public static final String MOD_ID = "sunshinecore";
    public static final Logger LOGGER = LoggerFactory.getLogger("Sunshine Core");

    public static void init() {
        LOGGER.info("Sunshine Core loaded! version: {}", Platform.getMod(MOD_ID).getVersion());
        EventInvokers.init();

    }

    private SunshineCore() {
    }
}
