package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;

public class BloodyCore extends GenericCore {

	
	 public BloodyCore(String unlocName, Block portal) {
		super(unlocName, portal);
	}


	@Override
	    public TileEntity createNewTileEntity(World worldIn, int meta) {
	        return new BloodyCoreEntity();
	    }

	
	@Override
    public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    	if(!worldObj.isRemote)
    	{
    		
    		player.openGui(WomCore.instance, GuiHandler.BloodyPortalID, worldObj, x, y, z);
    	}
    	
    	return true;
    }

	
	 
}
