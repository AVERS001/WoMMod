package com.projectbronze.wom.tileEntity;

import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;

import com.projectbronze.wom.registry.ItemRegistry;

public class ThaumPortalEntity extends GenericPortalEntity {
	
	private static final ItemStack input = ItemApi.getItem("itemEldritchObject", 3);
	private static final ItemStack output = new ItemStack(ItemRegistry.timeShard, 1, 0);
	@Override
	public void updateEntity() 
	{
		update(worldObj, xCoord, yCoord, zCoord, input, output);
	}
	
	
}
