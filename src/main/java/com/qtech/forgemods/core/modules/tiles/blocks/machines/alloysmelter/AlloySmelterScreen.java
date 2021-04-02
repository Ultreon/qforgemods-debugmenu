package com.qtech.forgemods.core.modules.tiles.blocks.machines.alloysmelter;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.AbstractMachineScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class AlloySmelterScreen extends AbstractMachineScreen<AlloySmelterContainer> {
    public static final ResourceLocation TEXTURE = QFMCore.rl("textures/gui/alloy_smelter.png");

    public AlloySmelterScreen(AlloySmelterContainer containerIn, PlayerInventory playerInventoryIn, ITextComponent titleIn) {
        super(containerIn, playerInventoryIn, titleIn);
    }

    @Override
    public ResourceLocation getGuiTexture() {
        return TEXTURE;
    }

    @Override
    protected int getProgressArrowPosX(int guiPosX) {
        return guiPosX + 92;
    }

    @Override
    protected int getProgressArrowPosY(int guiPosY) {
        return guiPosY + 35;
    }
}
