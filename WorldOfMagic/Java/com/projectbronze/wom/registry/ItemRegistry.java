package com.projectbronze.wom.registry;

import net.minecraft.item.Item;

import com.projectbronze.wom.items.GenericItem;
import com.projectbronze.wom.items.PotionBelt;
import com.projectbronze.wom.items.TimeShard;

import cpw.mods.fml.common.registry.GameRegistry;

public final class ItemRegistry {
	
	public static Item timeShard;
	public static Item genItem;
	public static Item potionBelt;
	
	public static final void init()
	{
		GameRegistry.registerItem(timeShard = new TimeShard("TimeShard"), "TimeShard");
		GameRegistry.registerItem(genItem = new GenericItem("CraftItem"), "CraftItem");
		GameRegistry.registerItem(potionBelt = new PotionBelt("PotionBelt"), "PotionBelt");
	}

}
