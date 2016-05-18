package com.projectbronze.wom.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.tileEntity.ThaumPortalEntity;

public class ThaumPortal extends GenericPortal {

	public ThaumPortal(String unlocName) {
		super(unlocName);					
	}
	
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ThaumPortalEntity();
    }
	
	

}
