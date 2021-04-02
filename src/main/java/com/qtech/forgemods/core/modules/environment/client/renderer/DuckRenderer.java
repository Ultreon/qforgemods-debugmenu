package com.qtech.forgemods.core.modules.environment.client.renderer;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.modules.environment.client.model.FreeChickenModel;
import com.qtech.forgemods.core.modules.environment.entities.DuckEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

/**
 * Duck entity renderer class.
 *
 * @author Qboi123
 */
@OnlyIn(Dist.CLIENT)
public class DuckRenderer extends MobRenderer<DuckEntity, FreeChickenModel<DuckEntity>> {
    private static final ResourceLocation DUCK_TEXTURES = new ResourceLocation(QFMCore.modId, "textures/entity/duck.png");

    public DuckRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new FreeChickenModel<>(), 0.5F);
    }

    /**
     * Returns the location of an entity's texture.
     */
    public @NotNull ResourceLocation getEntityTexture(@NotNull DuckEntity entity) {
        return DUCK_TEXTURES;
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(DuckEntity livingBase, float partialTicks) {
        float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.wingRotation);
        float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.destPos);
        return (MathHelper.sin(f) + 1.0F) * f1;
    }
}
