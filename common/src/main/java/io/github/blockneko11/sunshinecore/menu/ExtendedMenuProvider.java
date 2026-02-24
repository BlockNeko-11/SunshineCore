package io.github.blockneko11.sunshinecore.menu;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.MenuProvider;

public interface ExtendedMenuProvider extends MenuProvider {
    void saveExtraData(FriendlyByteBuf buf);
}
