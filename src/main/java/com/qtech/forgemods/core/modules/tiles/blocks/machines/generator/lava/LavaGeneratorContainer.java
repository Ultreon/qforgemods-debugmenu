package com.qtech.forgemods.core.modules.tiles.blocks.machines.generator.lava;

import com.qtech.forgemods.core.modules.ui.ModMachineContainers;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.generator.AbstractFluidFuelGeneratorTileEntity;
import com.qtech.forgemods.core.modules.tiles.blocks.machines.generator.AbstractFluidGeneratorContainer;
import com.qtech.modlib.silentlib.util.InventoryUtils;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;

public class LavaGeneratorContainer extends AbstractFluidGeneratorContainer<LavaGeneratorTileEntity> {
    public LavaGeneratorContainer(int id, PlayerInventory playerInventory) {
        this(id, playerInventory, new LavaGeneratorTileEntity(), new IntArray(AbstractFluidFuelGeneratorTileEntity.FIELDS_COUNT));
    }

    public LavaGeneratorContainer(int id, PlayerInventory playerInventory, LavaGeneratorTileEntity tileEntity, IIntArray fieldsIn) {
        super(ModMachineContainers.lavaGenerator, id, tileEntity, fieldsIn);

        this.addSlot(new Slot(tileEntity, 0, 80, 16));
        this.addSlot(new Slot(tileEntity, 1, 80, 59));

        InventoryUtils.createPlayerSlots(playerInventory, 8, 84).forEach(this::addSlot);

        this.addUpgradeSlots();
    }

    @Override
    protected boolean isFuel(ItemStack stack) {
        return stack.getItem() instanceof BucketItem && ((BucketItem) stack.getItem()).getFluid().isIn(FluidTags.LAVA);
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            final int inventorySize = 1;
            final int playerInventoryEnd = inventorySize + 27;
            final int playerHotbarEnd = playerInventoryEnd + 9;

            if (index != 0) {
                if (this.isFuel(itemstack1)) {
                    if (!this.mergeItemStack(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < playerInventoryEnd) {
                    if (!this.mergeItemStack(itemstack1, playerInventoryEnd, playerHotbarEnd, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index < playerHotbarEnd && !this.mergeItemStack(itemstack1, inventorySize, playerInventoryEnd, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, inventorySize, playerHotbarEnd, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}
