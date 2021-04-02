package com.qtech.forgemods.core.modules.environment.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.environment.client.model.FreeCreeperModel;
import com.qtech.forgemods.core.modules.environment.client.renderer.layers.CustomCreeperChargeLayer;
import com.qtech.forgemods.core.modules.environment.entities.FireCreeperEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

/**
 * Fire creeper entity renderer class.
 *
 * @author Qboi123
 */
@OnlyIn(Dist.CLIENT)
public class FireCreeperRenderer extends MobRenderer<FireCreeperEntity, FreeCreeperModel<FireCreeperEntity>> {
    private static final ResourceLocation CREEPER_TEXTURES = new ResourceLocation(QFMCore.modId, "textures/entity/creeper/fire.png");
//    private static final RenderType RENDER_TYPE = RenderType.getEyes(new ResourceLocation(QForgeUtils.MOD_ID, "textures/entity/creeper/fire_eyes.png"));

    public FireCreeperRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FreeCreeperModel<>(), 0.5F);
        this.addLayer(new CustomCreeperChargeLayer<>(this, entityModel));
    }

    @Override
    protected void preRenderCallback(FireCreeperEntity entityLivingBaseIn, MatrixStack matrixStackIn, float partialTickTime) {
        float f = entityLivingBaseIn.getCreeperFlashIntensity(partialTickTime);
        float f1 = 1.0F + MathHelper.sin(f * 100.0F) * f * 0.01F;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        f = f * f;
        f = f * f;
        float f2 = (1.0F + f * 0.4F) * f1;
        float f3 = (1.0F + f * 0.1F) / f1;
        matrixStackIn.scale(f2, f3, f2);
    }

    @Override
    protected float getOverlayProgress(FireCreeperEntity livingEntityIn, float partialTicks) {
        float f = livingEntityIn.getCreeperFlashIntensity(partialTicks);
        return (int) (f * 10.0F) % 2 == 0 ? 0.0F : MathHelper.clamp(f, 0.5F, 1.0F);
    }

    /**
     * Returns the location of an entity's texture.
     */
    @Override
    public @NotNull ResourceLocation getEntityTexture(@NotNull FireCreeperEntity entity) {
        return CREEPER_TEXTURES;
    }
}
