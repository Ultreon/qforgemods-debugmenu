package com.qtech.forgemods.core.modules.tiles.blocks.machines.generator.diesel;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.util.render.RenderUtils;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.AbstractMachineBaseScreen;
import com.qtech.forgemods.core.util.TextUtils;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class DieselGeneratorScreen extends AbstractMachineBaseScreen<DieselGeneratorContainer> {
    public static final ResourceLocation TEXTURE = QFMCore.rl("textures/gui/fluid_generator.png");

    public DieselGeneratorScreen(DieselGeneratorContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return TEXTURE;
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderHoveredTooltip(matrixStack, mouseX, mouseY);
    }

    @Override
    protected void renderHoveredTooltip(MatrixStack matrixStack, int x, int y) {
        if (isPointInRegion(135, 17, 13, 51, x, y)) {
            ITextComponent text = TextUtils.fluidWithMax(container.getFluidInTank(), 4000);
            renderTooltip(matrixStack, text, x, y);
        }
        if (isPointInRegion(153, 17, 13, 51, x, y)) {
            ITextComponent text = TextUtils.energyWithMax(container.getEnergyStored(), container.getMaxEnergyStored());
            renderTooltip(matrixStack, text, x, y);
        }
        super.renderHoveredTooltip(matrixStack, x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        super.drawGuiContainerBackgroundLayer(matrixStack, partialTicks, x, y);

        if (minecraft == null) return;
        int xPos = (this.width - this.xSize) / 2;
        int yPos = (this.height - this.ySize) / 2;

        // Energy meter
        int energyBarHeight = container.getEnergyBarHeight();
        if (energyBarHeight > 0) {
            blit(matrixStack, xPos + 154, yPos + 68 - energyBarHeight, 176, 31, 12, energyBarHeight);
        }

        // Fluid tank
        RenderUtils.renderGuiTank(container.getFluidInTank(), 4000, xPos + 136, yPos + 18, 0, 12, 50);
    }
}
