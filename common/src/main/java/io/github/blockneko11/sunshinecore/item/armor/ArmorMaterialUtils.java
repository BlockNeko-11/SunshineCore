package io.github.blockneko11.sunshinecore.item.armor;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public final class ArmorMaterialUtils {
    public static ArmorMaterial create(ResourceLocation texture, boolean dyeable, int[] defense, int enchantmentValue, Supplier<SoundEvent> equipSound, float toughness, float knockbackResistance) {
        if (defense.length != 5) {
            throw new IllegalArgumentException("Expected 5 values for defense, got " + defense.length);
        }

        Map<ArmorItem.Type, Integer> defenseMap = new EnumMap<>(ArmorItem.Type.class);
        for (int i = 0; i < defense.length; i++) {
            defenseMap.put(ArmorItem.Type.values()[i], defense[i]);
        }

        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(texture, "", dyeable));

        Holder<SoundEvent> sound = BuiltInRegistries.SOUND_EVENT.getHolder(equipSound.get().getLocation()).orElseThrow();
        return new ArmorMaterial(defenseMap, enchantmentValue, sound, () -> null, layers, toughness, knockbackResistance);
    }

    private ArmorMaterialUtils() {
    }
}
