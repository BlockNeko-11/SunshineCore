package io.github.blockneko11.sunshinecore.entity.mob.fabric;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public final class SpawnPlacementsRegistryImpl {
    public static <T extends Mob> void register(EntityType<T> entityType, SpawnPlacementType pType, Heightmap.Types hType, SpawnPlacements.SpawnPredicate<T> sPredicate) {
        SpawnPlacements.register(entityType, pType, hType, sPredicate);
    }

    private SpawnPlacementsRegistryImpl() {
    }
}
