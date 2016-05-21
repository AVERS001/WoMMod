package com.projectbronze.wom.gui.slot;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;

public class PotionSlot extends Slot {

    public PotionSlot(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
    
    public static boolean isPotion(ItemStack stack)
    {
    	return stack.getItem() instanceof ItemPotion;
    }
    
    @Override
    public boolean isItemValid(ItemStack stack)
    {
    	return isPotion(stack);
    }
}