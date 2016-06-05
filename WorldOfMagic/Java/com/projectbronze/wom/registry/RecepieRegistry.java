package com.projectbronze.wom.registry;

import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.ShapedArcaneRecipe;
import twilightforest.block.TFBlocks;
import twilightforest.item.TFItems;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.item.ModItems;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.registry.GameRegistry;
import ec3.common.block.BlocksCore;
import ec3.common.item.ItemsCore;


public class RecepieRegistry {
	
	public static final void init()
	{
				GameRegistry.addRecipe(new ItemStack(BlockRegistry.botanCore),
				"121",
				"232", 
				"121",
				'1', new ItemStack(ModBlocks.shimmerrock, 1, 0),
				'2', new ItemStack(ModItems.manaResource, 1, 14),
				'3', new ItemStack(ModBlocks.storage, 1, 1));
				GameRegistry.addRecipe(new ItemStack(BlockRegistry.bloodyCore),
				"121",
				"232", 
				"121",
				'1', new ItemStack(WayofTime.alchemicalWizardry.ModItems.demonBloodShard),
				'2', new ItemStack(WayofTime.alchemicalWizardry.ModBlocks.blockCrystal),
				'3', new ItemStack(WayofTime.alchemicalWizardry.ModBlocks.blockStabilityGlyph));
				GameRegistry.addRecipe(new ItemStack(BlockRegistry.twilightCore),
				"121",
				"232", 
				"121",
				'1', new ItemStack(TFItems.transformPowder),
				'2', new ItemStack(TFBlocks.knightmetalStorage),
				'3', new ItemStack(TFBlocks.underBrick));
				GameRegistry.addRecipe(new ItemStack(BlockRegistry.essentialCore),
				"121",
				"345", 
				"161",
				'1', new ItemStack(ItemsCore.genericItem, 1, 61),
				'2', new ItemStack(ItemsCore.genericItem, 1, 70),
				'3', new ItemStack(ItemsCore.genericItem, 1, 72),
				'4', new ItemStack(BlocksCore.demonicPlating, 1, 0),
				'5', new ItemStack(ItemsCore.genericItem, 1, 71),
				'6', new ItemStack(ItemsCore.genericItem, 1, 73));
				
	}
	
}
