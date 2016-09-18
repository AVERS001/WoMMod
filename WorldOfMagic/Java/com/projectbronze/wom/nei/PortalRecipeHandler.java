package com.projectbronze.wom.nei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.projectbronze.wom.core.Core;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.ResourceLocation;
import codechicken.lib.gui.GuiDraw;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class PortalRecipeHandler extends TemplateRecipeHandler
{

	private class PortalRecipeWrapper extends CachedRecipe
	{
		private PortalRecipe rec;

		public PortalRecipeWrapper(PortalRecipe rec)
		{
			this.rec = rec;
		}

		@Override
		public PositionedStack getIngredient()
		{
			return new PositionedStack(rec.in, 24, 45);
		}

		@Override
		public List<PositionedStack> getOtherStacks()
		{
			ArrayList<PositionedStack> stacks = new ArrayList<PositionedStack>();
			stacks.add(new PositionedStack(new ItemStack(rec.core), 50, 70));
			stacks.add(new PositionedStack(rec.frame, 100, 70));
			stacks.add(new PositionedStack(new ItemStack(rec.portal), 75, 45));
			if(rec.powerstack != null)
			{
				stacks.add(new PositionedStack(rec.powerstack, 75, 85));
			}
			return stacks;
		}

		@Override
		public PositionedStack getResult()
		{
			return new PositionedStack(rec.out, 126, 45);
		}
	}

	@Override
	public String getRecipeName()
	{
		return "Portal Recipe";
	}

	@Override
	public String getGuiTexture()
	{
		return Core.modid + ":textures/gui/PortalRecipe.png";
	}

	@Override
	public void loadCraftingRecipes(ItemStack result)
	{
		PortalRecipe[] recipes = PortalRecipe.getByOutput(result);
		for (PortalRecipe rec : recipes)
		{
			arecipes.add(new PortalRecipeWrapper(rec));
		}
	}

	@Override
	public void loadUsageRecipes(ItemStack ingredient)
	{
		PortalRecipe[] recipes = PortalRecipe.getByInput(ingredient);
		for (PortalRecipe rec : recipes)
		{
			PortalRecipeWrapper res = new PortalRecipeWrapper(rec);
			res.setIngredientPermutation(res.getIngredients(), ingredient);
			arecipes.add(res);
		}
	}

	
}
