package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;

import com.projectbronze.wom.tileEntity.BotanCoreEntity;

public class BotanCore extends GenericCore
{

	public BotanCore(String unlocName, Block portal)
	{
		super(unlocName, portal, BotanCoreEntity.class);
	}
}
