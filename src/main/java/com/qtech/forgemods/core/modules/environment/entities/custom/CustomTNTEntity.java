package com.qtech.forgemods.core.modules.environment.entities.custom;

import com.qtech.forgemods.core.QFMCore;
import com.qtech.forgemods.core.common.TNTProperties;
import com.qtech.forgemods.core.modules.environment.ModEntities;
import com.qtech.forgemods.core.modules.tiles.blocks.CustomTNTBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.TNTEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.NBTUtil;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public final class CustomTNTEntity extends TNTEntity {
    private final TNTProperties properties;
    private @Nullable BlockState blockState;
    private static final Supplier<BlockState> defaultBlockState = Blocks.TNT::getDefaultState;
    private static final TNTProperties defaultProperties = TNTProperties.builder().radius(4.0f).mode(Explosion.Mode.BREAK).fuse(80).build();

    public CustomTNTEntity(@NotNull BlockState blockState, World worldIn) {
        super(ModEntities.CUSTOM_TNT.get(), worldIn);

        Block block = blockState.getBlock();
        TNTProperties properties = null;
        if (block instanceof CustomTNTBlock<?>) {
            CustomTNTBlock<?> tntBlock = (CustomTNTBlock<?>) block;
            properties = tntBlock.getTNTProperties();
        }

        this.blockState = blockState;
        this.properties = properties != null ? properties : defaultProperties;
    }

    public CustomTNTEntity(@NotNull BlockState blockState, World worldIn, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(blockState, worldIn);
        this.setPosition(x, y, z);

        double d0 = worldIn.rand.nextDouble() * (double)((float)Math.PI * 2F);
        this.setMotion(-Math.sin(d0) * 0.02D, 0.2F, -Math.cos(d0) * 0.02D);
        this.setFuse(properties.getFuse());
        this.prevPosX = x;
        this.prevPosY = y;
        this.prevPosZ = z;
        this.tntPlacedBy = igniter;
    }

    /**
     * Get block
     * @return block of the entity.
     * @deprecated Use {@link BlockState#getBlock()} using {@link #getBlockState()} instead.
     */
    @Nullable
    @Deprecated
    public Block getBlock() {
        return blockState != null ? blockState.getBlock() : null;
    }

    @Override
    public void readAdditional(@NotNull CompoundNBT compound) {
        super.read(compound);

        compound.contains(QFMCore.nbtName);
        CompoundNBT blockStateNbt = compound.getCompound(QFMCore.nbtName);
        BlockState state = NBTUtil.readBlockState(blockStateNbt);
        if (!(state.getBlock() instanceof CustomTNTBlock<?>)) {
            Minecraft.getInstance().runAsync(() -> this.remove(false));
        } else {
            this.blockState = state;
        }
    }

    @Override
    protected void writeAdditional(@NotNull CompoundNBT compound) {
        super.writeAdditional(compound);

        CompoundNBT qfmNbt = new CompoundNBT();
        qfmNbt.put("BlockState", NBTUtil.writeBlockState(this.blockState == null ? defaultBlockState.get() : blockState));
        compound.put(QFMCore.nbtName, qfmNbt);
    }

    @Override
    protected void explode() {
        this.world.createExplosion(this, this.getPosX(), this.getPosYHeight(0.0625D), this.getPosZ(), properties.getRadius(), properties.isCausesFire(), properties.getMode());
    }

    public @NotNull BlockState getBlockState() {
        return blockState == null ? defaultBlockState.get() : blockState;
    }

    public void setBlockState(@NotNull BlockState blockState) {
        this.blockState = blockState;
    }
}
