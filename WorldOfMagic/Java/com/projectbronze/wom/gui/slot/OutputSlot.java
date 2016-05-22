package com.projectbronze.wom.gui.slot;

import WayofTime.alchemicalWizardry.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class OutputSlot extends Slot {

    public OutputSlot(IInventory inventory, int index, int xPosition, int yPosition) {
        super(inventory, index, xPosition, yPosition);
    }

    @Override
    public int getSlotStackLimit() {
        return 1;
    }
    
    
    public boolean isItemValid(ItemStack stack)
    {
    	return false;
    }
}