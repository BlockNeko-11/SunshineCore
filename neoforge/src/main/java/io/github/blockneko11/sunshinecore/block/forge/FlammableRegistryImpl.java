package io.github.blockneko11.sunshinecore.block.forge;

import io.github.blockneko11.sunshinecore.registry.RegistryUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.ApiStatus;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public final class FlammableRegistryImpl {
    private static final Map<Block, FlammableEntry> FLAMMABLES = new HashMap<>();
    private static final Map<TagKey<Block>, FlammableEntry> FLAMMABLE_TAGS = new HashMap<>();
    private static Map<Block, FlammableEntry> COMPUTED_FLAMMABLES = null;

    public static void register(int flameAbility, int spreadSpeed, Collection<Block> blocks) {
        COMPUTED_FLAMMABLES = null;
        for (Block block : blocks) {
            FLAMMABLES.put(block, new FlammableEntry(flameAbility, spreadSpeed));
        }
    }

    public static void register(int burn, int spread, TagKey<Block> tag) {
        COMPUTED_FLAMMABLES = null;
        FLAMMABLE_TAGS.put(tag, new FlammableEntry(burn, spread));
    }

    @ApiStatus.Internal
    public static Map<Block, FlammableEntry> getFlammables() {
        if (COMPUTED_FLAMMABLES != null) {
            return COMPUTED_FLAMMABLES;
        }

        Map<Block, FlammableEntry> computed = new HashMap<>();

        for (Map.Entry<TagKey<Block>, FlammableEntry> entry : FLAMMABLE_TAGS.entrySet()) {
            for (Block block : RegistryUtils.getTagEntries(BuiltInRegistries.BLOCK, entry.getKey())) {
                computed.put(block, entry.getValue());
            }
        }

        computed.putAll(FLAMMABLES);
        COMPUTED_FLAMMABLES = computed;
        return COMPUTED_FLAMMABLES;
    }

    @ApiStatus.Internal
    public static void onUpdateTags() {
        COMPUTED_FLAMMABLES = null;
    }

    @ApiStatus.Internal
    public record FlammableEntry(int flameAbility, int spreadSpeed) {
    }

    private FlammableRegistryImpl() {
    }
}
