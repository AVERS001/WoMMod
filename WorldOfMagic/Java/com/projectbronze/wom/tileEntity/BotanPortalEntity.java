package com.projectbronze.wom.tileEntity;

import net.minecraft.item.ItemStack;
import vazkii.botania.common.item.ModItems;

import com.projectbronze.wom.registry.ItemRegistry;

public class BotanPortalEntity extends GenericPortalEntity {
	
	
	private static final ItemStack input = new ItemStack(ModItems.laputaShard, 1, 19);
	private static final ItemStack output = new ItemStack(ItemRegistry.timeShard, 1, 1);

	
	
	@Override
	public void updateEntity() 
	{
		update(worldObj, xCoord, yCoord, zCoord, input, output);
	}
	
}
