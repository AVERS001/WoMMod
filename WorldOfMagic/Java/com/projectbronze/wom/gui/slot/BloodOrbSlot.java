package com.projectbronze.wom.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import WayofTime.alchemicalWizardry.ModItems;

public class BloodOrbSlot extends Slot {

    public BloodOrbSlot(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
    
    public static boolean isBloodOrb(ItemStack stack)
    {
    	Item[] bloodorb = new Item[6];
    	bloodorb[0] = ModItems.weakBloodOrb;
    	bloodorb[1] = ModItems.apprenticeBloodOrb;
    	bloodorb[2] = ModItems.magicianBloodOrb;
    	bloodorb[3] = ModItems.masterBloodOrb;
    	bloodorb[4] = ModItems.archmageBloodOrb;
    	bloodorb[5] = ModItems.transcendentBloodOrb;
    	for(int i = 0; i < 6; i++)
    	{
    		if(stack.getItem() == bloodorb[i])
    		{
    			return true;
    		}
    	}
		return false;
    }
    
    public boolean isItemValid(ItemStack stack)
    {
    	return isBloodOrb(stack);
    }
}