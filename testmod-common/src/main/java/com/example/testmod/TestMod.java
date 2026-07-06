package com.example.testmod;

import com.example.testmod.data.TestTagProvider;
import com.example.testmod.data.TestTranslationProvider;
import com.example.testmod.registry.TestRegistry;
import io.github.blockneko11.sunshinecore.SunshineCore;
import io.github.blockneko11.sunshinecore.block.FlammableRegistry;
import io.github.blockneko11.sunshinecore.command.CommandRegistry;
import io.github.blockneko11.sunshinecore.data.SDataGeneration;
import io.github.blockneko11.sunshinecore.entity.villager.SimpleVillagerTrade;
import io.github.blockneko11.sunshinecore.entity.villager.VillagerInteractionRegistry;
import io.github.blockneko11.sunshinecore.event.initialize.SetupEvent;
import io.github.blockneko11.sunshinecore.item.CompostingRegistry;
import io.github.blockneko11.sunshinecore.item.FuelRegistry;
import io.github.blockneko11.sunshinecore.item.tool.ToolInteractionRegistry;
import io.github.blockneko11.sunshinecore.server.event.level.ServerLevelEvent;
import io.github.blockneko11.sunshinecore.loader.Platform;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class TestMod {
    public static final String MOD_ID = "testmod";
    public static final String MOD_NAME = "Test Mod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static void init() {
        LOGGER.info("Hello from Test Mod!");

        LOGGER.info("Loader: {}, Env: {}, Dev mode: {}", Platform.getLoader(), Platform.getSide(), Platform.isDev());

        LOGGER.info("Sunshine Core mod loaded? {}", Platform.isModLoaded(SunshineCore.MOD_ID));
        LOGGER.info("Test Mod version: {}", Platform.getMod(MOD_ID).getVersion());

        TestRegistry.init();
        SetupEvent.EVENT.register(TestMod::onSetup);
        ServerLevelEvent.LOAD.register(l -> LOGGER.info("Level loaded: {}", l.dimension().location()));

        CommandRegistry.registerCommand((dispatcher, registryAccess, environment) -> {
            dispatcher.register(Commands.literal("test")
                    .executes(ctx -> {
                        ctx.getSource().sendSuccess(() -> Component.literal("Hello"), true);
                        return 1;
                    }));
        });
    }

    private static void onSetup() {
        ToolInteractionRegistry.registerFlattenable(TestRegistry.TEST_BLOCK.get(), TestRegistry.TEST_BLOCK_FLATTENED.get().defaultBlockState());
        ToolInteractionRegistry.registerTillable(TestRegistry.TEST_BLOCK.get(),
                HoeItem::onlyIfAirAbove,
                ctx -> Block.popResourceFromFace(ctx.getLevel(), ctx.getClickedPos(), ctx.getClickedFace(), new ItemStack(Items.BEDROCK)));

        FuelRegistry.register(50, TestRegistry.TEST_BLOCK_ITEM.get());
        CompostingRegistry.register(0.4f, TestRegistry.TEST_BLOCK_ITEM.get());
        FlammableRegistry.register(5, 5, TestRegistry.TEST_BLOCK_FLAMMABLE.get());

        VillagerInteractionRegistry.registerWanted(TestRegistry.TEST_BLOCK_ITEM.get());
        VillagerInteractionRegistry.registerCompostable(TestRegistry.TEST_BLOCK_ITEM.get());
    }

    public static void initDataGen(SDataGeneration gen) {
        gen.addProvider(TestTagProvider.Block::new);
        gen.addProvider(TestTagProvider.Item::new);
        gen.addProvider(TestTranslationProvider::new);

        gen.run();
    }
}
