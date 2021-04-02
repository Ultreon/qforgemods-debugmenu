package com.qtech.forgemods.core.modules.tiles.blocks.machines.generator.diesel;

import com.qtech.modlib.api.IFluidContainer;
import com.qtech.forgemods.core.modules.tiles.ModMachineTileEntities;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.generator.AbstractFluidFuelGeneratorTileEntity;
import com.qtech.forgemods.core.util.InventoryUtils;
import com.qtech.forgemods.core.util.TextUtils;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

import javax.annotation.Nullable;

public class DieselGeneratorTileEntity extends AbstractFluidFuelGeneratorTileEntity {
    // Energy constants
    public static final int MAX_ENERGY = 50_000;
    public static final int MAX_SEND = 500;
    public static final int ENERGY_CREATED_PER_TICK = 120;
    public static final int TICKS_PER_MILLIBUCKET = 10;

    static final ITag.INamedTag<Fluid> FUEL_TAG = FluidTags.makeWrapperTag(new ResourceLocation("forge", "diesel").toString());

    public DieselGeneratorTileEntity() {
        super(ModMachineTileEntities.dieselGenerator, 2, MAX_ENERGY, 0, MAX_SEND, new FluidTank(4000, s -> s.getFluid().isIn(FUEL_TAG)));
    }

    @Override
    protected boolean hasFuel() {
        return tank.getFluidAmount() > 0;
    }

    @Override
    protected int getFuelBurnTime(FluidStack fluid) {
        return TICKS_PER_MILLIBUCKET * fluid.getAmount();
    }

    @Override
    protected int getEnergyCreatedPerTick() {
        return ENERGY_CREATED_PER_TICK;
    }

    @Override
    public int[] getSlotsForFace(Direction side) {
        return new int[]{0, 1};
    }

    @Override
    public boolean canInsertItem(int index, ItemStack stack, @Nullable Direction direction) {
        return index == 0
                && InventoryUtils.isFilledFluidContainer(stack)
                && tank.isFluidValid(IFluidContainer.getBucketOrContainerFluid(stack));
    }

    @Override
    public boolean canExtractItem(int index, ItemStack stack, Direction direction) {
        return index == 1;
    }

    @Override
    protected ITextComponent getDefaultName() {
        return TextUtils.translate("container", "diesel_generator");
    }

    @Override
    protected Container createMenu(int id, PlayerInventory player) {
        return new DieselGeneratorContainer(id, player, this, this.fields);
    }
}
