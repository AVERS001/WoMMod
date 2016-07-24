package com.projectbronze.wom.gui.slot;

import com.projectbronze.wom.core.Core;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import WayofTime.alchemicalWizardry.api.items.interfaces.IBloodOrb;

public class BloodOrbSlot extends Slot
{

	public BloodOrbSlot(IInventory inventory, int index, int xPosition, int yPosition)
	{
		super(inventory, index, xPosition, yPosition);
		setBackgroundIconTexture(new ResourceLocation(Core.modid, "textures/gui/orb.png"));
	}

	@Override
	public int getSlotStackLimit()
	{
		return 1;
	}

	public static boolean isBloodOrb(ItemStack stack)
	{
		return stack.getItem() instanceof IBloodOrb;
	}

	public boolean isItemValid(ItemStack stack)
	{
		return isBloodOrb(stack);
	}
}