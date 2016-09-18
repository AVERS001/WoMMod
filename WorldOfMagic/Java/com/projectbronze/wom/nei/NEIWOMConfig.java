package com.projectbronze.wom.nei;

import codechicken.nei.api.API;
import codechicken.nei.api.IConfigureNEI;

public class NEIWOMConfig implements IConfigureNEI
{

	@Override
	public void loadConfig()
	{
		PortalRecipeHandler r = new PortalRecipeHandler();
		API.registerRecipeHandler(r);
		API.registerUsageHandler(r);
	}

	@Override
	public String getName()
	{
		return "World Of Magic NEI Plugin";
	}

	@Override
	public String getVersion()
	{
		return "1.0";
	}

}
