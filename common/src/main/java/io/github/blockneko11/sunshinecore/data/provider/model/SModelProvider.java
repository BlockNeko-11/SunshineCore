package io.github.blockneko11.sunshinecore.data.provider.model;

import com.google.gson.JsonElement;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.ModelProvider;
import net.minecraft.data.models.blockstates.BlockStateGenerator;
import net.minecraft.data.models.model.DelegatedModel;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public abstract class SModelProvider extends ModelProvider {
    private final String modId;
    private final boolean validate;

    public SModelProvider(String modId, boolean validate, PackOutput output) {
        super(output);
        this.validate = validate;
        this.modId = modId;
    }

    public abstract void generateBlockStateModels(BlockModelGenerators generator);

    public abstract void generateItemModels(ItemModelGenerators generator);

    @NotNull
    @Override
    public CompletableFuture<?> run(CachedOutput writer) {
        Map<Block, BlockStateGenerator> blockStateModels = new HashMap<>();
        Map<ResourceLocation, Supplier<JsonElement>> itemModels = new HashMap<>();

        Set<Item> items = new HashSet<>();
        BlockModelGenerators blockModelGenerator = new BlockModelGenerators(gen -> {
            Block block = gen.getBlock();
            if (blockStateModels.containsKey(block)) {
                throw new IllegalStateException("Duplicate block state definition for " + block);
            }

            blockStateModels.put(block, gen);
        }, (id, supplier) -> {
            if (itemModels.containsKey(id)) {
                throw new IllegalStateException("Duplicate model definition for " + id);
            }

            itemModels.put(id, supplier);
        }, items::add);
        this.generateBlockStateModels(blockModelGenerator);
        blockModelGenerator.run();

        ItemModelGenerators itemModelGenerator = new ItemModelGenerators((id, supplier) -> {
            if (itemModels.containsKey(id)) {
                throw new IllegalStateException("Duplicate model definition for " + id);
            }

            itemModels.put(id, supplier);
        });
        this.generateItemModels(itemModelGenerator);
        itemModelGenerator.run();

        // check models if missing block states
        if (this.validate) {
            List<Block> blocksMissingState = BuiltInRegistries.BLOCK.stream().filter(block ->
                            BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(this.modId) &&
                            !blockStateModels.containsKey(block))
                    .toList();

            if (!blocksMissingState.isEmpty()) {
                throw new IllegalStateException("Missing block state definition for " + blocksMissingState);
            }
        }

        BuiltInRegistries.BLOCK.forEach(b -> {
            Item item = Item.BY_BLOCK.get(b);
            if (item == null || items.contains(item)) {
                return;
            }

            if (blockStateModels.containsKey(b)) {
                return;
            }

            if (!BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(this.modId)) {
                return;
            }

            ResourceLocation id = ModelLocationUtils.getModelLocation(item);
            if (itemModels.containsKey(id)) {
                return;
            }

            itemModels.put(id, new DelegatedModel(ModelLocationUtils.getModelLocation(b)));
        });

        CompletableFuture<?>[] blockStateModelSaver = blockStateModels.entrySet().stream().map(e ->
                DataProvider.saveStable(writer, e.getValue().get(), this.blockStatePathProvider.json(e.getKey().builtInRegistryHolder().key().location()))).toArray(i -> new CompletableFuture<?>[i]);
        CompletableFuture<?>[] modelSaver = itemModels.entrySet().stream().map(e ->
                DataProvider.saveStable(writer, e.getValue().get(), this.modelPathProvider.json(e.getKey()))).toArray(i -> new CompletableFuture<?>[i]);

        return CompletableFuture.allOf(CompletableFuture.allOf(blockStateModelSaver), CompletableFuture.allOf(modelSaver));
    }
}
