package com.projectbronze.wom.blocks.cores;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.gt22.gt22core.baseclasses.block.BlockWithTile;
import com.gt22.gt22core.utils.ToolClass;
import com.projectbronze.wom.core.Core;

/*
 * Standard for core blocks, also contain removing portal blocks after
 * destroying block
 */
public class GenericCore extends BlockWithTile
{

	private Block portal;

	public GenericCore(String unlocName, Block portal, Class<? extends TileEntity> te)
	{
		super(Material.iron, 5F, 5F, unlocName, Core.instance, ToolClass.pickaxe, 2, te);
		this.portal = portal;
	}

	@Override
	public void onBlockHarvested(World world, int x, int y, int z, int p_149681_5_, EntityPlayer player)
	{
		if (world.getBlock(x + 1, y + 1, z) == portal)
		{
			for (int xCoord = x - 1; xCoord < x + 2; xCoord++)
			{
				for (int yCoord = y + 1; yCoord < y + 4; yCoord++)
				{
					world.setBlock(xCoord, yCoord, z, Blocks.air);
				}
			}
		}
		if (world.getBlock(x, y + 1, z + 1) == portal)
		{
			for (int zCoord = z - 1; zCoord < z + 2; zCoord++)
			{
				for (int yCoord = y + 1; yCoord < y + 4; yCoord++)
				{
					world.setBlock(x, yCoord, zCoord, Blocks.air);
				}
			}
		}
	}

}
