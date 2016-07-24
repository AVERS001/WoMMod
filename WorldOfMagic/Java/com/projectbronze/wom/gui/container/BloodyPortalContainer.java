package com.projectbronze.wom.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.gt22.gt22core.baseclasses.container.ContainerWithPlayerInv;
import com.projectbronze.wom.gui.slot.BloodOrbSlot;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;

public class BloodyPortalContainer extends ContainerWithPlayerInv
{

	private BloodyCoreEntity te;

	public BloodyPortalContainer(IInventory playerInv, BloodyCoreEntity te)
	{
		super(playerInv, new Slot[] {new BloodOrbSlot(te, 0, 80, 24)});
		this.te = te;
	}

	/*
	 * Standart look:
	 * 
	 * @Override
	 * public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot)
	 * {
	 * ItemStack previous = null;
	 * Slot slot = (Slot) this.inventorySlots.get(fromSlot);
	 * 
	 * if (slot != null && slot.getHasStack()) {
	 * ItemStack current = slot.getStack();
	 * previous = current.copy();
	 * 
	 * // [...] Custom behaviour
	 * 
	 * if (current.stackSize == 0)
	 * slot.putStack((ItemStack) null);
	 * else
	 * slot.onSlotChanged();
	 * 
	 * if (current.stackSize == previous.stackSize)
	 * return null;
	 * slot.onPickupFromSlot(playerIn, current);
	 * }
	 * }
	 * return previous;
	 */
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot)
	{
		ItemStack previous = null;
		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

		if (slot != null && slot.getHasStack())
		{
			ItemStack current = slot.getStack();
			previous = current.copy();

			if (fromSlot < 1)
			{
				if (!this.mergeItemStack(current, 1, 37, true))
					return null;
			}
			else
			{
				if (BloodOrbSlot.isBloodOrb(current))
				{
					if (!this.mergeItemStack(current, 0, 1, false))
						return null;
				}
			}
			if (current.stackSize == 0)
				slot.putStack((ItemStack) null);
			else
				slot.onSlotChanged();

			if (current.stackSize == previous.stackSize)
				return null;
			slot.onPickupFromSlot(playerIn, current);
		}
		return previous;
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn)
	{
		return this.te.isUseableByPlayer(playerIn);
	}

}
