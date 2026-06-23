package io.github.blockneko11.sunshinecore.entity.mob;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public final class SpawnPlacementsRegistry {
    @ExpectPlatform
    public static <T extends Mob> void register(EntityType<T> entityType, SpawnPlacementType pType, Heightmap.Types hType, SpawnPlacements.SpawnPredicate<T> sPredicate) {
        throw new AssertionError();
    }

    private SpawnPlacementsRegistry() {
    }
}
