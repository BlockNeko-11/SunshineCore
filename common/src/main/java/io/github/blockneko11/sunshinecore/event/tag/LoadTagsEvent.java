package io.github.blockneko11.sunshinecore.event.tag;

import io.github.blockneko11.sunshinecore.event.api.Event;
import io.github.blockneko11.sunshinecore.event.api.EventFactory;
import net.minecraft.core.RegistryAccess;

@FunctionalInterface
public interface LoadTagsEvent {
    Event<LoadTagsEvent> EVENT = EventFactory.createNonReturn();

    void onLoadTags(RegistryAccess access, boolean isClient);
}
