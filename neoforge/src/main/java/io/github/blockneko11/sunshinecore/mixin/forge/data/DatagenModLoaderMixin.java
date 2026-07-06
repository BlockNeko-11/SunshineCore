package io.github.blockneko11.sunshinecore.mixin.forge.data;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.data.loading.DatagenModLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;

// refer to https://github.com/Fuzss/puzzleslib/blob/main/1.21.1/NeoForge/src/main/java/fuzs/puzzleslib/neoforge/mixin/DatagenModLoaderNeoForgeMixin.java
// under the MPL-2.0 licence
@Mixin(value = DatagenModLoader.class, remap = false)
public abstract class DatagenModLoaderMixin {
    @Unique
    private static final Logger sc$LOGGER = LoggerFactory.getLogger(DatagenModLoaderMixin.class);

//    @Shadow
//    private static boolean runningDataGen;

    @WrapOperation(
            method = "begin",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/neoforged/neoforge/data/event/GatherDataEvent$DataGeneratorConfig;runAll()V"
            )
    )
    private static void onRunAllDataGen$fixDataGenRun(GatherDataEvent.DataGeneratorConfig config, Operation<Void> runAll) {
        try {
            runAll.call(config);
            System.exit(0);
        } catch (Throwable t) {
            sc$LOGGER.error("Failed to run data generation", t);
            System.exit(-1);
        }
    }
}
