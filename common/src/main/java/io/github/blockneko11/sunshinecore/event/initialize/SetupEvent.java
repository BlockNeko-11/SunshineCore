package io.github.blockneko11.sunshinecore.event.initialize;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.api.EventFactory;

/**
 * The setup event.
 * <ul>
 *     <li>For Fabric, fired when Sunshine Core's client entrypoint or server entrypoint is loaded.
 *     <li>For Forge / NeoForge, equivalent to {@code FMLCommonSetupEvent}.
 * </ul>
 */
@FunctionalInterface
public interface SetupEvent {
    Event<SetupEvent> EVENT = EventFactory.createNonReturn();

    void onSetup();
}
