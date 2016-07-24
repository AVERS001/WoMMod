package com.projectbronze.wom.registry;

import java.awt.Color;
import java.util.Arrays;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import amerifrance.guideapi.api.GuideRegistry;
import amerifrance.guideapi.api.abstraction.CategoryAbstract;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.util.BookBuilder;
import amerifrance.guideapi.gui.GuiBase;
import amerifrance.guideapi.gui.GuiHome;
import com.gt22.gt22core.baseclasses.item.GenericItem;
import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.items.PotionBelt;
import com.projectbronze.wom.items.TimeShard;
import com.projectbronze.wom.items.TradeEditor;
import cpw.mods.fml.common.registry.GameRegistry;

public final class ItemRegistry
{

	public static Item timeShard;
	public static Item potionBelt;
	public static GenericItem craftItem;
	public static TradeEditor tradeEditor;
	public static void register(Item item)
	{
		GameRegistry.registerItem(item, item.getUnlocalizedName());
	}

	public static void init()
	{
		register(timeShard = new TimeShard("TimeShard"));
		register(potionBelt = new PotionBelt("PotionBelt"));
		registerCraftItems();
		register(tradeEditor = new TradeEditor("TradeEditor"));
	}

	public static void registerCraftItems()
	{
		String[] plates =
		{ "AluminumBrass", "Alumite", "Ardite", "Bronze", "Cobalt", "Copper", "Manyullyn", "Obsidian", "Steel", "Tin" };
		craftItem = new GenericItem(Core.instance);
		for (int i = 0; i < plates.length; i++)
		{
			craftItem.addGenericItem("plate" + plates[i]);
		}
		register(craftItem);
	}
}
