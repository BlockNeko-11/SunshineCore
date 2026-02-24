package io.github.blockneko11.sunshinecore.event.api;

public final class SEventBus extends EventBus {
    private static final SEventBus INSTANCE = new SEventBus();

    private SEventBus() {
        super("Event Bus (Sunshine Core)");
    }

    public static EventBus get() {
        return INSTANCE;
    }
}
