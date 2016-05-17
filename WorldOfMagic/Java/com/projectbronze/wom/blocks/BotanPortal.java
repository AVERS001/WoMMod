package com.projectbronze.wom.blocks;

import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.tileEntity.BotanPortalEntity;

public class BotanPortal extends GenericPortal{

	public BotanPortal(String unlocName) {
		super(unlocName);					
	}
	
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new BotanPortalEntity();
    }

}
