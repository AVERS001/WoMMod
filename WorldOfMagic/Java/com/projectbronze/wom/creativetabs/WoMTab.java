package com.projectbronze.wom.creativetabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

import com.projectbronze.wom.registry.ItemRegistry;

public class WoMTab extends CreativeTabs{
	
	public WoMTab(String label) {
		super(label);
	}
	
	
	@Override
	public Item getTabIconItem() {
		return ItemRegistry.timeShard;
	}

}
