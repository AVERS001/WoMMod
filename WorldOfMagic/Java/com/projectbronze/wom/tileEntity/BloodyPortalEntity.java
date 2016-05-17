package com.projectbronze.wom.tileEntity;

import net.minecraft.item.ItemStack;
import WayofTime.alchemicalWizardry.ModBlocks;

import com.projectbronze.wom.registry.ItemRegistry;





public class BloodyPortalEntity extends GenericPortalEntity {
	
	
	private ItemStack input = new ItemStack(ModBlocks.blockStabilityGlyph, 1, 0);
	private ItemStack output = new ItemStack(ItemRegistry.timeShard, 1, 2);
	@Override
	public void updateEntity() 
	{
		update(worldObj, xCoord, yCoord, zCoord, input, output);
	}
}
