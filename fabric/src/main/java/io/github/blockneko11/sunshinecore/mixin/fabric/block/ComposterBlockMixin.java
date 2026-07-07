package io.github.blockneko11.sunshinecore.mixin.fabric.block;

import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.ComposterBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ComposterBlock.class)
public abstract class ComposterBlockMixin {
    @Redirect(
            method = "useItemOn",
            at = @At(
                    value = "INVOKE",
                    target = "Lit/unimi/dsi/fastutil/objects/Object2FloatMap;containsKey(Ljava/lang/Object;)Z",
                    remap = false
            )
    )
    private static boolean sc$useItemOn(Object2FloatMap<ItemLike> instance, Object o) {
        Item item = (Item) o;
        if (item.sc$getCompostingChance() > 0.0f) {
            return true;
        }

        return instance.containsKey(item);
    }

    @Redirect(
            method = "insertItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lit/unimi/dsi/fastutil/objects/Object2FloatMap;containsKey(Ljava/lang/Object;)Z",
                    remap = false
            )
    )
    private static boolean sc$insertItem(Object2FloatMap<ItemLike> instance, Object o) {
        Item item = (Item) o;
        if (item.sc$getCompostingChance() > 0.0f) {
            return true;
        }

        return instance.containsKey(item);
    }

    @Redirect(
            method = "addItem",
            at = @At(
                    value = "INVOKE",
                    target = "Lit/unimi/dsi/fastutil/objects/Object2FloatMap;getFloat(Ljava/lang/Object;)F",
                    remap = false
            )
    )
    private static float sc$addItem(Object2FloatMap<ItemLike> instance, Object o) {
        Item item = (Item) o;
        if (item.sc$getCompostingChance() > 0.0f) {
            return item.sc$getCompostingChance();
        }

        return instance.getFloat(item);
    }
}
