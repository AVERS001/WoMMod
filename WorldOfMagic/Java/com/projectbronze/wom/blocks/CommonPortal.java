package com.projectbronze.wom.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;

import com.gt22.gt22core.baseclasses.block.BlockWithTile;
import com.gt22.gt22core.utils.ToolClass;
import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.tileEntity.CommonPortalEntity;

/*
 * standart for portal blocks.
 */
public class CommonPortal extends BlockWithTile
{

	public ItemStack input, output;

	public CommonPortal(String unlocName, ItemStack input, ItemStack output)
	{
		super(Material.portal, 0F, 0F, unlocName, Core.instance, -1, ToolClass.none, 0, "portals/", CommonPortalEntity.class, new Class[] {ItemStack.class, ItemStack.class}, new Object[] {input, output});
		setBlockUnbreakable();
		setLightOpacity(8);
		setLightLevel(1.0F);
		setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 1.0F, 1.0F);
		this.input = input;
		this.output = output;
	}

	@Override
	public boolean isBlockSolid(IBlockAccess world, int x, int y, int z, int p_149747_5_)
	{
		return false;
	}

	@Override
	public int damageDropped(int meta)
	{
		return meta;
	}

	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list)
	{
		for (int i = 0; i < 2; i++)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public void setBlockBoundsBasedOnState(IBlockAccess world, int x, int y, int z)
	{
		if (world.getBlockMetadata(x, y, z) == 0)
		{
			setBlockBounds(0.0F, 0.0F, 0.4F, 1.0F, 1.0F, 0.6F);
		}
		else
		{
			setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 1.0F, 1.0F);
		}
	};

	@Override
	public boolean isOpaqueCube()
	{
		return false;
	};

	@Override
	public boolean renderAsNormalBlock()
	{
		return false;
	}

}
