package com.projectbronze.wom.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.projectbronze.wom.core.Core;

public class TradeEditor extends Item
{
	public TradeEditor(String unlocName)
	{
		setCreativeTab(Core.tabWoM);
		setTextureName(Core.modid + ":" + unlocName);
		setUnlocalizedName(unlocName);
	}

	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
	{
		return stack;
	}

}
