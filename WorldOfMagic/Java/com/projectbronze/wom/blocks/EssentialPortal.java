package com.projectbronze.wom.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.tileEntity.BloodyPortalEntity;
import com.projectbronze.wom.tileEntity.EssentialPortalEntity;

public class EssentialPortal extends GenericPortal{

	public EssentialPortal(String unlocName) {
		super(unlocName);					
	}
	
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new EssentialPortalEntity();
    }

}
