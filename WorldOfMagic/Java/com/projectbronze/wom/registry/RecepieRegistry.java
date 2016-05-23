package com.projectbronze.wom.registry;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import ec3.common.block.BlocksCore;
import ec3.common.item.ItemsCore;


public class RecepieRegistry {

	public static final void init()
	{
				GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.craftItem, 1, 1), 
				"###",
				"###",
				"###",
				'#', new ItemStack(ItemRegistry.craftItem, 1, 0));
	}
}
