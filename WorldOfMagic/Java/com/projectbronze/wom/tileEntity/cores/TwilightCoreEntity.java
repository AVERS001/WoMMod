package com.projectbronze.wom.tileEntity.cores;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import twilightforest.TwilightForestMod;
import twilightforest.block.TFBlocks;
import com.projectbronze.wom.registry.BlockRegistry;

public class TwilightCoreEntity extends GenericCoreEntity
{

	public TwilightCoreEntity()
	{
		super(new ItemStack(TFBlocks.underBrick, 1, 0), BlockRegistry.twilightPortal);
	}

	@Override
	protected boolean canWork(int str)
	{
		return worldObj.provider.dimensionId == TwilightForestMod.dimensionID;
	}

	@Override
	protected void work(int str)
	{
	}
}
