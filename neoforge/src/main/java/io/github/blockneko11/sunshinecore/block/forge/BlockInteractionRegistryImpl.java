package io.github.blockneko11.sunshinecore.block.forge;

import io.github.blockneko11.sunshinecore.registry.RegistryUtils;
import io.github.blockneko11.sunshinecore.util.forge.EventBusUtils;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.event.TagsUpdatedEvent;
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent;
import org.jetbrains.annotations.ApiStatus;

import java.util.*;
import java.util.function.Consumer;

public final class BlockInteractionRegistryImpl {
    private static final Map<Block, FlammableEntry> FLAMMABLES = new HashMap<>();
    private static final Map<TagKey<Block>, FlammableEntry> FLAMMABLE_TAGS = new HashMap<>();
    private static Map<Block, FlammableEntry> COMPUTED_FLAMMABLES = null;

    private static final Map<Item, Float> COMPOSTABLES = new HashMap<>();
    private static final Map<TagKey<Item>, Float> COMPOSTABLE_TAGS = new HashMap<>();
    private static Map<Item, Float> COMPUTED_COMPOSTABLES = null;

    private static final Map<Item, Integer> FUELS = new HashMap<>();
    private static final Map<TagKey<Item>, Integer> FUEL_TAGS = new HashMap<>();
    private static Map<Item, Integer> COMPUTED_FUELS = null;

    private static final List<Consumer<PotionBrewing.Builder>> BREWING_RECIPES = new ArrayList<>();

    public static void registerFlammable(int flameAbility, int spreadSpeed, Collection<Block> blocks) {
        COMPUTED_FLAMMABLES = null;
        for (Block block : blocks) {
            FLAMMABLES.put(block, new FlammableEntry(flameAbility, spreadSpeed));
        }
    }

    public static void registerFlammable(int burn, int spread, TagKey<Block> tag) {
        COMPUTED_FLAMMABLES = null;
        FLAMMABLE_TAGS.put(tag, new FlammableEntry(burn, spread));
    }

    public static void registerComposting(float chance, Collection<Item> items) {
        COMPUTED_COMPOSTABLES = null;
        for (Item item : items) {
            COMPOSTABLES.put(item, chance);
        }
    }

    public static void registerComposting(float chance, TagKey<Item> tag) {
        COMPUTED_COMPOSTABLES = null;
        COMPOSTABLE_TAGS.put(tag, chance);
    }

    public static void registerFuel(int burnTick, Collection<Item> items) {
        COMPUTED_FUELS = null;
        for (Item i : items) {
            FUELS.put(i, burnTick);
        }
    }

    public static void registerFuel(int burnTick, TagKey<Item> tag) {
        COMPUTED_FUELS = null;
        FUEL_TAGS.put(tag, burnTick);
    }

    public static void registerBrewingRecipe(Consumer<PotionBrewing.Builder> builder) {
        BREWING_RECIPES.add(builder);
    }

    static {
        EventBusUtils.FML().addListener(BlockInteractionRegistryImpl::onTagsUpdated);
        EventBusUtils.SC().addListener(BlockInteractionRegistryImpl::onRegisterBrewingRecipes);
    }

    private static void onTagsUpdated(TagsUpdatedEvent e) {
        COMPUTED_FLAMMABLES = null;
        COMPUTED_COMPOSTABLES = null;
        COMPUTED_FUELS = null;
    }

    private static void onRegisterBrewingRecipes(RegisterBrewingRecipesEvent e) {
        for (Consumer<PotionBrewing.Builder> consumer : BREWING_RECIPES) {
            consumer.accept(e.getBuilder());
        }
    }

    @ApiStatus.Internal
    public static Map<Block, FlammableEntry> getFlammables() {
        if (COMPUTED_FLAMMABLES != null) {
            return COMPUTED_FLAMMABLES;
        }

        Map<Block, FlammableEntry> computed = new HashMap<>();

        for (Map.Entry<TagKey<Block>, FlammableEntry> entry : FLAMMABLE_TAGS.entrySet()) {
            for (Block block : RegistryUtils.getEntries(BuiltInRegistries.BLOCK, entry.getKey())) {
                computed.put(block, entry.getValue());
            }
        }

        computed.putAll(FLAMMABLES);
        COMPUTED_FLAMMABLES = computed;
        return COMPUTED_FLAMMABLES;
    }

    @ApiStatus.Internal
    public static Map<Item, Float> getCompostables() {
        if (COMPUTED_COMPOSTABLES != null) {
            return COMPUTED_COMPOSTABLES;
        }

        Map<Item, Float> computed = new HashMap<>();
        for (Map.Entry<TagKey<Item>, Float> entry : COMPOSTABLE_TAGS.entrySet()) {
            for (Item item : RegistryUtils.getEntries(BuiltInRegistries.ITEM, entry.getKey())) {
                computed.put(item, entry.getValue());
            }
        }

        computed.putAll(COMPOSTABLES);
        COMPUTED_COMPOSTABLES = computed;
        return COMPUTED_COMPOSTABLES;
    }

    @ApiStatus.Internal
    public static Map<Item, Integer> getFuels() {
        if (COMPUTED_FUELS != null) {
            return COMPUTED_FUELS;
        }

        Map<Item, Integer> computed = new HashMap<>();
        for (Map.Entry<TagKey<Item>, Integer> entry : FUEL_TAGS.entrySet()) {
            for (Item item : RegistryUtils.getEntries(BuiltInRegistries.ITEM, entry.getKey())) {
                computed.put(item, entry.getValue());
            }
        }

        computed.putAll(FUELS);
        COMPUTED_FUELS = computed;
        return COMPUTED_FUELS;
    }

    @ApiStatus.Internal
    public record FlammableEntry(int flameAbility, int spreadSpeed) {
    }

    private BlockInteractionRegistryImpl() {
    }
}
