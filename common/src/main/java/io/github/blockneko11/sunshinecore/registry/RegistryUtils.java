package io.github.blockneko11.sunshinecore.registry;

import io.github.blockneko11.sunshinecore.util.StreamUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.tags.TagKey;

import java.util.List;

public final class RegistryUtils {
    public static <R> List<R> getEntries(Registry<R> registry, TagKey<R> tag) {
        return StreamUtil.toStream(registry.getTagOrEmpty(tag))
                .map(Holder::value)
                .toList();
    }

    private RegistryUtils() {
    }
}
