package com.projectbronze.wom.tileEntity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

import com.gt22.gt22core.baseclasses.tileEntity.TileWithInventory;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TradeTileEntity extends TileWithInventory
{

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
		if (!this.flag)
		{
			return 0.0F;
		}
		else
		{
			int i = (int) (this.worldObj.getTotalWorldTime() - this.time);
			this.time = this.worldObj.getTotalWorldTime();

			if (i > 1)
			{
				this.f -= (float) i / 40.0F;

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

	@Override
	public void updateEntity()
	{
		if (!worldObj.isRemote)
		{
			if (inventory[2] != null && inventory[3] != null && inventory[0] != null && !inventory[2].hasTagCompound() && inventory[0].stackSize >= inventory[2].stackSize && inventory[0].getItem() == inventory[2].getItem() && inventory[0].getItemDamage() == inventory[2].getItemDamage())
			{
				int currentitems = 0;
				if (getStackInSlot(1) != null)
				{
					currentitems = getStackInSlot(1).stackSize;
				}
				int outstacksize = inventory[3].stackSize + currentitems;
				if (outstacksize <= inventory[3].getMaxStackSize())
				{
					ItemStack out = inventory[3].copy();
					out.stackSize = outstacksize;
					setInventorySlotContents(1, out);
					decrStackSize(0, inventory[2].stackSize);
				}
			}
		}
	}

	public TradeTileEntity()
	{
		super(4);
	}

	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if (index == 0)
		{
			return true;
		}
		return false;
	}
}
