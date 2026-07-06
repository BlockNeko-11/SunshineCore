package io.github.blockneko11.sunshinecore.item.tab;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Supplier;

public final class CreativeModeTabUtils {
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


    public static void append(Supplier<CreativeModeTab> tab, Item... items) {
        append(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, items);
    }

    public static void append(Supplier<CreativeModeTab> tab, CreativeModeTab.TabVisibility visibility, Item... items) {
        append(tab, visibility, Arrays.asList(items));
    }

    public static void append(Supplier<CreativeModeTab> tab, Collection<Item> items) {
        append(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, items);
    }

    public static void append(Supplier<CreativeModeTab> tab, CreativeModeTab.TabVisibility visibility, Collection<Item> items) {
        appendStack(tab, visibility, items.stream().map(ItemStack::new).toList());
    }

    public static void appendStack(Supplier<CreativeModeTab> tab, ItemStack... stacks) {
        appendStack(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, stacks);
    }

    public static void appendStack(Supplier<CreativeModeTab> tab, CreativeModeTab.TabVisibility visibility, ItemStack... stacks) {
        appendStack(tab, visibility, Arrays.asList(stacks));
    }

    public static void appendStack(Supplier<CreativeModeTab> tab, Collection<ItemStack> stacks) {
        appendStack(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, stacks);
    }

    public static void appendStack(Supplier<CreativeModeTab> tab, CreativeModeTab.TabVisibility visibility, Collection<ItemStack> stacks) {
        modify(tab, (output, isOP) -> output.acceptAll(stacks, visibility));
    }


    public static void append(CreativeModeTab tab, Item... items) {
        append(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, items);
    }

    public static void append(CreativeModeTab tab, CreativeModeTab.TabVisibility visibility, Item... items) {
        append(tab, visibility, Arrays.asList(items));
    }

    public static void append(CreativeModeTab tab, Collection<Item> items) {
        append(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, items);
    }

    public static void append(CreativeModeTab tab, CreativeModeTab.TabVisibility visibility, Collection<Item> items) {
        appendStack(tab, visibility, items.stream().map(ItemStack::new).toList());
    }

    public static void appendStack(CreativeModeTab tab, ItemStack... stacks) {
        appendStack(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, stacks);
    }

    public static void appendStack(CreativeModeTab tab, CreativeModeTab.TabVisibility visibility, ItemStack... stacks) {
        appendStack(tab, visibility, Arrays.asList(stacks));
    }

    public static void appendStack(CreativeModeTab tab, Collection<ItemStack> stacks) {
        appendStack(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, stacks);
    }

    public static void appendStack(CreativeModeTab tab, CreativeModeTab.TabVisibility visibility, Collection<ItemStack> stacks) {
        modify(tab, (output, isOP) -> output.acceptAll(stacks, visibility));
    }


    public static void append(ResourceKey<CreativeModeTab> tab, Item... items) {
        append(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, items);
    }

    public static void append(ResourceKey<CreativeModeTab> tab, CreativeModeTab.TabVisibility visibility, Item... items) {
        append(tab, visibility, Arrays.asList(items));
    }

    public static void append(ResourceKey<CreativeModeTab> tab, Collection<Item> items) {
        append(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, items);
    }

    public static void append(ResourceKey<CreativeModeTab> tab, CreativeModeTab.TabVisibility visibility, Collection<Item> items) {
        appendStack(tab, visibility, items.stream().map(ItemStack::new).toList());
    }

    public static void appendStack(ResourceKey<CreativeModeTab> tab, ItemStack... stacks) {
        appendStack(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, stacks);
    }

    public static void appendStack(ResourceKey<CreativeModeTab> tab, CreativeModeTab.TabVisibility visibility, ItemStack... stacks) {
        appendStack(tab, visibility, Arrays.asList(stacks));
    }

    public static void appendStack(ResourceKey<CreativeModeTab> tab, Collection<ItemStack> stacks) {
        appendStack(tab, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS, stacks);
    }

    public static void appendStack(ResourceKey<CreativeModeTab> tab, CreativeModeTab.TabVisibility visibility, Collection<ItemStack> stacks) {
        modify(tab, (output, isOP) -> output.acceptAll(stacks, visibility));
    }

    private CreativeModeTabUtils() {
    }
}
