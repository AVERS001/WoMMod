package com.projectbronze.wom.proxy;

import com.projectbronze.wom.render.TimeReturnerRenderer;
import com.projectbronze.wom.render.TradeRenderer;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;
import com.projectbronze.wom.tileEntity.TradeTileEntity;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInit(FMLPreInitializationEvent e)
	{
		super.preInit(e);
	}

	@Override
	public void init(FMLInitializationEvent e)
	{
		super.init(e);
		ClientRegistry.bindTileEntitySpecialRenderer(TimeReturnerEntity.class, new TimeReturnerRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(TradeTileEntity.class, new TradeRenderer());
	}

	@Override
	public void postInit(FMLPostInitializationEvent e)
	{
		super.postInit(e);

	}

}
