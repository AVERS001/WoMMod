package com.projectbronze.wom.tileEntity.cores;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/*
 * Standart class for portal cores
 */
public abstract class GenericCoreEntity extends TileEntity
{

	protected static enum PortalDirection
	{
		X, Z, NONE;
	}

	protected final ItemStack mainblock;
	protected final Block portalblock;
	protected int resettime = 20;
	protected int lastside;

	public GenericCoreEntity(ItemStack mainblock, Block portalblock)
	{
		this.mainblock = mainblock;
		this.portalblock = portalblock;
	}

	@Override
	public void updateEntity()
	{
		if (worldObj.isRemote)
		{
			return;
		}
		if (resettime-- == 0)
		{
			int str = checkStructure(worldObj, xCoord, yCoord, zCoord, mainblock);
			if (canWork(str))
			{
				processStr(str);
			}
			else
			{
				if (lastside == 1)
				{
					clearx(worldObj);
				}
				else if (lastside == 2)
				{
					clearz(worldObj);
				}
			}
			resettime = 20;
		}
	}

	/**
	 * @param worldObj
	 *            - world
	 * @param x
	 * @param y
	 * @param z
	 * @param mainblock
	 *            - main block of structure
	 * @return 1 if placed int x axis, 2 if in z, 0 in any other
	 */
	public int checkStructure(World worldObj, int x, int y, int z, ItemStack mainblock)
	{
		Block block = Block.getBlockFromItem(mainblock.getItem());
		int damage = mainblock.getItemDamage();

		if (worldObj.getBlock(x - 1, y, z) == block && worldObj.getBlockMetadata(x - 1, y, z) == damage)
			if (worldObj.getBlock(x - 2, y + 1, z) == block && worldObj.getBlockMetadata(x - 2, y + 1, z) == damage)
				if (worldObj.getBlock(x - 2, y + 2, z) == block && worldObj.getBlockMetadata(x - 2, y + 2, z) == damage)
					if (worldObj.getBlock(x - 2, y + 3, z) == block && worldObj.getBlockMetadata(x - 2, y + 3, z) == damage)
						if (worldObj.getBlock(x - 1, y + 4, z) == block && worldObj.getBlockMetadata(x - 1, y + 4, z) == damage)
							if (worldObj.getBlock(x, y + 4, z) == block && worldObj.getBlockMetadata(x, y + 4, z) == damage)
								if (worldObj.getBlock(x + 1, y + 4, z) == block && worldObj.getBlockMetadata(x + 1, y + 4, z) == damage)
									if (worldObj.getBlock(x + 2, y + 3, z) == block && worldObj.getBlockMetadata(x + 2, y + 3, z) == damage)
										if (worldObj.getBlock(x + 2, y + 2, z) == block && worldObj.getBlockMetadata(x + 2, y + 2, z) == damage)
											if (worldObj.getBlock(x + 2, y + 1, z) == block && worldObj.getBlockMetadata(x + 2, y + 1, z) == damage)
												if (worldObj.getBlock(x + 1, y, z) == block && worldObj.getBlockMetadata(x + 1, y, z) == damage)
													return 1;

		if (worldObj.getBlock(x, y, z - 1) == block && worldObj.getBlockMetadata(x, y, z - 1) == damage)
			if (worldObj.getBlock(x, y + 1, z - 2) == block && worldObj.getBlockMetadata(x, y + 1, z - 2) == damage)
				if (worldObj.getBlock(x, y + 2, z - 2) == block && worldObj.getBlockMetadata(x, y + 2, z - 2) == damage)
					if (worldObj.getBlock(x, y + 3, z - 2) == block && worldObj.getBlockMetadata(x, y + 3, z - 2) == damage)
						if (worldObj.getBlock(x, y + 4, z - 1) == block && worldObj.getBlockMetadata(x, y + 4, z - 1) == damage)
							if (worldObj.getBlock(x, y + 4, z) == block && worldObj.getBlockMetadata(x, y + 4, z) == damage)
								if (worldObj.getBlock(x, y + 4, z + 1) == block && worldObj.getBlockMetadata(x, y + 4, z + 1) == damage)
									if (worldObj.getBlock(x, y + 3, z + 2) == block && worldObj.getBlockMetadata(x, y + 3, z + 2) == damage)
										if (worldObj.getBlock(x, y + 2, z + 2) == block && worldObj.getBlockMetadata(x, y + 2, z + 2) == damage)
											if (worldObj.getBlock(x, y + 1, z + 2) == block && worldObj.getBlockMetadata(x, y + 1, z + 2) == damage)
												if (worldObj.getBlock(x, y, z + 1) == block && worldObj.getBlockMetadata(x, y, z + 1) == damage)
													return 2;
		return 0;
	}

	/**
	 * Place blocks in 3x3 over tile in x axis
	 * 
	 * @param worldObj
	 *            - world
	 * @param block
	 *            - block to place
	 */
	public void placex(World worldObj, Block block)
	{
		for (int x = xCoord - 1; x < xCoord + 2; x++)
		{
			for (int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(x, y, zCoord, block, 0, 6);
			}
		}
	}

	/**
	 * Place blocks in 3x3 over tile in z axis
	 * 
	 * @param worldObj
	 *            - world
	 * @param block
	 *            - block to place
	 */
	public void placez(World worldObj, Block block)
	{
		for (int z = zCoord - 1; z < zCoord + 2; z++)
		{
			for (int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(xCoord, y, z, block, 1, 6);

			}
		}
	}

	/**
	 * Clear blocks in 3x3 over tile in x axis
	 * 
	 * @param worldObj
	 *            - world
	 * 
	 */
	public void clearx(World worldObj)
	{
		for (int x = xCoord - 1; x < xCoord + 2; x++)
		{
			for (int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(x, y, zCoord, Blocks.air);
			}
		}
	}

	/**
	 * Clear blocks in 3x3 over tile in z axis
	 * 
	 * @param worldObj
	 *            - world
	 * 
	 */
	public void clearz(World worldObj)
	{
		for (int z = zCoord - 1; z < zCoord + 2; z++)
		{
			for (int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(xCoord, y, z, Blocks.air);
			}
		}
	}

	protected void processStr(int str)
	{
		switch (getDirection(str))
		{
			case X:
			{
				lastside = 1;
				work(str);
				placex(worldObj, portalblock);
				break;
			}
			case Z:
			{
				lastside = 2;
				work(str);
				placez(worldObj, portalblock);
				break;
			}
			case NONE:
			{
				switch (lastside)
				{
					case (1):
					{
						clearx(worldObj);
						lastside = 0;
						break;
					}
					case (2):
					{
						clearz(worldObj);
						lastside = 0;
						break;
					}
				}
			}
			default:
			{
				processCustomStr(str);
				break;
			}
		}
	}

	protected abstract boolean canWork(int structureret);

	protected abstract void work(int structureret);

	protected void onClose()
	{
		//NO-OP
	}

	protected void processCustomStr(int str)
	{

	}

	protected PortalDirection getDirection(int str)
	{
		return str == 1 ? PortalDirection.X : str == 2 ? PortalDirection.Z : PortalDirection.NONE;
	}
	
	public ItemStack getFrameBlock()
	{
		return mainblock;
	}
}
