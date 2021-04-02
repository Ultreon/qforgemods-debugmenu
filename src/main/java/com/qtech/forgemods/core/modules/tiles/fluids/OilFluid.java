package com.qtech.forgemods.core.modules.tiles.fluids;

import java.util.Random;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.qtech.forgemods.core.modules.tiles.ModFluids;
import com.qtech.forgemods.core.modules.tiles.ModBlocks;
import com.qtech.forgemods.core.modules.items.ModItems;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.item.Item;
import net.minecraft.particles.IParticleData;
import net.minecraft.state.StateContainer;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidAttributes;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public abstract class OilFluid extends FlowingFluid {
   @Override
   public Fluid getFlowingFluid() {
      return ModFluids.FLOWING_OIL;
   }

   @Override
   public Fluid getStillFluid() {
      return ModFluids.OIL;
   }

   @Override
   public Item getFilledBucket() {
      return ModItems.OIL_BUCKET.get();
   }

   @Override
   public void randomTick(World world, BlockPos pos, FluidState state, Random random) {

   }

   @Override
   protected FluidAttributes createAttributes() {
      return FluidAttributes.builder(
              new ResourceLocation("qforgemod:blocks/oil_still"),
              new ResourceLocation("qforgemod:blocks/oil_flowing"))
              .translationKey("block.qforgemod.oil")
              .luminosity(0).density(5_000).viscosity(10_000).temperature(0)
              .sound(SoundEvents.ITEM_BUCKET_FILL, SoundEvents.ITEM_BUCKET_EMPTY)
              .build(this);
   }

   private boolean isSurroundingBlockFlammable(IWorldReader worldIn, BlockPos pos) {
      return false;
   }

   private boolean getCanBlockBurn(IWorldReader worldIn, BlockPos pos) {
      return false;
   }

   @Override
   protected int getLevelDecreasePerBlock(IWorldReader worldIn) {
      return 2;
   }

   /**
    * Todo: add particle for oil.
    */
   @Nullable
   @OnlyIn(Dist.CLIENT)
   @Override
   public IParticleData getDripParticleData() {
//      return ParticleTypes.DRIPPING_LAVA;
      return null;
   }

   @Override
   protected void beforeReplacingBlock(IWorld worldIn, BlockPos pos, BlockState state) {
      this.triggerEffects(worldIn, pos);
   }

   @Override
   public int getSlopeFindDistance(IWorldReader worldIn) {
      return worldIn.getDimensionType().isUltrawarm() ? 4 : 2;
   }

   @Override
   public BlockState getBlockState(FluidState state) {
      return ModBlocks.OIL.get().getDefaultState().with(FlowingFluidBlock.LEVEL, getLevelFromState(state));
   }

   @Override
   public boolean isEquivalentTo(Fluid fluidIn) {
      return fluidIn == ModFluids.OIL || fluidIn == ModFluids.FLOWING_OIL;
   }

   @Override
   public boolean canDisplace(FluidState fluidState, IBlockReader blockReader, BlockPos pos, Fluid fluid, Direction direction) {
      return false;
   }

   @Override
   public int getTickRate(IWorldReader p_205569_1_) {
      return 30;
   }

   @Override
   public int func_215667_a(World world, BlockPos pos, FluidState p_215667_3_, FluidState p_215667_4_) {
      int i = this.getTickRate(world);
      if (!p_215667_3_.isEmpty() && !p_215667_4_.isEmpty() && !p_215667_3_.get(FALLING) && !p_215667_4_.get(FALLING) && p_215667_4_.getActualHeight(world, pos) > p_215667_3_.getActualHeight(world, pos) && world.getRandom().nextInt(4) != 0) {
         i *= 4;
      }

      return i;
   }

   private void triggerEffects(IWorld world, BlockPos pos) {
      world.playEvent(1501, pos, 0);
   }

   @Override
   protected boolean canSourcesMultiply() {
      return false;
   }

   @Override
   protected void flowInto(IWorld worldIn, BlockPos pos, BlockState blockStateIn, Direction direction, FluidState fluidStateIn) {
      super.flowInto(worldIn, pos, blockStateIn, direction, fluidStateIn);
   }

   protected boolean ticksRandomly() {
      return true;
   }

   protected float getExplosionResistance() {
      return 100.0F;
   }

   public static class Flowing extends OilFluid {
      protected void fillStateContainer(StateContainer.Builder<Fluid, FluidState> builder) {
         super.fillStateContainer(builder);
         builder.add(LEVEL_1_8);
      }

      public int getLevel(FluidState state) {
         return state.get(LEVEL_1_8);
      }

      public boolean isSource(FluidState state) {
         return false;
      }
   }

   public static class Source extends OilFluid {
      public int getLevel(FluidState state) {
         return 8;
      }

      public boolean isSource(FluidState state) {
         return true;
      }
   }
}
