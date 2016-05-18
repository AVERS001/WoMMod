package com.projectbronze.wom.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.tileEntity.AuraPortalEntity;

public class AuraPortal extends GenericPortal {

	public AuraPortal(String unlocName) {
		super(unlocName);
	}
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new AuraPortalEntity();
    }

}
