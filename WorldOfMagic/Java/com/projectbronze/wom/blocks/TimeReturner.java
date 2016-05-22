package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import DummyCore.Utils.MiscUtils;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;

public class TimeReturner extends BlockContainer {

	public TimeReturner(String unlocName) {
		super(Material.iron);
		setBlockName(unlocName);
		setBlockTextureName(WomCore.modid + ":" + unlocName);
		setHardness(1.0F);
		setCreativeTab(WomCore.tabWoM);
	}


    public boolean isOpaqueCube()
    {
        return false;
    }

    public boolean renderAsNormalBlock()
    {
        return false;
    }
    
    @Override
    public int getRenderBlockPass() {
    	return 0;
    }

    public int getRenderType() {
    	return 2634;
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

    @Override
    public void breakBlock(World world, int x, int y,
    		int z, Block block, int par6) {
    	MiscUtils.dropItemsOnBlockBreak(world, x, y, z, block, par6);
    	super.breakBlock(world, x, y, z,
    			block, par6);
    }
}
