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
	
	public static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}
	
	public static final void init()
	{
		register(timeShard = new TimeShard("TimeShard"));
		register(potionBelt = new PotionBelt("PotionBelt"));
		registerCraftItems();
		register(tradeEditor = new TradeEditor("TradeEditor"));
	}

	
	public static void registerCraftItems()
	{
		String[] plates =
		{
			"AluminumBrass",
			"Alumite",
			"Ardite",
			"Bronze",
			"Cobalt",
			"Copper",
			"Manyullyn",
			"Obsidian",
			"Steel",
			"Tin",
		};
		for(int i = 0; i < plates.length; i++)
		{
			plates[i] = "plate" + plates[i];
		}
		craftItem = new CraftItem();
		craftItem.addCraftItems(plates);
		register(craftItem);
	}
}
