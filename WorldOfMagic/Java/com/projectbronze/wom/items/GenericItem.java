package com.projectbronze.wom.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.projectbronze.wom.core.WomCore;

public class GenericItem extends Item {
	
	public IIcon[] icons = new IIcon[2];
	public static String modid = WomCore.modid;
	private String textureName;
	public GenericItem(String unlocName)
	{
		super();
		this.setUnlocalizedName(unlocName);
		this.setHasSubtypes(true);
		this.setCreativeTab(WomCore.tabWoM);
		setNoRepair();
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 2; i++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
		@Override
		public void registerIcons(IIconRegister reg) {
			icons[0] = reg.registerIcon(modid + ":addCalcMatrix");
			icons[1] = reg.registerIcon(modid + ":portalCore");
		}
		
		@Override
		public IIcon getIconFromDamage(int meta) {
		    if (meta > 1)
		        meta = 0;

		    return this.icons[meta];
		}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = null;
		switch (stack.getItemDamage())
		{
			case(0): //Additional Calculation Matrix
			{
				name = "AddCalcMatrix";
				break;
			}
			case(1): //Portal core
			{
				name = "PortalCore";
				break;
			}
			default:
			{
				name = "meta-" + stack.getItemDamage() + "-";
			}
			
		}
	    return "item." + name + "GenItem";
	}

}
