package io.github.blockneko11.sunshinecore.data.provider.misc;

import com.google.gson.JsonObject;
import io.github.blockneko11.sunshinecore.util.Id;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletableFuture;

public abstract class STranslationProvider implements DataProvider {
    private final String modId;
    private final PackOutput output;
    private final String language;

    public STranslationProvider(String modId, boolean validate, PackOutput output, String language) {
        this.modId = modId;
        this.output = output;
        this.language = language;
    }

    public abstract void translate(Translator builder);

    @NotNull
    @Override
    public CompletableFuture<?> run(CachedOutput writer) {
        Map<String, String> translations = new TreeMap<>();

        this.translate((k, v) -> {
            if (translations.containsKey(k)) {
                throw new IllegalStateException("Duplicate translation key: " + k);
            }

            translations.put(k, v);
        });

        JsonObject json = new JsonObject();
        translations.forEach(json::addProperty);
        return DataProvider.saveStable(writer, json, this.output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "lang")
                .json(Id.id(this.modId, this.language)));
    }

    @NotNull
    @Override
    public final String getName() {
        return "Sunshine Core Translation Provider for " + this.language;
    }

    public static abstract class EN_US extends STranslationProvider {
        public EN_US(String modId, boolean validate, PackOutput output) {
            super(modId, validate, output, "en_us");
        }
    }

    public static abstract class ZH_CN extends STranslationProvider {
        public ZH_CN(String modId, boolean validate, PackOutput output) {
            super(modId, validate, output, "zh_cn");
        }
    }

    @FunctionalInterface
    public interface Translator {
        void add(String key, String value);

        default void add(ItemLike item, String value) {
            this.add(item.asItem().getDescriptionId(), value);
        }

        default void add(ResourceLocation identifier, String value) {
            add(identifier.toLanguageKey(), value);
        }
    }
}
