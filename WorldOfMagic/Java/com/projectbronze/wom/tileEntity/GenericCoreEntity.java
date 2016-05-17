package com.projectbronze.wom.tileEntity;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GenericCoreEntity extends TileEntity {
	
	/*
	 * returns 1 if placed int x axis, 2 if in z, 0 in any other
	 */
	public int checkStructure(World worldObj, int x, int y, int z, ItemStack mainblock)
	{
		Block block = Block.getBlockFromItem(mainblock.getItem());
		int damage = mainblock.getItemDamage();

		
		
		
		if(worldObj.getBlock(x - 1, y, z) == block && worldObj.getBlockMetadata(x - 1, y, z) == damage)
			if(worldObj.getBlock(x - 2, y + 1, z) == block && worldObj.getBlockMetadata(x - 2, y + 1, z) == damage)
				if(worldObj.getBlock(x - 2, y + 2, z) == block && worldObj.getBlockMetadata(x - 2, y + 2, z) == damage)
					if(worldObj.getBlock(x - 2, y + 3, z) == block && worldObj.getBlockMetadata(x - 2, y + 3, z) == damage)
						if(worldObj.getBlock(x - 1, y + 4, z) == block && worldObj.getBlockMetadata(x - 1, y + 4, z) == damage)
							if(worldObj.getBlock(x, y + 4, z) == block && worldObj.getBlockMetadata(x, y + 4, z) == damage)
								if(worldObj.getBlock(x + 1, y + 4, z) == block && worldObj.getBlockMetadata(x + 1, y + 4, z) == damage)
									if(worldObj.getBlock(x + 2, y + 3, z) == block && worldObj.getBlockMetadata(x + 2, y + 3, z) == damage)
										if(worldObj.getBlock(x + 2, y + 2, z) == block && worldObj.getBlockMetadata(x + 2, y + 2, z) == damage)
											if(worldObj.getBlock(x + 2, y + 1, z) == block && worldObj.getBlockMetadata(x + 2, y + 1, z) == damage)
												if(worldObj.getBlock(x + 1, y, z) == block && worldObj.getBlockMetadata(x + 1, y, z) == damage)
													return 1;
		if(worldObj.getBlock(x, y, z - 1) == block && worldObj.getBlockMetadata(x, y, z - 1) == damage)
			if(worldObj.getBlock(x, y + 1, z - 2) == block && worldObj.getBlockMetadata(x, y + 1, z - 2) == damage)
				if(worldObj.getBlock(x, y + 2, z - 2) == block && worldObj.getBlockMetadata(x, y + 2, z - 2) == damage)
					if(worldObj.getBlock(x, y + 3, z - 2) == block && worldObj.getBlockMetadata(x, y + 3, z - 2) == damage)
						if(worldObj.getBlock(x, y + 4, z - 1) == block && worldObj.getBlockMetadata(x, y + 4, z - 1) == damage)
							if(worldObj.getBlock(x, y + 4, z) == block && worldObj.getBlockMetadata(x, y + 4, z) == damage)
								if(worldObj.getBlock(x, y + 4, z + 1) == block && worldObj.getBlockMetadata(x, y + 4, z + 1) == damage)
									if(worldObj.getBlock(x, y + 3, z + 2) == block && worldObj.getBlockMetadata(x, y + 3, z + 2) == damage)
										if(worldObj.getBlock(x, y + 2, z + 2) == block && worldObj.getBlockMetadata(x, y + 2, z + 2) == damage)
											if(worldObj.getBlock(x, y + 1, z + 2) == block && worldObj.getBlockMetadata(x, y + 1, z + 2) == damage)
												if(worldObj.getBlock(x, y, z + 1) == block && worldObj.getBlockMetadata(x, y, z + 1) == damage)
													return 2;
		return 0;
	}
	
	public void placex(World worldObj, Block block)
	{
		for(int x = xCoord - 1; x < xCoord + 2; x++)
		{
			for(int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(x, y, zCoord, block, 0, 6);
			}
		}
	}
	
	public void placez(World worldObj, Block block)
	{
		for(int z = zCoord - 1; z < zCoord + 2; z++)
		{
			for(int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(xCoord, y, z, block, 1, 6);
				
			}
		}
	}
	
	public void clearx(World worldObj)
	{
		for(int x = xCoord - 1; x < xCoord + 2; x++)
		{
			for(int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(x, y, zCoord, Blocks.air);
			}
		}
	}
	
	public void clearz(World worldObj)
	{
		for(int z = zCoord - 1; z < zCoord + 2; z++)
		{
			for(int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(xCoord, y, z, Blocks.air);
			}
		}
	}

		
}
