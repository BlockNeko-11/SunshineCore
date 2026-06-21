package io.github.blockneko11.sunshinecore.event;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.api.EventFactory;

public final class CommonEvents {
    public static final Event<Setup> SETUP = EventFactory.createNonReturn();

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
