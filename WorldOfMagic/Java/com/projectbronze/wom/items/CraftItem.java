package com.projectbronze.wom.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.projectbronze.wom.core.WomCore;

public class CraftItem extends Item {
	
	public IIcon[] icons = new IIcon[12];
	public static String modid = WomCore.modid;
	private String textureName;
	private int maxmeta = 0;
	ArrayList<String> names = new ArrayList();
	public CraftItem()
	{
		super();
		this.setHasSubtypes(true);
		this.setCreativeTab(WomCore.tabWoM);
	}
	
	public void addCraftItem(String unlocName)
	{
		maxmeta++;
		names.add(unlocName);
	}
	
	public void addCraftItems(String[] names)
	{
		maxmeta += names.length;
		for(int i = 0; i < names.length; i++)
		{
			this.names.add(names[i]);
		}
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < maxmeta; i++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
		@Override
		public void registerIcons(IIconRegister reg) {
			for(int i = 0; i < maxmeta; i++)
			{
				icons[i] = reg.registerIcon(modid + ":" + names.get(i));
			}
		}
		
		@Override
		public IIcon getIconFromDamage(int meta) {
		    if (meta > maxmeta)
		        meta = 0;

		    return this.icons[meta];
		}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		int meta = stack.getItemDamage();
		if(meta > maxmeta)
		{
			meta = maxmeta;
		}
	    return "item." + names.get(meta);
	}

}
