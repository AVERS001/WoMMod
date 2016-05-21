package com.projectbronze.wom.registry;

import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import ec3.common.block.BlocksCore;
import ec3.common.item.ItemsCore;


public class RecepieRegistry {

	public static final void init()
	{
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.genItem, 1, 0), "010", "232", "010", '0', new ItemStack(ItemsCore.genericItem, 1, 54), '1', new ItemStack(ItemsCore.genericItem, 1, 37), '2', new ItemStack(ItemsCore.genericItem, 1, 36), '3', new ItemStack(ItemsCore.genericItem, 1, 69));
		GameRegistry.addShapedRecipe(new ItemStack(ItemRegistry.genItem, 1, 1), "010", "121", "010", '0', new ItemStack(ItemsCore.genericItem, 1, 75), '1', new ItemStack(ItemRegistry.genItem, 1, 0), '2', new ItemStack(ItemsCore.genericItem, 1, 56));
		GameRegistry.addShapedRecipe(new ItemStack(BlockRegistry.essentialCore, 1, 0), "010", "234", "050", '0', new ItemStack(BlocksCore.demonicPlating, 1, 75), '1', new ItemStack(ItemsCore.genericItem, 1, 58), '2', new ItemStack(ItemsCore.genericItem, 1, 60), '3', new ItemStack(ItemRegistry.genItem, 1, 1), '4', new ItemStack(ItemsCore.genericItem, 1, 61),'5', new ItemStack(ItemsCore.genericItem, 1, 62));
		
	}
}
