package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import DummyCore.Utils.MiscUtils;

import com.gt22.gt22core.baseclasses.block.BlockBase;
import com.gt22.gt22core.utils.ToolClass;
import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;

public class TimeReturner extends BlockBase implements ITileEntityProvider
{

	public TimeReturner(String unlocName)
	{
		super(Material.iron, 10F, 10F, unlocName, Core.instance, ToolClass.pickaxe, 2);
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
	{
		par1World.setBlockMetadataWithNotify(par2, par3, par4, MathHelper.floor_double((double) (par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3, 2);

	}

	public boolean isOpaqueCube()
	{
		return false;
	}

	public boolean renderAsNormalBlock()
	{
		return false;
	}

	public int getRenderType()
	{
		return -1;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int par2)
	{
		return new TimeReturnerEntity();
	}

	@Override
	public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (!worldObj.isRemote)
		{
			player.openGui(Core.instance, GuiHandler.TimeRetrunerID, worldObj, x, y, z);
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
