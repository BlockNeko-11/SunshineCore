package io.github.blockneko11.sunshinecore.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.blockneko11.sunshinecore.item.tab.CreativeModeTabUtils;
import io.github.blockneko11.sunshinecore.util.Id;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import org.jetbrains.annotations.ApiStatus;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Registrar API, which is used to registering objects to the game.
 * <p>
 * Different loaders have different behaviors for registering objects:
 * <ul>
 *   <li>In Fabric, objects will be immediately registered.
 *   <li>In Forge / NeoForge, objects wil be registered later.
 * </ul>
 */
public abstract class Registrar {
    protected String modId;
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
     * @param entry a {@link Supplier} of the object to register
     * @return a {@link RegistryHolder} of the registered object
     * @param <R> the type of the registry
     * @param <T> the type of the object
     */
    public abstract <R, T extends R> RegistryHolder<R, T> register(Registry<R> registry, String id, Supplier<T> entry);

    /**
     * Register an object to the game.
     * @param registry a {@link Registry} instance. See {@link BuiltInRegistries}
     * @param id the in-game id of the object
     * @param factory a factory function
     * @return a {@link RegistryHolder} of the registered object
     * @param <R> the type of the registry
     * @param <T> the type of the object
     */
    public <R, T extends R> RegistryHolder<R, T> register(Registry<R> registry, String id, Function<ResourceLocation, T> factory) {
        ResourceLocation loc = this.id(id);
        return this.register(registry, id, () -> factory.apply(loc));
    }

    // block helper methods

    public final <B extends Block> RegistryHolder<Block, B> block(String id, Supplier<B> block) {
        return this.register(BuiltInRegistries.BLOCK, id, block);
    }

    public final <B extends Block> RegistryHolder<Block, B> block(String id, Function<ResourceLocation, B> factory) {
        return this.register(BuiltInRegistries.BLOCK, id, factory);
    }

    public final <B extends Block> RegistryHolder<Block, B> block(String id, BlockFactory<B> factory, BlockBehaviour.Properties props) {
        return this.register(BuiltInRegistries.BLOCK, id, identifier -> factory.apply(props));
    }

    public final RegistryHolder<Block, Block> block(String id, BlockBehaviour.Properties props) {
        return this.block(id, Block::new, props);
    }

    public final RegistryHolder<Block, Block> block(String id) {
        return this.block(id, BlockBehaviour.Properties.of());
    }

    // item helper methods

    public final <I extends Item> RegistryHolder<Item, I> item(String id, Supplier<I> item) {
        return this.register(BuiltInRegistries.ITEM, id, item);
    }

    public final <I extends Item> RegistryHolder<Item, I> item(String id, Function<ResourceLocation, I> factory) {
        return this.register(BuiltInRegistries.ITEM, id, factory);
    }

    public final <I extends Item> RegistryHolder<Item, I> item(String id, ItemFactory<I> factory, Item.Properties props) {
        return this.register(BuiltInRegistries.ITEM, id, identifier -> factory.apply(props));
    }

    public final RegistryHolder<Item, Item> item(String id, Item.Properties props) {
        return this.item(id, Item::new, props);
    }

    public final RegistryHolder<Item, Item> item(String id) {
        return this.item(id, new Item.Properties());
    }

    public final RegistryHolder<Item, BlockItem> blockItem(String id, Supplier<? extends Block> block, Item.Properties props) {
        return this.item(id, identifier -> new BlockItem(block.get(), props));
    }

    public final RegistryHolder<Item, BlockItem> blockItem(String id, Supplier<? extends Block> block) {
        return this.blockItem(id, block, new Item.Properties());
    }

    // block entity helper methods

    public final <E extends BlockEntity> RegistryHolder<BlockEntityType<?>, BlockEntityType<E>> blockEntity(String id, Supplier<BlockEntityType<E>> type) {
        return this.register(BuiltInRegistries.BLOCK_ENTITY_TYPE, id, type);
    }

    @SafeVarargs
    public final <E extends BlockEntity> RegistryHolder<BlockEntityType<?>, BlockEntityType<E>> blockEntity(String id, BlockEntityType.BlockEntitySupplier<E> factory, Supplier<? extends Block>... blocks) {
        Block[] array = Arrays.stream(blocks)
                .map(Supplier::get)
                .toArray(Block[]::new);
        return this.blockEntity(id, () -> BlockEntityType.Builder.of(factory, array).build(null));
    }

    // creative mod tab helper methods

    public final RegistryHolder<CreativeModeTab, CreativeModeTab> tab(String id, Supplier<CreativeModeTab> tab) {
        return this.register(BuiltInRegistries.CREATIVE_MODE_TAB, id, tab);
    }

    public final RegistryHolder<CreativeModeTab, CreativeModeTab> tab(String id, Component title, Supplier<ItemStack> icon) {
        return this.tab(id, () -> CreativeModeTabUtils.create(title, icon));
    }

    public final RegistryHolder<CreativeModeTab, CreativeModeTab> tab(String id, Consumer<CreativeModeTab.Builder> factory) {
        return this.tab(id, () -> CreativeModeTabUtils.create(factory));
    }

    // generic helper methods

    /**
     * Create a tag key.
     * @param registry a {@link Registry} instance. See {@link BuiltInRegistries}
     * @param id the in-game id of the tag
     * @return a tag key
     * @param <T> the type of the registry
     */
    public final <T> TagKey<T> tag(Registry<T> registry, String id) {
        return TagKey.create(registry.key(), this.id(id));
    }

    public final ResourceLocation id(String id) {
        return Id.id(this.modId, id);
    }

    public final <R> ResourceKey<R> regKey(Registry<R> registry, String id) {
        return this.regKey(registry, this.id(id));
    }

    public final <R> ResourceKey<R> regKey(Registry<R> registry, ResourceLocation id) {
        return ResourceKey.create(registry.key(), id);
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

    @ApiStatus.OverrideOnly
    protected abstract void bootstrap();
}
