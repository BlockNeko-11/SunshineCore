package io.github.blockneko11.sunshinecore.item.armor;

import net.minecraft.world.item.ArmorItem;

import java.util.EnumMap;

public record ArmorDefense(int helmet, int chestplates, int leggings, int boots, int body) {
    public boolean isValid() {
        return this.helmet > 0 ||
                this.chestplates > 0 ||
                this.leggings > 0 ||
                this.boots > 0 ||
                this.body > 0;
    }

    public EnumMap<ArmorItem.Type, Integer> getDefenseMap() {
        if (!this.isValid()) {
            throw new IllegalArgumentException("ArmorDefense is not valid");
        }

        EnumMap<ArmorItem.Type, Integer> map = new EnumMap<>(ArmorItem.Type.class);
        map.put(ArmorItem.Type.HELMET, this.helmet);
        map.put(ArmorItem.Type.CHESTPLATE, this.chestplates);
        map.put(ArmorItem.Type.LEGGINGS, this.leggings);
        map.put(ArmorItem.Type.BOOTS, this.boots);
        map.put(ArmorItem.Type.BODY, this.body);

        return map;
    }
}
