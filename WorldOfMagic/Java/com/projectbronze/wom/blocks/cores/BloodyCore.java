package com.projectbronze.wom.blocks.cores;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import DummyCore.Utils.MiscUtils;
import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.tileEntity.cores.BloodyCoreEntity;

public class BloodyCore extends GenericCore
{

	public BloodyCore(String unlocName, Block portal)
	{
		super(unlocName, portal, BloodyCoreEntity.class);
	}

	@Override
	public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (!worldObj.isRemote)
		{
			player.openGui(Core.instance, GuiHandler.BloodyPortalID, worldObj, x, y, z);
		}

		return true;
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int par6)
	{
		MiscUtils.dropItemsOnBlockBreak(world, x, y, z, block, par6);
		super.breakBlock(world, x, y, z, block, par6);
	}

}
