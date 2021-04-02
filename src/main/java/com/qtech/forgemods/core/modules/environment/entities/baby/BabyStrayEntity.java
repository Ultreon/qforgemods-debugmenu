package com.qtech.forgemods.core.modules.environment.entities.baby;

import com.qtech.forgemods.core.modules.items.ModItemsAlt;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.monster.StrayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nonnull;
import java.util.Random;

/**
 * Baby stray entity class.
 *
 * @author QForgeUtils community.
 */
public class BabyStrayEntity extends StrayEntity implements IBabyEntity {

    private static final DataParameter<Boolean> IS_CHILD = EntityDataManager.createKey(BabyStrayEntity.class, DataSerializers.BOOLEAN);

    public BabyStrayEntity(EntityType<BabyStrayEntity> type, World world) {
        super(type, world);
        setChild(true);
    }

    //Copy of stray spawn restrictions
    public static boolean spawnRestrictions(EntityType<BabyStrayEntity> type, IServerWorld world, SpawnReason reason, BlockPos pos, Random random) {
        return canMonsterSpawnInLight(type, world, reason, pos, random) && (reason == SpawnReason.SPAWNER || world.canSeeSky(pos));
    }

    @Override
    protected void registerData() {
        super.registerData();
        getDataManager().register(IS_CHILD, false);
    }

    @Override
    public boolean isChild() {
        return getDataManager().get(IS_CHILD);
    }

    @Override
    public void setChild(boolean child) {
        setChild(IS_CHILD, child);
    }

    @Override
    public void notifyDataManagerChange(@Nonnull DataParameter<?> key) {
        if (IS_CHILD.equals(key)) {
            recalculateSize();
        }
        super.notifyDataManagerChange(key);
    }

    @Override
    protected int getExperiencePoints(@Nonnull PlayerEntity player) {
        if (isChild()) {
            experienceValue = (int) (experienceValue * 2.5F);
        }
        return super.getExperiencePoints(player);
    }

    @Override
    public double getYOffset() {
        return isChild() ? 0 : super.getYOffset();
    }

    @Override
    protected float getStandingEyeHeight(@Nonnull Pose pose, @Nonnull EntitySize size) {
        return this.isChild() ? 0.93F : super.getStandingEyeHeight(pose, size);
    }

    @Override
    public ItemStack getPickedResult(RayTraceResult target) {
        return new ItemStack(ModItemsAlt.BABY_STRAY_SPAWN_EGG.asItem());
    }

    @Nonnull
    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }
}