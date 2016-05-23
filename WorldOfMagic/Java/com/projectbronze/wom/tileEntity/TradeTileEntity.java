package com.projectbronze.wom.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;

import com.projectbronze.wom.registry.ItemRegistry;

public class TradeTileEntity extends TileEntity implements IInventory {

	
	@Override
    public AxisAlignedBB getRenderBoundingBox() {
        double d0 = 5.0D;
        AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double)xCoord, (double)yCoord, (double)zCoord, (double)xCoord + 1.0D, (double)yCoord + 1.0D, (double)zCoord + 1.0D).expand(d0, d0, d0);
        return axisalignedbb;    
    }
	
	public ItemStack[] inventory;
	@Override
	public int getSizeInventory() {
		return 4;
	}
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote)
		{
			if(inventory[2] != null && inventory[3] != null && inventory[0] != null && inventory[0].getItem().equals(inventory[2].getItem()) && inventory[0].getItemDamage() == inventory[2].getItemDamage() && inventory[0].stackSize >= inventory[2].stackSize)
			{
				int currentitems = 0;
				if(getStackInSlot(1) != null)
				{
					currentitems = getStackInSlot(1).stackSize;
				}
				int outstacksize = inventory[3].stackSize + currentitems;
				if(outstacksize < 65)
				{
					setInventorySlotContents(1, new ItemStack(inventory[3].getItem(), outstacksize, inventory[3].getItemDamage()));
					decrStackSize(0, inventory[2].stackSize);
				}
			}
		}
	}
	
	public TradeTileEntity() {
        this.inventory = new ItemStack[this.getSizeInventory()];
    }

	@Override
	public ItemStack getStackInSlot(int index) {
		if (index < 0 || index >= this.getSizeInventory())
	        return null;
	    return this.inventory[index];
	}

	@Override
	public ItemStack decrStackSize(int index, int count) {
	    if (this.getStackInSlot(index) != null) {
	        ItemStack itemstack;

	        if (this.getStackInSlot(index).stackSize <= count) {
	            itemstack = this.getStackInSlot(index);
	            this.setInventorySlotContents(index, null);
	            this.markDirty();
	            return itemstack;
	        } else {
	            itemstack = this.getStackInSlot(index).splitStack(count);

	            if (this.getStackInSlot(index).stackSize <= 0) {
	                this.setInventorySlotContents(index, null);
	            } else {
	                //Just to show that changes happened
	                this.setInventorySlotContents(index, this.getStackInSlot(index));
	            }

	            this.markDirty();
	            return itemstack;
	        }
	    } else {
	        return null;
	    }
	}


	@Override
	public ItemStack getStackInSlotOnClosing(int index) {
		ItemStack stack = this.getStackInSlot(index);
	    this.setInventorySlotContents(index, null);
	    return stack;
	}

	@Override
	public void setInventorySlotContents(int index, ItemStack stack) {
	    if (index < 0 || index >= this.getSizeInventory())
	        return;

	    if (stack != null && stack.stackSize > this.getInventoryStackLimit())
	        stack.stackSize = this.getInventoryStackLimit();
	        
	    if (stack != null && stack.stackSize == 0)
	        stack = null;

	    this.inventory[index] = stack;
	    this.markDirty();
	}

	@Override
	public String getInventoryName() {
		return "TradeStation";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord, yCoord, zCoord) <= 64;
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {
		
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack) {
		if(index == 0)
		{
			return true;
		}
		return false;
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
	    super.writeToNBT(nbt);

	    NBTTagList list = new NBTTagList();
	    for (int i = 0; i < this.getSizeInventory(); ++i) {
	        if (this.getStackInSlot(i) != null) {
	            NBTTagCompound stackTag = new NBTTagCompound();
	            stackTag.setByte("Slot", (byte) i);
	            this.getStackInSlot(i).writeToNBT(stackTag);
	            list.appendTag(stackTag);
	        }
	    }
	    nbt.setTag("Items", list);

	}


	@Override
	public void readFromNBT(NBTTagCompound nbt) {
	    super.readFromNBT(nbt);

	    NBTTagList list = nbt.getTagList("Items", 10);
	    for (int i = 0; i < list.tagCount(); ++i) {
	        NBTTagCompound stackTag = list.getCompoundTagAt(i);
	        int slot = stackTag.getByte("Slot") & 255;
	        this.setInventorySlotContents(slot, ItemStack.loadItemStackFromNBT(stackTag));
	    }
	}

}
