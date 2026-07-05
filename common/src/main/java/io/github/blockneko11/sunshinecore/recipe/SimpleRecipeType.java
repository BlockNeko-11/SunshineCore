package io.github.blockneko11.sunshinecore.recipe;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import org.jetbrains.annotations.NotNull;

public record SimpleRecipeType<T extends Recipe<?>>(String id) implements RecipeType<T> {
    @Override
    public String toString() {
        return id;
    }
}
