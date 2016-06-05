package com.projectbronze.wom.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.AxisAlignedBB;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TradeTileEntity extends TileEntity implements IInventory {

	
	 private boolean flag = true;
	    
	    @SideOnly(Side.CLIENT)
		private long time;
	    @SideOnly(Side.CLIENT)
	    private float f;
	    
	    @SideOnly(Side.CLIENT)
	    public double getMaxRenderDistanceSquared()
	    {
	        return 65536.0D;
	    }
	    
	    @Override
		@SideOnly(Side.CLIENT)
		public AxisAlignedBB getRenderBoundingBox()
		{
			return INFINITE_EXTENT_AABB;
		}

		@SideOnly(Side.CLIENT)
	    public float func_146002_i()
	    {
	        if (!this.flag )
	        {
	            return 0.0F;
	        }
	        else
	        {
	            int i = (int)(this.worldObj.getTotalWorldTime() - this.time);
	            this.time = this.worldObj.getTotalWorldTime();

	            if (i > 1)
	            {
	                this.f -= (float)i / 40.0F;

	                if (this.f < 0.0F)
	                {
	                    this.f = 0.0F;
	                }
	            }

	            this.f += 0.025F;

	            if (this.f > 1.0F)
	            {
	                this.f = 1.0F;
	            }

	            return this.f;
	        }
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
			if(inventory[2] != null && inventory[3] != null && inventory[0] != null && !inventory[2].hasTagCompound())
			{
				int currentitems = 0;
				if(getStackInSlot(1) != null)
				{
					currentitems = getStackInSlot(1).stackSize;
				}
				int outstacksize = inventory[3].stackSize + currentitems;
				if(outstacksize < 65)
				{
					ItemStack out = inventory[3].copy();
					out.stackSize = outstacksize;
					setInventorySlotContents(1, out);
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
