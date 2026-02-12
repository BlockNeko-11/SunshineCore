package com.example.testmod.data;

import com.example.testmod.registry.TestRegistry;
import io.github.blockneko11.sunshinecore.data.provider.misc.STranslationProvider;
import net.minecraft.data.PackOutput;

public final class TestTranslationProvider extends STranslationProvider.EN_US {
    public TestTranslationProvider(String modId, boolean validate, PackOutput output) {
        super(modId, validate, output);
    }

    @Override
    public void translate(Translator builder) {
        builder.add(TestRegistry.TEST_BLOCK.get(), "Test Block");
        builder.add(TestRegistry.TEST_BLOCK_FLATTENED.get(), "Test Block (Flattened)");
    }
}
