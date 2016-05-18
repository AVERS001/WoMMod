package com.projectbronze.wom.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;

public class TimeReturner extends BlockContainer {

	public TimeReturner(String unlocName) {
		super(Material.iron);
		setBlockName(unlocName);
		//setBlockTextureName(WomCore.modid + ":" + unlocName);
		setHardness(1.0F);
		setCreativeTab(WomCore.tabWoM);
	}

	@Override
    public boolean shouldSideBeRendered(IBlockAccess world, int x, int y, int z, int side)
    {
        return false;
    }
 
    @Override
    public boolean isOpaqueCube()
    {
        return false;
    }
 
    @Override
    public boolean renderAsNormalBlock()
    {
        return false;
    }
 
    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TimeReturnerEntity();
    }
    @Override
    public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    	if(!worldObj.isRemote)
    	{
    		player.openGui(WomCore.instance, GuiHandler.TimeRetrunerID, worldObj, x, y, z);
    	}
    	
    	return true;
    }


}
