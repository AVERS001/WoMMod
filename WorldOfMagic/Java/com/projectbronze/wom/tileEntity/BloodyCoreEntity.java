package com.projectbronze.wom.tileEntity;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import WayofTime.alchemicalWizardry.ModBlocks;
import WayofTime.alchemicalWizardry.api.soulNetwork.SoulNetworkHandler;

import com.projectbronze.wom.gui.slot.BloodOrbSlot;
import com.projectbronze.wom.registry.BlockRegistry;
public class BloodyCoreEntity extends GenericCoreEntity implements IInventory{
	
	public ItemStack[] inventory;
	private int lastside = 0;
	private int resettime = 20;
	private ItemStack mainblock = new ItemStack(ModBlocks.blockStabilityGlyph, 1, 0);
	private Block portalblock = BlockRegistry.BloodyPortal;
	private Boolean isOpen = false;
	@Override
	public int getSizeInventory() {
		return 1;
	}
	
	public BloodyCoreEntity() {
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
		return "BloodyPortal";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 1;
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
		return BloodOrbSlot.isBloodOrb(stack);
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
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote)
		{
			int structure = checkStructure(worldObj, xCoord, yCoord, zCoord, mainblock);
			resettime--;
			if(resettime == 0)
			if(structure != 0 && inventory != null && inventory[0] != null && !isOpen && SoulNetworkHandler.syphonFromNetwork(inventory[0], 100000) != 0 || structure != 0 && inventory != null && inventory[0] != null && isOpen && SoulNetworkHandler.syphonFromNetwork(inventory[0], 1000) != 0)
			{
				isOpen = true;
					switch (structure)
					{
						case(1): 
						{
								lastside = 1;
								clearx(worldObj);	
								placex(worldObj, portalblock);
							break;
						}
						case(2):
						{
								lastside = 2;
								clearz(worldObj);
								placez(worldObj, portalblock);
							break;
						}
				}
			}
			else
			{
				isOpen = false;
				switch (lastside)
				{
				case(1): 
				{
					clearx(worldObj);
					lastside = 0;
					break;
				}
				case(2):
				{
					clearz(worldObj);
					lastside = 0;
					break;
				}
			}
			}
			if(resettime == 0)
			{
				resettime = 20;
			}
			
	}
}
}
	
	