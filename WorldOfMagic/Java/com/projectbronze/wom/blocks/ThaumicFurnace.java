package com.projectbronze.wom.blocks;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import thaumcraft.api.ItemApi;

import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.tileEntity.ThaumicFurnaceEntity;

public class ThaumicFurnace extends BlockContainer
{

	public ThaumicFurnace(String unlocName)
	{
		super(Material.portal);
		setBlockTextureName(Core.modid + ":" + unlocName);
		setBlockName(unlocName);
		setCreativeTab(Core.tabWoM);
		setHardness(0.5F);
		setHarvestLevel("pickaxe", 1);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_)
	{
		return new ThaumicFurnaceEntity();
	}

	@Override
	public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_)
	{
		if (!worldObj.isRemote)
		{
			if (player.getHeldItem() != null && player.getHeldItem().getItem() == ItemApi.getBlock("blockTube", 1).getItem())
			{
				System.out.println("tube");
				return false;
			}
			player.openGui(Core.instance, GuiHandler.ThaumicFurnaceID, worldObj, x, y, z);
		}

		return true;
	}

}
