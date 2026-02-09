package io.github.blockneko11.sunshinecore.data.provider.tag;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagEntry;

import java.util.function.Consumer;
import java.util.function.Predicate;

public class ForceAddTagEntry extends TagEntry {
    private final TagEntry delegate;

    public ForceAddTagEntry(TagEntry delegate) {
        super(delegate.id, true, delegate.required);
        this.delegate = delegate;
    }

    @Override
    public <T> boolean build(TagEntry.Lookup<T> arg, Consumer<T> consumer) {
        return this.delegate.build(arg, consumer);
    }

    @Override
    public boolean verifyIfPresent(Predicate<ResourceLocation> elementExists, Predicate<ResourceLocation> tagExists) {
        return true;
    }
}
