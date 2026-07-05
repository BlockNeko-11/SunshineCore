package io.github.blockneko11.sunshinecore.recipe;

import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;

public class SimpleRecipeType<T extends Recipe<?>> implements RecipeType<T> {
    private final String id;

    public SimpleRecipeType(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.id;
    }
}
