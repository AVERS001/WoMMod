package com.projectbronze.wom.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.projectbronze.wom.tileEntity.ThaumicFurnaceEntity;

public class ThaumicFuranceContainer extends Container
{

	private ThaumicFurnaceEntity te;

	/*
	 * SLOTS:
	 * 
	 * Tile Entity 0-1 ........ 0 - 1
	 * Player Inventory 9-35 .. 2 - 28 inv
	 * Player Inventory 0-8 ... 29 -37 hotbat
	 */
	public ThaumicFuranceContainer(IInventory playerInv, ThaumicFurnaceEntity te)
	{
		this.te = te;
		this.addSlotToContainer(new Slot(te, 0, 80, 8));
		this.addSlotToContainer(new Slot(te, 1, 80, 48));

		for (int y = 0; y < 3; ++y)
		{
			for (int x = 0; x < 9; ++x)
			{
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; x++)
		{
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
		}
	}

	@Override
	public ItemStack transferStackInSlot(EntityPlayer player, int index)
	{
		ItemStack itemstack = null;
		Slot slot = (Slot) inventorySlots.get(index);

		if (slot != null && slot.getHasStack())
		{
			ItemStack itemstack1 = slot.getStack();
			itemstack = itemstack1.copy();

			if (index < 2)
			{
				if (!mergeItemStack(itemstack1, 2, 37, true))
					return null;
			}
			else
			{
				if (te.getStackInSlot(0) == null && te.isItemValidForSlot(0, itemstack1))
				{
					if(!mergeItemStack(itemstack1, 0, 1, false))
					{
						if(te.isItemValidForSlot(1, itemstack1))
						{
							if(!mergeItemStack(itemstack1, 1, 2, false))
							{
								return null;
							}
						}
					}
						
				}
				else
				{
					if(te.isItemValidForSlot(1, itemstack1))
					{
						if(!mergeItemStack(itemstack1, 1, 2, false))
						{
							return null;
						}
					}
				}
			}

			if (itemstack1.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (itemstack1.stackSize == itemstack.stackSize)
				return null;

			slot.onPickupFromSlot(player, itemstack1);
		}

		return itemstack;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.te.isUseableByPlayer(playerIn);
	}

}
