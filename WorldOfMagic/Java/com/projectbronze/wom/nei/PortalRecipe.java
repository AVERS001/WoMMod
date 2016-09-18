package com.projectbronze.wom.nei;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import com.projectbronze.wom.blocks.CommonPortal;
import com.projectbronze.wom.blocks.cores.GenericCore;
import com.projectbronze.wom.registry.BlockRegistry;

public class PortalRecipe
{
	private static final List<PortalRecipe> recipes = new ArrayList<PortalRecipe>();
	public final ItemStack in, out;
	public final GenericCore core;
	public final CommonPortal portal;
	public final ItemStack frame, powerstack;
	public static final PortalRecipe INVALID_RECIPE = new PortalRecipe(new ItemStack(Blocks.fire), new ItemStack(Blocks.fire), BlockRegistry.thaumCore, BlockRegistry.thaumPortal, new ItemStack(Blocks.fire), null);
	public PortalRecipe(ItemStack in, ItemStack out, GenericCore core, CommonPortal portal, ItemStack frame, ItemStack powerstack)
	{
		this.in = in;
		this.out = out;
		this.core = core;
		this.portal = portal;
		this.frame = frame;
		this.powerstack = powerstack;
	}
	
	public static void createRecipe(GenericCore core, ItemStack frame, ItemStack powerstack)
	{
		recipes.add(new PortalRecipe(core.portal.input, core.portal.output, core, core.portal, frame, powerstack));
	}
	
	public static PortalRecipe[] getByInput(ItemStack input)
	{
		return recipes.parallelStream().filter(rec -> ItemStack.areItemStacksEqual(input, rec.in)).toArray(PortalRecipe[]::new);
	}
	
	public static PortalRecipe[] getByOutput(ItemStack output)
	{
		return recipes.parallelStream().filter(rec -> ItemStack.areItemStacksEqual(output, rec.out)).toArray(PortalRecipe[]::new);
	}
}