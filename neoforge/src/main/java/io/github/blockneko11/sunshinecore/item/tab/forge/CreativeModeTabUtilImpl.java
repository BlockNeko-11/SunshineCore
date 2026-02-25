package io.github.blockneko11.sunshinecore.item.tab.forge;

import io.github.blockneko11.sunshinecore.item.tab.CreativeModTabModifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class CreativeModeTabUtilImpl {
    private static final List<Consumer<BuildCreativeModeTabContentsEvent>> MODIFIERS = new ArrayList<>();

    public static CreativeModeTab.Builder createBuilder() {
        return CreativeModeTab.builder();
    }

    public static void modify(ResourceKey<CreativeModeTab> tab, CreativeModTabModifier modifier) {
        MODIFIERS.add(e -> {
            if (e.getTabKey().equals(tab)) {
                modifier.modify(e, e.hasPermissions());
            }
        });
    }

    // BuildCreativeModeTabContentsEvent fires with each tab

    @SubscribeEvent(priority = EventPriority.HIGH)
    public static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent e) {
        MODIFIERS.forEach(c -> c.accept(e));
    }
}
