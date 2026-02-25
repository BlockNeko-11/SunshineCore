package io.github.blockneko11.sunshinecore.event;

import io.github.blockneko11.sunshinecore.event.api.Event;

public final class CommonEvents {
    public static final Event<Setup> SETUP = Event.create(handlers -> () -> {
        for (Setup handler : handlers) {
            handler.onSetup();
        }
    });

    /**
     * The setup event.
     * <ul>
     *     <li>In Fabric, fired when Sunshine Core mod's client entrypoint or server entrypoint is loaded.
     *     <li>In Forge / NeoForge, equivalent to {@code FMLCommonSetupEvent}.
     * </ul>
     */
    @FunctionalInterface
    public interface Setup {
        void onSetup();
    }

    private CommonEvents() {
    }
}
