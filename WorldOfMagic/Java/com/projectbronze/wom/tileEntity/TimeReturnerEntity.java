package com.projectbronze.wom.tileEntity;

import java.awt.geom.Ellipse2D;

import net.minecraft.item.ItemStack;
import net.minecraft.util.AxisAlignedBB;

import com.gt22.gt22core.baseclasses.tileEntity.TileWithInventory;
import com.projectbronze.wom.registry.ItemRegistry;

public class TimeReturnerEntity extends TileWithInventory
{

	@Override
	public AxisAlignedBB getRenderBoundingBox()
	{
		double d0 = 5.0D;
		AxisAlignedBB axisalignedbb = AxisAlignedBB.getBoundingBox((double) xCoord, (double) yCoord, (double) zCoord, (double) xCoord + 1.0D, (double) yCoord + 1.0D, (double) zCoord + 1.0D).expand(d0, d0, d0);
		return axisalignedbb;
	}

	
	@Override
	public int getInventoryStackLimit()
	{
		return 1;
	}
	
	@Override
	public boolean isItemValidForSlot(int index, ItemStack stack)
	{
		if (stack.getItem() == ItemRegistry.timeShard)
		{
			for (int i = 0; i < inventory.length; i++)
			{
				if (inventory[i] != null && inventory[i].getItemDamage() == stack.getItemDamage())
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	
	private boolean isAllShards()
	{
		if (inventory != null)
		{
			for (int i = 0; i < 6; i++)
			{
				if (inventory[i] == null)
				{
					return false;
				}
			}
			return true;
		}
		return false;
	}

	public boolean active = false;

	@Override
	public void updateEntity()
	{
		if (isAllShards())
		{
			active = true;
		}
		else
		{
			active = false;
		}
	}

	public TimeReturnerEntity()
	{
		super(6, true);
	}

	

}
