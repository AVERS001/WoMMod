package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.Explosion;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.gt22.gt22core.baseclasses.block.BlockBase;
import com.gt22.gt22core.utils.ToolClass;
import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.registry.BlockRegistry;

public class CloudBlock extends BlockBase
{

	public CloudBlock(String unlocName)
	{
		super(Core.cloud, 5F, 2F, unlocName, Core.instance, ToolClass.none, 0);
		setStepSound(soundTypeCloth);
	}

	@Override
	public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity)
	{
		if (entity.motionY < 0.0D)
		{
			entity.motionY *= 0.005D;
		}
		entity.fallDistance = 0.0F;
	}

	@Override
	public boolean canDropFromExplosion(Explosion par1Explosion)
	{
		return false;
	}

	@Override
	public int getRenderBlockPass()
	{
		return 1;
	}

	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int x, int y, int z, int side)
	{
		Block block = iblockaccess.getBlock(x, y, z);
		if (block == BlockRegistry.cloudBlock)
		{
			return false;
		}
		else
		{
			return super.shouldSideBeRendered(iblockaccess, x, y, z, side);
		}
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	}

	@Override
	public boolean isBlockSolid(IBlockAccess iblockaccess, int x, int y, int z, int l)
	{
		Block block = iblockaccess.getBlock(x, y, z);
		if (block == BlockRegistry.cloudBlock)
		{
			return false;
		}
		else
		{
			return super.isBlockSolid(iblockaccess, x, y, z, l);
		}
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z)
	{
		if (world.getBlock(x, y - 1, z) == BlockRegistry.cloudBlock)
		{
			return null;
		}
		else
		{
			return AxisAlignedBB.getBoundingBox(x, y, z, x + 1.0D, y + 0.0625D, z + 1.0D);
		}
	}
}
