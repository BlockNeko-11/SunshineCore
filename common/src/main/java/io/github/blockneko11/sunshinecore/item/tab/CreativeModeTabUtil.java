package io.github.blockneko11.sunshinecore.item.tab;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public final class CreativeModeTabUtil {
    public static CreativeModeTab create(Component title, Supplier<ItemStack> icon) {
        return create(builder -> builder.title(title).icon(icon));
    }

    public static CreativeModeTab create(Consumer<CreativeModeTab.Builder> consumer) {
        CreativeModeTab.Builder builder = createBuilder();
        consumer.accept(builder);
        return builder.build();
    }

    @ExpectPlatform
    public static CreativeModeTab.Builder createBuilder() {
        throw new AssertionError();
    }

    public static void modify(Supplier<CreativeModeTab> tab, CreativeModTabModifier modifier) {
        modify(tab.get(), modifier);
    }

    public static void modify(CreativeModeTab tab, CreativeModTabModifier modifier) {
        modify(BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(tab).orElseThrow(), modifier);
    }

    @ExpectPlatform
    public static void modify(ResourceKey<CreativeModeTab> tab, CreativeModTabModifier modifier) {
        throw new AssertionError();
    }

    private CreativeModeTabUtil() {
    }
}
