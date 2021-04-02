package com.qtech.forgemods.core.modules.debugMenu.pages;

import com.qtech.forgemods.core.modules.debugMenu.DebugEntry;
import com.qtech.forgemods.core.modules.debugMenu.DebugPage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;

import java.util.ArrayList;
import java.util.List;

public class LivingEntityPage extends EntityPage {
    public LivingEntityPage(String modId, String name) {
        super(modId, name);
    }

    @Override
    public List<DebugEntry> getLinesRight() {
        List<DebugEntry> list = new ArrayList<>();
        PlayerEntity player = Minecraft.getInstance().player;
        if (player == null) {
            list.add(new DebugEntry("$PLAYER$", null));
            return list;
        }

        float f = player.rotationPitch;
        float f1 = player.rotationYaw;
        Vector3d vec3d = player.getEyePosition(1.0F);

        float f2 = MathHelper.cos(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f3 = MathHelper.sin(-f1 * ((float) Math.PI / 180F) - (float) Math.PI);
        float f4 = -MathHelper.cos(-f * ((float) Math.PI / 180F));
        float f5 = MathHelper.sin(-f * ((float) Math.PI / 180F));
        float f6 = f3 * f4;
        float f7 = f2 * f4;

        double d0 = 16;

        Vector3d vec3d1 = vec3d.add((double) f6 * d0, (double) f5 * d0, (double) f7 * d0);
        Minecraft mc = Minecraft.getInstance();
        ClientWorld world = Minecraft.getInstance().world;
        if (world == null) {
            list.add(new DebugEntry("$WORLD$", null));
            return list;
        }
        RayTraceResult raytraceresult = Minecraft.getInstance().world.rayTraceBlocks(new RayTraceContext(vec3d, vec3d1, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, player));
        if (raytraceresult.getType() != RayTraceResult.Type.MISS) {
            vec3d1 = raytraceresult.getHitVec();
        }

        RayTraceResult rayTraceResult1 = ProjectileHelper.rayTraceEntities(world, player, vec3d, vec3d1, player.getBoundingBox().grow(16.0D), entity -> !entity.equals(player));
        if (rayTraceResult1 != null) {
            raytraceresult = rayTraceResult1;
        }
        if (raytraceresult.getType() == RayTraceResult.Type.ENTITY) {
            @SuppressWarnings("ConstantConditions") EntityRayTraceResult entityRayTraceResult = (EntityRayTraceResult) raytraceresult;

            Entity entity = entityRayTraceResult.getEntity();
            if (entity instanceof LivingEntity) {
                LivingEntity living = (LivingEntity) entity;
                list.add(new DebugEntry("health", living::getHealth));
                list.add(new DebugEntry("maxHealth", living::getMaxHealth));
                list.add(new DebugEntry("absorptionAmount", living::getAbsorptionAmount));
                list.add(new DebugEntry("movementSpeed", living::getMovementSpeed));
                list.add(new DebugEntry("aiMoveSpeed", living::getAIMoveSpeed));
                list.add(new DebugEntry("activeHand", living::getActiveHand));
                list.add(new DebugEntry("attackingEntity", living::getAttackingEntity));
                list.add(new DebugEntry("itemInUseCount", living::getItemInUseCount));
                list.add(new DebugEntry("lastAttackedEntity", living::getLastAttackedEntity));
                list.add(new DebugEntry("leashPosition", () -> living.getLeashPosition(mc.getRenderPartialTicks())));
                list.add(new DebugEntry("pose", living::getPose));
                list.add(new DebugEntry("scale", living::getRenderScale));
                list.add(new DebugEntry("revengeTarget", living::getRevengeTarget));
                list.add(new DebugEntry("totalArmorValue", living::getTotalArmorValue));
            } else {
                list.add(new DebugEntry("$LIVING_ENTITY$", null));
                return list;
            }
        } else {
            list.add(new DebugEntry("$ENTITY$", null));
            return list;
        }
        return list;
    }

    @Override
    public boolean hasRequiredComponents() {
        return Minecraft.getInstance().player != null;
    }
}
