package com.projectbronze.wom.proxy;

import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.registry.BlockRegistry;
import com.projectbronze.wom.registry.BookRegistry;
import com.projectbronze.wom.registry.ItemRegistry;
import com.projectbronze.wom.registry.RecepieRegistry;
import com.projectbronze.wom.registry.ThaumRecipeRegistry;
import com.projectbronze.wom.registry.TileEntityRegistery;
import com.projectbronze.wom.world.WomWorldGenerator;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy
{

	public void preInit(FMLPreInitializationEvent e)
	{
		
	}

	public void init(FMLInitializationEvent e)
	{
		
		ItemRegistry.init();
		BlockRegistry.init();
		TileEntityRegistery.init();
		NetworkRegistry.INSTANCE.registerGuiHandler(Core.instance, new GuiHandler());
		RecepieRegistry.init();
		GameRegistry.registerWorldGenerator(new WomWorldGenerator(), 1000);
		BookRegistry.init();
	}

	public void postInit(FMLPostInitializationEvent e)
	{
		ThaumRecipeRegistry.init();
	}
}
