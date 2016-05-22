package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import DummyCore.Utils.MiscUtils;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;
import com.projectbronze.wom.tileEntity.EssentialCoreEntity;

public class EssentialCore extends GenericCore {

	
	 public EssentialCore(String unlocName, Block portal) {
		super(unlocName, portal);
	}


	@Override
	    public TileEntity createNewTileEntity(World worldIn, int meta) {
	        return new EssentialCoreEntity();
	    }

	
	@Override
    public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    	if(!worldObj.isRemote)
    	{
    		
    		player.openGui(WomCore.instance, GuiHandler.EssentialCoreID, worldObj, x, y, z);
    	}
    	
    	return true;
    }

	@Override
    public void breakBlock(World world, int x, int y,
    		int z, Block block, int par6) {
    	MiscUtils.dropItemsOnBlockBreak(world, x, y, z, block, par6);
    	super.breakBlock(world, x, y, z,
    			block, par6);
    }
	 
}
