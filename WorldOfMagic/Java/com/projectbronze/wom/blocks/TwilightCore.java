package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.tileEntity.TwilightCoreEntity;

public class TwilightCore extends GenericCore{

	public TwilightCore(String unlocName, Block portal) {
		super(unlocName, portal);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
	    return new TwilightCoreEntity();
	}
	


}
