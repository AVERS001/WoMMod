package com.projectbronze.wom.tileEntity;

import net.minecraft.item.ItemStack;
import twilightforest.item.TFItems;

import com.projectbronze.wom.registry.ItemRegistry;

public class TwilightPortalEntity extends GenericPortalEntity {
	
	private static final ItemStack input = new ItemStack(TFItems.charmOfKeeping3, 1, 0);
	private static final ItemStack output = new ItemStack(ItemRegistry.timeShard, 1, 5);
	
	
	@Override
	public void updateEntity() {
		super.update(worldObj, xCoord, yCoord, zCoord, input, output);
	}

}
