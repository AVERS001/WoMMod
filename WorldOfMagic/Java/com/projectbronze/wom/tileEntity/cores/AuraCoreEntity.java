package com.projectbronze.wom.tileEntity.cores;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.projectbronze.wom.registry.BlockRegistry;

public class AuraCoreEntity extends GenericCoreEntity
{
	public AuraCoreEntity()
	{
		super(new ItemStack(BlockRegistry.angelSteelBlock, 1, 0), BlockRegistry.auraPortal);
	}
	
	@Override
	protected boolean canWork(int structureret)
	{
		return true;
	}

	@Override
	protected void work(int structureret)
	{
	}
}
