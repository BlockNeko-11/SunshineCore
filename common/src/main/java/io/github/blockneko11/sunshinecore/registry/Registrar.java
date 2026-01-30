package io.github.blockneko11.sunshinecore.registry;

import dev.architectury.injectables.annotations.ExpectPlatform;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public abstract class Registrar {
    protected final String modId;
    private boolean registered = false;

    protected Registrar(String modId) {
        this.modId = modId;
    }

    @ExpectPlatform
    public static Registrar create(String modId) {
        throw new AssertionError();
    }

    public abstract <R, T extends R> Supplier<T> register(Registry<R> registry, String id, Supplier<T> entry);

    public Supplier<Item> simpleItem(String id) {
        return this.simpleItem(id, UnaryOperator.identity());
    }

    public Supplier<Item> simpleItem(String id, UnaryOperator<Item.Properties> operator) {
        return this.register(BuiltInRegistries.ITEM, id, () -> new Item(operator.apply(new Item.Properties())));
    }

    public Supplier<Block> simpleBlock(String id) {
        return this.simpleBlock(id, UnaryOperator.identity());
    }

    public Supplier<Block> simpleBlock(String id, UnaryOperator<Block.Properties> operator) {
        return this.register(BuiltInRegistries.BLOCK, id, () -> new Block(operator.apply(Block.Properties.of())));
    }

    public void register() {
        if (this.registered) {
            throw new IllegalStateException("Cannot regster twice for mod " + this.modId);
        }

        this.registered = true;
        this.bootstrap();
    }

    protected abstract void bootstrap();
}
