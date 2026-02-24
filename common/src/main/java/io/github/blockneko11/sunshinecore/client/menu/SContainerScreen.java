package io.github.blockneko11.sunshinecore.client.menu;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;

@Environment(EnvType.CLIENT)
public class SContainerScreen<T extends AbstractContainerMenu> extends AbstractContainerScreen<T> {
    private final ResourceLocation texture;

    public SContainerScreen(T menu, Inventory playerInventory, Component title, ResourceLocation texture) {
        super(menu, playerInventory, title);
        this.texture = texture;
    }

    @Override
    public void renderBg(GuiGraphics guiGraphics, float partialTick, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, this.texture);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;
        guiGraphics.blit(this.texture, x, y, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        renderBg(guiGraphics, partialTick, mouseX, mouseY);
        super.render(guiGraphics, mouseX, mouseY, partialTick);
        renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    public void init() {
        super.init();
    }
}
