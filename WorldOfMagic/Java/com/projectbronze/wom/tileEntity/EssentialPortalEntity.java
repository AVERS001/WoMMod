package com.projectbronze.wom.tileEntity;

import net.minecraft.item.ItemStack;

import com.projectbronze.wom.registry.BlockRegistry;
import com.projectbronze.wom.registry.ItemRegistry;

import ec3.common.item.ItemsCore;

public class EssentialPortalEntity extends GenericPortalEntity {
	
	private static final ItemStack input = new ItemStack(ItemsCore.genericItem, 1, 56);
	private static final ItemStack output = new ItemStack(ItemRegistry.timeShard, 1, 4);
	
	
	@Override
	public void updateEntity() {
		super.update(worldObj, xCoord, yCoord, zCoord, input, output);
	}

}
