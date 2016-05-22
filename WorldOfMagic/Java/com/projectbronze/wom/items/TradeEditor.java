package com.projectbronze.wom.items;

import com.projectbronze.wom.core.WomCore;

import net.minecraft.item.Item;

public class TradeEditor extends Item {
	public TradeEditor(String unlocName) {
		setCreativeTab(WomCore.tabWoM);
		setTextureName(WomCore.modid + ":" + unlocName);
		setUnlocalizedName(unlocName);
	}

}
