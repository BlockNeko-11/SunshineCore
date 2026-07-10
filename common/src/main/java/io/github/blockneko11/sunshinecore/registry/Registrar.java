package io.github.blockneko11.sunshinecore.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import io.github.blockneko11.sunshinecore.util.ResourceLoc;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.ApiStatus;

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
     * @param entry the object to register
     * @return a {@link Supplier} instance of the registered object
     * @param <R> the type of the registry
     * @param <T> the type of the object
     */
    public abstract <R, T extends R> RegistryHolder<R, T> register(Registry<R> registry, ResourceLocation id, Supplier<T> entry);

    public <R, T extends R> RegistryHolder<R, T> register(Registry<R> registry, String id, Supplier<T> entry) {
        return this.register(registry, this.loc(id), entry);
    }

    public <R, T extends R> RegistryHolder<R, T> register(Registry<R> registry, String id, Function<ResourceLocation, T> factory) {
        ResourceLocation loc = this.loc(id);
        return this.register(registry, loc, () -> factory.apply(loc));
    }

    public final <T> TagKey<T> tag(Registry<T> registry, String id) {
        return TagKey.create(registry.key(), this.loc(id));
    }

    public final ResourceLocation loc(String id) {
        return ResourceLoc.id(this.modId, id);
    }

    public final <R> ResourceKey<R> regKey(Registry<R> registry, String id) {
        return this.regKey(registry, this.loc(id));
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
