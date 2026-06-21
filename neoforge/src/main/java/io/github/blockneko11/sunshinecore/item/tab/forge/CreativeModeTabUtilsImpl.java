package io.github.blockneko11.sunshinecore.item.tab.forge;

import io.github.blockneko11.sunshinecore.item.tab.CreativeModTabModifier;
import io.github.blockneko11.sunshinecore.util.forge.EventBusUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public final class CreativeModeTabUtilsImpl {
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

    static {
        EventBusUtils.SC().addListener(CreativeModeTabUtilsImpl::onBuildCreativeModeTabContents);
    }

    // BuildCreativeModeTabContentsEvent fires with each tab

    private static void onBuildCreativeModeTabContents(BuildCreativeModeTabContentsEvent e) {
        MODIFIERS.forEach(c -> c.accept(e));
    }

    private CreativeModeTabUtilsImpl() {
    }
}
