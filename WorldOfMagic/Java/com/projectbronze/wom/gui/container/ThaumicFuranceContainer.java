package com.projectbronze.wom.gui.container;


import com.projectbronze.wom.gui.slot.TimeShardSlot;
import com.projectbronze.wom.registry.ItemRegistry;
import com.projectbronze.wom.tileEntity.ThaumicFurnaceEntity;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ThaumicFuranceContainer /*extends Container*/ {
	
	private ThaumicFurnaceEntity te;

	 
	 /*
	  * SLOTS:
	  * 
	  * Tile Entity 0-8 ........ 0  - 11
	  * Player Inventory 9-35 .. 12  - 39 inv
	  * Player Inventory 0-8 ... 40 - 48 hotbat
	  */
	/*	    public ThaumicFuranceContainer(IInventory playerInv, ThaumicFurnaceEntity te) {
	        this.te = te;
	        this.addSlotToContainer(new Slot(te, 0, 80, 8));
	        this.addSlotToContainer(new Slot(te, 1, 80, 48));
	        
	        
	        for (int y = 0; y < 3; ++y) {
	            for (int x = 0; x < 9; ++x) {
	                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	            }
	        }

	        for (int x = 0; x < 9; x++) {
	            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
	        }
	    }


	    @Override
	    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	        ItemStack previous = null;
	        Slot slot = (Slot) this.inventorySlots.get(fromSlot);
	        
	        if (slot != null && slot.getHasStack()) {
	            ItemStack current = slot.getStack();
	            previous = current.copy();
	            
	            
		            if (fromSlot < 2) {	      
		                if (!this.mergeItemStack(current, 2, 38, true))
		                    return null;
		            } else {
		            	boolean valid = false;
		            	if(current.getItem() == ItemRegistry.timeShard)
		            	{
		            		valid = true;
		            		for(int i = 0; i < 2; i++)
		            		{
		            			TimeShardSlot sslot = (TimeShardSlot) this.getSlot(i);
		            			if(sslot.getStack() != null && sslot.getStack().getItemDamage() == current.getItemDamage())
		            			{
		            				valid = false;
		            			}
		            		}
		            		if (valid && !this.mergeItemStack(current, 0, 12, false))
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
	    public boolean canInteractWith(EntityPlayer playerIn) {
	        return this.te.isUseableByPlayer(playerIn);
	    }
*/
}
