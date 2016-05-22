package com.projectbronze.wom.registry;

import net.minecraft.item.Item;

import com.projectbronze.wom.items.CraftItem;
import com.projectbronze.wom.items.PotionBelt;
import com.projectbronze.wom.items.TimeShard;
import com.projectbronze.wom.items.TradeEditor;

import cpw.mods.fml.common.registry.GameRegistry;

public final class ItemRegistry {
	
	public static Item timeShard;
	public static Item potionBelt;
	public static CraftItem craftItem;
	public static TradeEditor tradeEditor;
	
	public static final void init()
	{
		GameRegistry.registerItem(timeShard = new TimeShard("TimeShard"), "TimeShard");
		GameRegistry.registerItem(potionBelt = new PotionBelt("PotionBelt"), "PotionBelt");
		craftItem = new CraftItem("Coin1");
		craftItem.addCraftItem("Coin2");
		craftItem.addCraftItem("Coin3");
		GameRegistry.registerItem(craftItem, "CraftItem");
		GameRegistry.registerItem(tradeEditor = new TradeEditor("TradeEditor"), "TradeEditor");
	}

}
