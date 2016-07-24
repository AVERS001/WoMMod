package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.tileEntity.ThaumCoreEntity;

public class ThaumCore extends GenericCore
{

	public ThaumCore(String unlocName, Block portal)
	{
		super(unlocName, portal, ThaumCoreEntity.class);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta)
	{
		return new ThaumCoreEntity();
	}

}
