package com.projectbronze.wom.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import ec3.common.item.ItemBoundGem;

public class BoundGemSlot extends Slot
{

	public BoundGemSlot(IInventory inventory, int index, int xPosition, int yPosition)
	{
		super(inventory, index, xPosition, yPosition);
	}

	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}

	public static boolean isBoundGem(ItemStack stack)
	{
		return stack.getItem() instanceof ItemBoundGem;
	}

	@Override
	public boolean isItemValid(ItemStack stack)
	{
		return isBoundGem(stack);
	}
}