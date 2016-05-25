package com.projectbronze.wom.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.tileEntity.TwilightPortalEntity;

public class TwilightPortal extends GenericPortal {

	public TwilightPortal(String unlocName) {
		super(unlocName);
	}
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TwilightPortalEntity();
    }

}
