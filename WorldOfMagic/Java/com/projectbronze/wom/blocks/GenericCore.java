package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.core.WomCore;

public class GenericCore extends BlockContainer {
	
	private Block portal;
	
	public GenericCore(String unlocName, Block portal) {
		super(Material.iron);
		setBlockName(unlocName);
		setBlockTextureName(WomCore.modid + ":" + unlocName);
		setHardness(1.0F);
		setCreativeTab(WomCore.tabWoM);
		this.portal = portal;
	}

	 @Override
	    public TileEntity createNewTileEntity(World worldIn, int meta) {
	        return null;
	    }

	
	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int p_149681_5_, EntityPlayer player) {
		if(world.getBlock(x + 1, y + 1, z) == portal)
		{
			for(int xCoord = x - 1; xCoord < x + 2; xCoord++)
			{
				for(int yCoord = y + 1; yCoord < y + 4; yCoord++)
				{
					world.setBlock(xCoord, yCoord, z, Blocks.air);
				}
			}
		}
		else if (world.getBlock(x, y + 1, z + 1) == portal)
		{
			for(int zCoord = z - 1; zCoord < z + 2; zCoord++)
			{
				for(int yCoord = y + 1; yCoord < y + 4; yCoord++)
				{
					world.setBlock(x, yCoord, zCoord, Blocks.air);
				}
			}
		}
	}

}
