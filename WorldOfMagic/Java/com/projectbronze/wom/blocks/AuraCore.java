package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;

import com.projectbronze.wom.tileEntity.AuraCoreEntity;

public class AuraCore extends GenericCore
{

	public AuraCore(String unlocName, Block portal)
	{
		super(unlocName, portal, AuraCoreEntity.class);
	}

}
