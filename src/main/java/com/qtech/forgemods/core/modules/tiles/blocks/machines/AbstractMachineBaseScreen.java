package com.qtech.forgemods.core.modules.tiles.blocks.machines;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.qtech.modlib.api.RedstoneMode;
import com.qtech.forgemods.core.modules.ui.button.RedstoneModeButton;
import com.qtech.forgemods.core.common.gui.MachineUpgradeSlot;
import com.qtech.forgemods.core.network.Network;
import com.qtech.forgemods.core.network.SetRedstoneModePacket;
import com.qtech.forgemods.core.util.TextUtils;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.gui.widget.Widget;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.IFormattableTextComponent;
import net.minecraft.util.text.ITextComponent;

public abstract class AbstractMachineBaseScreen<C extends AbstractMachineBaseContainer<?>> extends ContainerScreen<C> {
    public AbstractMachineBaseScreen(C screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    public abstract ResourceLocation getGuiTexture();

    @Override
    protected void init() {
        super.init();
        this.addButton(new RedstoneModeButton(container, this.guiLeft - 16, this.guiTop, 16, 16, button -> {
            RedstoneMode mode = ((RedstoneModeButton) button).getMode();
            Network.channel.sendToServer(new SetRedstoneModePacket(mode));
        }));
    }

    @Override
    protected void renderHoveredTooltip(MatrixStack matrixStack, int x, int y) {
        if (isPointInRegion(153, 17, 13, 51, x, y)) {
            IFormattableTextComponent text = TextUtils.energyWithMax(container.getEnergyStored(), container.getMaxEnergyStored());
            renderTooltip(matrixStack, text, x, y);
        }
        if (hoveredSlot instanceof MachineUpgradeSlot && !hoveredSlot.getHasStack()) {
            renderTooltip(matrixStack, TextUtils.translate("misc", "upgradeSlot"), x, y);
        }
        super.renderHoveredTooltip(matrixStack, x, y);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(MatrixStack matrixStack, float partialTicks, int x, int y) {
        if (minecraft == null) return;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(getGuiTexture());
        int xPos = (this.width - this.xSize) / 2;
        int yPos = (this.height - this.ySize) / 2;
        blit(matrixStack, xPos, yPos, 0, 0, this.xSize, this.ySize);

        // Upgrade slots
        for (int i = 0; i < this.container.tileEntity.tier.getUpgradeSlots(); ++i) {
            blit(matrixStack, xPos + 5 + 18 * i, yPos - 11, 190, 0, 18, 14);
        }
    }

    @Override
    protected void drawGuiContainerForegroundLayer(MatrixStack matrixStack, int x, int y) {
        this.font.drawString(matrixStack, this.title.getString(), 8.0F, 6.0F, 4210752);
//        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);

        for (Widget widget : this.buttons) {
            if (widget.isHovered() && widget instanceof RedstoneModeButton) {
                RedstoneMode mode = ((RedstoneModeButton) widget).getMode();
                renderTooltip(matrixStack, TextUtils.translate("misc", "redstoneMode", mode.name()), x - guiLeft, y - guiTop);
            }
        }
    }
}
