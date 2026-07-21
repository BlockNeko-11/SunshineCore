package io.github.blockneko11.sunshinecore.item.armor;

import io.github.blockneko11.sunshinecore.registry.holder.RegistryHolder;
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
    public static ArmorMaterial create(ResourceLocation texture, boolean dyeable, ArmorDefense defense, int enchantmentValue, Supplier<SoundEvent> equipSound, float toughness, float knockbackResistance) {
        Holder<SoundEvent> sound = BuiltInRegistries.SOUND_EVENT.getHolder(equipSound.get().getLocation()).orElseThrow();
        return create(texture, dyeable, defense, enchantmentValue, sound, toughness, knockbackResistance);
    }

    public static ArmorMaterial create(ResourceLocation texture, boolean dyeable, ArmorDefense defense, int enchantmentValue, RegistryHolder<SoundEvent, SoundEvent> equipSound, float toughness, float knockbackResistance) {
        return create(texture, dyeable, defense, enchantmentValue, equipSound.holder(), toughness, knockbackResistance);
    }

    public static ArmorMaterial create(ResourceLocation texture, boolean dyeable, ArmorDefense defense, int enchantmentValue, Holder<SoundEvent> equipSound, float toughness, float knockbackResistance) {
        EnumMap<ArmorItem.Type, Integer> defenseMap = defense.getDefenseMap();
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(texture, "", dyeable));
        return new ArmorMaterial(defenseMap, enchantmentValue, equipSound, () -> null, layers, toughness, knockbackResistance);
    }

    private ArmorMaterialUtils() {
    }
}
