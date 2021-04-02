package com.qtech.forgemods.core.modules.tiles.tileentities.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.generator.lava.LavaGeneratorTileEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import org.jetbrains.annotations.NotNull;

public class LavaGeneratorTileRenderer extends TileEntityRenderer<LavaGeneratorTileEntity> {
    public LavaGeneratorTileRenderer(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(@NotNull LavaGeneratorTileEntity tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
    }
}
