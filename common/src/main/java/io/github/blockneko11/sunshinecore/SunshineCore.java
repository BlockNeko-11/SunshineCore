package io.github.blockneko11.sunshinecore;

import io.github.blockneko11.sunshinecore.event.api.EventBus;
import io.github.blockneko11.sunshinecore.event.api.SEventBus;

public final class SunshineCore {
    public static final String MOD_ID = "sunshinecore";
    public static final EventBus BUS = SEventBus.get();

    public static void init() {

    }

    private SunshineCore() {
    }
}
