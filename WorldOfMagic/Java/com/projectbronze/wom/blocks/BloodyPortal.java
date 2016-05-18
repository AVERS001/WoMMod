package com.projectbronze.wom.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.tileEntity.BloodyPortalEntity;

public class BloodyPortal extends GenericPortal{

	public BloodyPortal(String unlocName) {
		super(unlocName);					
	}
	
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new BloodyPortalEntity();
    }

}
