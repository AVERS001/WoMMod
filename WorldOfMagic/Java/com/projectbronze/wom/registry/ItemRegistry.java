package com.projectbronze.wom.registry;

import net.minecraft.item.Item;

import com.projectbronze.wom.items.TimeShard;

import cpw.mods.fml.common.registry.GameRegistry;

public final class ItemRegistry {
	
	public static Item timeShard;
	
	public static final void init()
	{
		GameRegistry.registerItem(timeShard = new TimeShard("TimeShard"), "TimeShard");
	}

}
