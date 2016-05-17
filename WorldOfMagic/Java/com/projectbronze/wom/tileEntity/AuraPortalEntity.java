package com.projectbronze.wom.tileEntity;

import net.minecraft.item.ItemStack;

import com.projectbronze.wom.registry.BlockRegistry;
import com.projectbronze.wom.registry.ItemRegistry;

public class AuraPortalEntity extends GenericPortalEntity {
	
	private static final ItemStack input = new ItemStack(BlockRegistry.AngelSteelBlock, 1, 0);
	private static final ItemStack output = new ItemStack(ItemRegistry.timeShard, 1, 3);
	
	
	@Override
	public void updateEntity() {
		super.update(worldObj, xCoord, yCoord, zCoord, input, output);
	}

}
