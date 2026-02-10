package io.github.blockneko11.sunshinecore.data.provider.tag;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagBuilder;
import net.minecraft.tags.TagEntry;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.NotNull;

import java.util.function.Function;
import java.util.function.Supplier;

public class STagBuilder<T> extends TagBuilder {
    private final Function<T, ResourceKey<T>> toResourceKey;

    private boolean replace = false;

    public STagBuilder(Function<T, ResourceKey<T>> toResourceKey) {
        super();
        this.toResourceKey = toResourceKey;
    }

    public STagBuilder<T> replace(boolean replace) {
        this.replace = replace;
        return this;
    }

    public boolean isReplace() {
        return this.replace;
    }

    // add

    public STagBuilder<T> add(T entry) {
        return this.add(this.toResourceKey.apply(entry));
    }

    public STagBuilder<T> add(Supplier<T> supplier) {
        return this.add(supplier.get());
    }

    public STagBuilder<T> add(ResourceKey<T> key) {
        return this.addElement(key.location());
    }

    @NotNull
    @Override
    public STagBuilder<T> addElement(ResourceLocation id) {
        super.addElement(id);
        return this;
    }

    @NotNull
    @Override
    public STagBuilder<T> add(TagEntry entry) {
        super.add(entry);
        return this;
    }

    // add optional

    @NotNull
    @Override
    public STagBuilder<T> addOptionalElement(ResourceLocation id) {
        super.addOptionalElement(id);
        return this;
    }

    public STagBuilder<T> addOptionalElement(ResourceKey<T> key) {
        return this.addOptionalElement(key.location());
    }

    // add tag


    @NotNull
    @Override
    public STagBuilder<T> addTag(ResourceLocation id) {
        super.addTag(id);
        return this;
    }

    public STagBuilder<T> addTag(TagKey<T> tag) {
        return this.addTag(tag.location());
    }

    @NotNull
    @Override
    public STagBuilder<T> addOptionalTag(ResourceLocation id) {
        super.addOptionalTag(id);
        return this;
    }

    public STagBuilder<T> addOptionalTag(TagKey<T> tag) {
        return this.addOptionalTag(tag.location());
    }

    public STagBuilder<T> forceAddTag(TagKey<T> tag) {
        return this.add(new ForceAddTagEntry(TagEntry.element(tag.location())));
    }
}
