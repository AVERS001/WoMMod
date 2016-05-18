package com.projectbronze.wom.tileEntity;

import net.minecraft.item.ItemStack;

import com.projectbronze.wom.registry.BlockRegistry;
import com.projectbronze.wom.registry.ItemRegistry;





public class BloodyPortalEntity extends GenericPortalEntity {
	
	
	//private ItemStack input = new ItemStack(ModBlocks.blockStabilityGlyph, 1, 0);
	private ItemStack input = new ItemStack(BlockRegistry.bloodyCore, 1, 0);
	private ItemStack output = new ItemStack(ItemRegistry.timeShard, 1, 2);
	@Override
	public void updateEntity() 
	{
		update(worldObj, xCoord, yCoord, zCoord, input, output);
	}
}
