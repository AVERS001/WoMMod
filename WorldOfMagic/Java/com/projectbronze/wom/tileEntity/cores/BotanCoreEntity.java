package com.projectbronze.wom.tileEntity.cores;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TilePool;
import com.projectbronze.wom.registry.BlockRegistry;

public class BotanCoreEntity extends GenericCoreEntity
{
	public BotanCoreEntity()
	{
		super(new ItemStack(ModBlocks.storage, 1, 1), BlockRegistry.botanPortal);
	}

	// Additional check for mana pools
	@Override
	public int checkStructure(World worldObj, int xCoord, int yCoord, int zCoord, ItemStack mainblock)
	{
		Block pool = ModBlocks.pool;
		switch (super.checkStructure(worldObj, xCoord, yCoord, zCoord, mainblock))
		{
			case (1):
			{
				if (worldObj.getBlock(xCoord + 2, yCoord, zCoord + 2) == pool)
					if (worldObj.getBlock(xCoord - 2, yCoord, zCoord + 2) == pool)
						return 1;
				if (worldObj.getBlock(xCoord + 2, yCoord, zCoord - 2) == pool)
					if (worldObj.getBlock(xCoord - 2, yCoord, zCoord - 2) == pool)
						return 3;
				break;
			}
			case (2):
			{
				if (worldObj.getBlock(xCoord + 2, yCoord, zCoord + 2) == pool)
					if (worldObj.getBlock(xCoord + 2, yCoord, zCoord - 2) == pool)
						return 2;
				if (worldObj.getBlock(xCoord - 2, yCoord, zCoord + 2) == pool)
					if (worldObj.getBlock(xCoord - 2, yCoord, zCoord - 2) == pool)
						return 4;
				break;
			}
		}
		return 0;
	}

	private boolean consumeMana(TilePool p1, TilePool p2)
	{
		if (p1 == null || p2 == null)
		{
			return false;
		}
		if (p1.getCurrentMana() >= 2000 && p2.getCurrentMana() >= 2000)
		{
			p1.recieveMana(-2000);
			p2.recieveMana(-2000);
			return true;
		}
		return false;
	}

	@Override
	protected boolean canWork(int str)
	{
		TilePool[] pools = getPools(str);
		return pools == null ? false : pools[0].getCurrentMana() >= 2000 && pools[1].getCurrentMana() >= 2000;
	}

	@Override
	protected void work(int str)
	{
		TilePool[] pools = getPools(str);
		consumeMana(pools[0], pools[1]);
	}

	private TilePool[] getPools(int str)
	{
		TileEntity p1, p2;
		switch (str)
		{
			case (1):
			{
				p1 = worldObj.getTileEntity(xCoord + 2, yCoord, zCoord + 2);
				p2 = worldObj.getTileEntity(xCoord - 2, yCoord, zCoord + 2);
				break;
			}
			case (2):
			{
				p1 = worldObj.getTileEntity(xCoord + 2, yCoord, zCoord + 2);
				p2 = worldObj.getTileEntity(xCoord + 2, yCoord, zCoord - 2);
				break;
			}
			case (3):
			{
				p1 = worldObj.getTileEntity(xCoord + 2, yCoord, zCoord - 2);
				p2 = worldObj.getTileEntity(xCoord - 2, yCoord, zCoord - 2);
				break;
			}
			case (4):
			{
				p1 = worldObj.getTileEntity(xCoord - 2, yCoord, zCoord + 2);
				p2 = worldObj.getTileEntity(xCoord - 2, yCoord, zCoord - 2);
				break;
			}
			default:
			{
				return null;
			}
		}
		if (!(p1 instanceof TilePool) || !(p2 instanceof TilePool))
		{
			return null;
		}
		return new TilePool[]
		{ (TilePool) p1, (TilePool) p2 };
	}

	@Override
	protected PortalDirection getDirection(int str)
	{
		return str == 1 || str == 3 ? PortalDirection.X : str == 2 || str == 4 ? PortalDirection.Z : PortalDirection.NONE;
	}
}
