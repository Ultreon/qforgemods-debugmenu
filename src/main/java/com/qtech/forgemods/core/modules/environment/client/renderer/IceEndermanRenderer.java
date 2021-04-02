package com.qtech.forgemods.core.modules.environment.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.environment.client.model.FreeEndermanModel;
import com.qtech.forgemods.core.modules.environment.client.renderer.layers.CustomEndermanEyesLayer;
import com.qtech.forgemods.core.modules.environment.client.renderer.layers.CustomHeldBlockLayer;
import com.qtech.forgemods.core.modules.environment.entities.IceEndermanEntity;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * Ice enderman entity renderer class.
 *
 * @author Qboi123
 */
@OnlyIn(Dist.CLIENT)
public class IceEndermanRenderer extends MobRenderer<IceEndermanEntity, FreeEndermanModel<IceEndermanEntity>> {
    private static final ResourceLocation ENDERMAN_TEXTURES = new ResourceLocation(QFMCore.modId, "textures/entity/enderman/ice.png");

    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(QFMCore.modId, "textures/entity/enderman/ice_eyes.png"));

    private final Random rnd = new Random();

    public IceEndermanRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FreeEndermanModel<>(0.0F), 0.5F);

        this.addLayer(new CustomEndermanEyesLayer<>(this, RENDER_TYPE));
        this.addLayer(new CustomHeldBlockLayer<>(this));
    }

    @Override
    public void render(IceEndermanEntity entityIn, float entityYaw, float partialTicks, @NotNull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn) {
        BlockState blockstate = entityIn.getHeldBlockState();
        FreeEndermanModel<IceEndermanEntity> endermanModel = this.getEntityModel();

        endermanModel.isCarrying = blockstate != null;
        endermanModel.isAttacking = entityIn.isScreaming();

        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public @NotNull Vector3d getRenderOffset(IceEndermanEntity entityIn, float partialTicks) {
        if (entityIn.isScreaming()) {
            return new Vector3d(this.rnd.nextGaussian() * 0.02D, 0.0D, this.rnd.nextGaussian() * 0.02D);
        } else {
            return super.getRenderOffset(entityIn, partialTicks);
        }
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public @NotNull ResourceLocation getEntityTexture(@NotNull IceEndermanEntity entity) {
        return ENDERMAN_TEXTURES;
    }
}
