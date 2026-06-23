package io.github.blockneko11.sunshinecore.entity.mob.forge;

import io.github.blockneko11.sunshinecore.util.forge.EventBusUtils;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent;

import java.util.ArrayList;
import java.util.List;

public final class SpawnPlacementsRegistryImpl {
    private static final List<Entry<?>> ENTRIES = new ArrayList<>();

    public static <T extends Mob> void register(EntityType<T> entityType, SpawnPlacementType pType, Heightmap.Types hType, SpawnPlacements.SpawnPredicate<T> sPredicate) {
        ENTRIES.add(new Entry<>(entityType, pType, hType, sPredicate));
    }

    static {
        EventBusUtils.SC().addListener(SpawnPlacementsRegistryImpl::onRegister);
    }

    private static void onRegister(RegisterSpawnPlacementsEvent e) {
        for (Entry<?> entry : ENTRIES) {
            e.register((EntityType) entry.entityType, entry.pType, entry.hType, entry.sPredicate, RegisterSpawnPlacementsEvent.Operation.OR);
        }
    }

    private record Entry<T extends Mob>(EntityType<T> entityType, SpawnPlacementType pType, Heightmap.Types hType, SpawnPlacements.SpawnPredicate<T> sPredicate) {
    }

    private SpawnPlacementsRegistryImpl() {
    }
}
