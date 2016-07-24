package com.projectbronze.wom.registry;

import com.gt22.gt22core.integration.thaumcraft.api.AdvThaumApi;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.crafting.InfusionRecipe;
import thaumcraft.api.crafting.ShapedArcaneRecipe;

public class ThaumRecipeRegistry
{
	public static ShapedArcaneRecipe thaumCore;
	public static InfusionRecipe potionBelt;
	public static ItemStack[] potionBeltRec =
	{ new ItemStack(Items.potionitem), ItemApi.getBlock("blockEssentiaReservoir", 0), ItemApi.getBlock("blockJar", 1), ItemApi.getItem("itemEldritchObject", 3) };

	public static void init()
	{
		thaumCore = ThaumcraftApi.addArcaneCraftingRecipe("THAUMPORTAL", new ItemStack(BlockRegistry.thaumCore), AdvThaumApi.getPrimals(100), "121", "232", "121", '1', ItemApi.getBlock("blockMetalDevice", 3), '2', ItemApi.getBlock("blockTube", 0), '3', ItemApi.getBlock("blockCosmeticSolid", 4));

		potionBelt = ThaumcraftApi.addInfusionCraftingRecipe("POTIONBELT", new ItemStack(ItemRegistry.potionBelt), 7, new AspectList().add(Aspect.WATER, 64).add(Aspect.ELDRITCH, 128).add(Aspect.HEAL, 64).add(Aspect.CRAFT, 64), ItemApi.getItem("itemBaubleBlanks", 2), potionBeltRec);
	}
}
