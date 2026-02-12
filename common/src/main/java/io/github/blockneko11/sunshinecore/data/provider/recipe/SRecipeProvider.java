package io.github.blockneko11.sunshinecore.data.provider.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;

import java.util.concurrent.CompletableFuture;

public abstract class SRecipeProvider extends RecipeProvider {
    public SRecipeProvider(String modId, boolean validate, PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected abstract void buildRecipes(RecipeOutput recipeOutput);
}
