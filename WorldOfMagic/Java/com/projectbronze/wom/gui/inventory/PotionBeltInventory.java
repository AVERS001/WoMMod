
package com.projectbronze.wom.gui.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import com.projectbronze.wom.items.PotionBelt;
import com.projectbronze.wom.registry.ItemRegistry;

public class PotionBeltInventory implements IInventory {

	private static final ItemStack[] inventory = new ItemStack[5];

	EntityPlayer player;
	int slot;
	ItemStack[] stacks = null;

	boolean invPushed = false;
	ItemStack storedInv = null;

	public PotionBeltInventory(EntityPlayer player, int slot) {
		this.player = player;
		this.slot = slot;
	}

	public static boolean isPotionBelt(ItemStack stack) {
		return stack != null && stack.getItem() == ItemRegistry.potionBelt;
	}

	public ItemStack getStack() {
		ItemStack stack = player.inventory.getStackInSlot(slot);
		if(stack != null)
			storedInv = stack;
		return stack;
	}

	public ItemStack[] getInventory() {
		if(stacks != null)
			return stacks;

		ItemStack stack = getStack();
		if(isPotionBelt(getStack())) {
			stacks = PotionBelt.loadStacks(stack);
			return stacks;
		}

		return inventory;
	}

	public void pushInventory() {
		if(invPushed)
			return;

		ItemStack stack = getStack();
		if(stack == null)
			stack = storedInv;

		if(stack != null) {
			ItemStack[] inv = getInventory();
			PotionBelt.setStacks(stack, inv);
		}

		invPushed = true;
	}

	@Override
	public int getSizeInventory() {
		return 5;
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		if(i < 0 || i > getSizeInventory() - 1)
			return null;
		return getInventory()[i];
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		ItemStack[] inventorySlots = getInventory();
		if (inventorySlots[i] != null) {
			ItemStack stackAt;

			if (inventorySlots[i].stackSize <= j) {
				stackAt = inventorySlots[i];
				inventorySlots[i] = null;
				return stackAt;
			} else {
				stackAt = inventorySlots[i].splitStack(j);

				if (inventorySlots[i].stackSize == 0)
					inventorySlots[i] = null;

				return stackAt;
			}
		}

		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return getStackInSlot(i);
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack itemstack) {
		if(slot < 0 || slot > getSizeInventory() - 1)
			return;
		ItemStack[] inventorySlots = getInventory();
		inventorySlots[slot] = itemstack;
	}

	@Override
	public int getInventoryStackLimit() {
		return isPotionBelt(getStack()) ? 64 : 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer entityplayer) {
		return isPotionBelt(getStack());
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemstack) {
		return isPotionBelt(getStack());
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public void openInventory() {
		// NO-OP
	}

	@Override
	public void closeInventory() {
		// NO-OP
	}

	@Override
	public String getInventoryName() {
		return "PotionBelt";
	}

	@Override
	public void markDirty() {
		// NO-OP
	}

}
