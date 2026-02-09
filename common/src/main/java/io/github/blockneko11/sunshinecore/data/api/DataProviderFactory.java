package io.github.blockneko11.sunshinecore.data.api;

import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

@FunctionalInterface
public interface DataProviderFactory<T extends DataProvider> {
    T create(String modId, boolean validate, PackOutput output);
}
