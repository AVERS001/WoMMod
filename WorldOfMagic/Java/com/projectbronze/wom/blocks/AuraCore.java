package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.tileEntity.AuraCoreEntity;

public class AuraCore extends GenericCore{

	public AuraCore(String unlocName, Block portal) {
		super(unlocName, portal);
	}
	
	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
	    return new AuraCoreEntity();
	}
	
	

}
