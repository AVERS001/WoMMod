package com.projectbronze.wom.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import vazkii.botania.api.mana.IManaItem;
import vazkii.botania.client.gui.SlotLocked;
import baubles.api.IBauble;

import com.projectbronze.wom.gui.inventory.PotionBeltInventory;
import com.projectbronze.wom.gui.slot.PotionSlot;

public class PotionBeltContainer extends Container {

	PotionBeltInventory potionBeltInv;
	 
	    public PotionBeltContainer(EntityPlayer player) {
	        

			int slot = player.inventory.currentItem;
			IInventory playerInv = player.inventory;
			potionBeltInv = new PotionBeltInventory(player, slot);
	        
			for(int i = 0; i < 5; i++)
			{
				addSlotToContainer(new PotionSlot(potionBeltInv, i, i  * 18 + 44, 32));
			}

			for(int i = 0; i < 3; ++i)
				for(int j = 0; j < 9; ++j)
					addSlotToContainer(new Slot(playerInv, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));

			for(int i = 0; i < 9; ++i) {
				if(player.inventory.currentItem == i)
					addSlotToContainer(new SlotLocked(playerInv, i, 8 + i * 18, 142));
				else addSlotToContainer(new Slot(playerInv, i, 8 + i * 18, 142));
			}
	    }
	    
	    
	    
	    @Override
		public boolean canInteractWith(EntityPlayer player) {
			boolean can = potionBeltInv.isUseableByPlayer(player);
			if(!can)
				onContainerClosed(player);

			return can;
		}

		@Override
		public void onContainerClosed(EntityPlayer player) {
			super.onContainerClosed(player);
			potionBeltInv.pushInventory();
		}

		@Override
		public void putStacksInSlots(ItemStack[] inv) {
			super.putStacksInSlots(inv);
		}

		@Override
		public ItemStack transferStackInSlot(EntityPlayer player, int index) {
			ItemStack itemstack = null;
			Slot slot = (Slot)inventorySlots.get(index);

			if(slot != null && slot.getHasStack()) {
				ItemStack itemstack1 = slot.getStack();
				itemstack = itemstack1.copy();

				if(index < 5) {
					if(!mergeItemStack(itemstack1, 5, 41, true))
						return null;
				} else {
					if(itemstack1 != null && (itemstack1.getItem() instanceof ItemPotion) && !mergeItemStack(itemstack1, 0, 28, false))
						return null;
				}

				if(itemstack1.stackSize == 0)
					slot.putStack((ItemStack)null);
				else slot.onSlotChanged();

				if(itemstack1.stackSize == itemstack.stackSize)
					return null;

				slot.onPickupFromSlot(player, itemstack1);
			}

			return itemstack;
		}
}
