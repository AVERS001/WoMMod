package com.projectbronze.wom.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

import com.projectbronze.wom.registry.ItemRegistry;

public class TimeShardSlot extends Slot {

    public TimeShardSlot(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
    
    public boolean isItemValid(ItemStack stack)
    {
    	if(stack.getItem() == ItemRegistry.timeShard)
    	{
    		//проверяем нет ли в тайле такого шарада
    		for(int i = 0; i < 12; i++)
    		{
    			if(inventory.getStackInSlot(i) != null && inventory.getStackInSlot(i).getItemDamage() == stack.getItemDamage())
    			{
    				return false;
    			}
    		}
    		return true;
    	}
        return false;
    }
}