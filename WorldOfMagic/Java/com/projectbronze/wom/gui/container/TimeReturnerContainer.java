package com.projectbronze.wom.gui.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.projectbronze.wom.gui.slot.TimeShardSlot;
import com.projectbronze.wom.registry.ItemRegistry;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;

public class TimeReturnerContainer extends Container {
	
	 private TimeReturnerEntity te;

	 
	 /*
	  * SLOTS:
	  * 
	  * Tile Entity 0-8 ........ 0  - 11
	  * Player Inventory 9-35 .. 12  - 39 inv
	  * Player Inventory 0-8 ... 40 - 48 hotbat
	  */
	    public TimeReturnerContainer(IInventory playerInv, TimeReturnerEntity te) {
	        this.te = te;
	        for (int y = 0; y < 3; y++) {
	            for (int x = 0; x < 4; x++) {
	                this.addSlotToContainer(new TimeShardSlot(te, x + y * 4, 7 + x * 21, 6 + y * 21));
	            }
	        }
	        
	        for (int y = 0; y < 3; ++y) {
	            for (int x = 0; x < 9; ++x) {
	                this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
	            }
	        }

	        for (int x = 0; x < 9; x++) {
	            this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
	        }
	    }
	    
	    
	    
	    /*Standart look:
	        @Override
			public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
    		ItemStack previous = null;
    		Slot slot = (Slot) this.inventorySlots.get(fromSlot);

    		if (slot != null && slot.getHasStack()) {
        	ItemStack current = slot.getStack();
        	previous = current.copy();

        	// [...] Custom behaviour

        	if (current.stackSize == 0)
            	slot.putStack((ItemStack) null);
        	else
            	slot.onSlotChanged();

        	if (current.stackSize == previous.stackSize)
            	return null;
        	slot.onPickupFromSlot(playerIn, current);
    		}
    	}
    return previous;
	     */
	    @Override
	    public ItemStack transferStackInSlot(EntityPlayer playerIn, int fromSlot) {
	        ItemStack previous = null;
	        Slot slot = (Slot) this.inventorySlots.get(fromSlot);
	        
	        if (slot != null && slot.getHasStack()) {
	            ItemStack current = slot.getStack();
	            previous = current.copy();
	            
	            
		            if (fromSlot < 12) {	      
		                if (!this.mergeItemStack(current, 12, 48, true))
		                    return null;
		            } else {
		            	boolean valid = false;
		            	if(current.getItem() == ItemRegistry.timeShard)
		            	{
		            		valid = true;
		            		for(int i = 0; i < 12; i++)
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

}
