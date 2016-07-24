package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;

import com.projectbronze.wom.tileEntity.TwilightCoreEntity;

public class TwilightCore extends GenericCore
{

	public TwilightCore(String unlocName, Block portal)
	{
		super(unlocName, portal, TwilightCoreEntity.class);
	}

}
