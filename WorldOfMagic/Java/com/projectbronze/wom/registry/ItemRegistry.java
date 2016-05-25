package com.projectbronze.wom.registry;

import java.util.ArrayList;

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
	private static final String[] coins = {
		"CoinCopper", 
		"CoinSilver",
		"CoinGold"
	};
	
	public static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
	
	public static final void init()
	{
		register(timeShard = new TimeShard("TimeShard"));
		register(potionBelt = new PotionBelt("PotionBelt"));
		craftItem = new CraftItem();
		craftItem.addCraftItems(coins);
		register(craftItem);
		register(tradeEditor = new TradeEditor("TradeEditor"));
	}

}
