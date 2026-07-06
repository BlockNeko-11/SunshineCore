package io.github.blockneko11.sunshinecore.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.blockneko11.sunshinecore.util.ResourceLoc;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 * Registrar API, which is used to registering objects to the game.
 * <p>
 * Different loaders have different behaviors for registering objects:
 * <ul>
 *   <li>In Fabric, objects are immediately registered when calling {@link #register(Registry, String, Supplier)}.
 *   <li>In Forge / NeoForge, objects wil be registered later.
 * </ul>
 */
public abstract class Registrar {
    protected final String modId;
    private boolean registered = false;

    protected Registrar(String modId) {
        this.modId = modId;
    }

    /**
     * Create a new registrar for a mod.
     * @param modId mod id
     * @return a Registrar instance
     */
    @ExpectPlatform
    public static Registrar create(String modId) {
        throw new AssertionError();
    }

    /**
     * Register an object to the game.
     * @param registry a {@link Registry} instance. See {@link BuiltInRegistries}
     * @param id the in-game id of the object
     * @param entry the object to register
     * @return a {@link Supplier} instance of the registered object
     * @param <R> the type of the registry
     * @param <T> the type of the object
     */
    public abstract <R, T extends R> RegistryHolder<R, T> register(Registry<R> registry, String id, Supplier<T> entry);

    public final RegistryHolder<Item, Item> simpleItem(String id) {
        return this.simpleItem(id, UnaryOperator.identity());
    }

    public final RegistryHolder<Item, Item> simpleItem(String id, UnaryOperator<Item.Properties> operator) {
        return this.register(BuiltInRegistries.ITEM, id, () -> new Item(operator.apply(new Item.Properties())));
    }

    public final <T extends Block> RegistryHolder<Item, Item> blockItem(String id, Supplier<T> block) {
        return this.blockItem(id, block, UnaryOperator.identity());
    }

    public final <T extends Block> RegistryHolder<Item, Item> blockItem(String id, Supplier<T> block, UnaryOperator<Item.Properties> operator) {
        return this.register(BuiltInRegistries.ITEM, id, () -> new BlockItem(block.get(), operator.apply(new Item.Properties())));
    }

    public final RegistryHolder<Block, Block> simpleBlock(String id) {
        return this.simpleBlock(id, UnaryOperator.identity());
    }

    public final RegistryHolder<Block, Block> simpleBlock(String id, UnaryOperator<BlockBehaviour.Properties> operator) {
        return this.register(BuiltInRegistries.BLOCK, id, () -> new Block(operator.apply(BlockBehaviour.Properties.of())));
    }

    public final <T> TagKey<T> tag(Registry<T> registry, String id) {
        return TagKey.create(registry.key(), ResourceLoc.id(this.modId, id));
    }

    /**
     * Register all objects to the game. Just call it once in your mod's entrypoint.
     */
    public final void register() {
        if (this.registered) {
            throw new IllegalStateException("Cannot register twice for the mod " + this.modId);
        }

        this.registered = true;
        this.bootstrap();
    }

    protected abstract void bootstrap();
}
