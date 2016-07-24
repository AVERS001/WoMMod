package com.projectbronze.wom.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.projectbronze.wom.registry.ItemRegistry;

public class TimeShardSlot extends Slot
{

	public TimeShardSlot(IInventory inventory, int index, int xPosition, int yPosition)
	{
		super(inventory, index, xPosition, yPosition);
	}

	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}

	public static boolean isValid(ItemStack stack, IInventory inventory)
	{
		if (stack.getItem() == ItemRegistry.timeShard)
		{
			// проверяем нет ли в тайле такого шарада
			for (int i = 0; i < 6; i++)
			{
				if (inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).getItemDamage() == stack.getItemDamage())
				{
					System.out.println("Conflict int slot" + i + "meta:" + inventory.getStackInSlot(i).getItemDamage());
					return false;
				}
			}
			return true;
		}
		return false;
	}
	
	public boolean isItemValid(ItemStack stack)
	{
		return isValid(stack, inventory);
	}
}