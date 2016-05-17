package com.projectbronze.wom.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.projectbronze.wom.core.WomCore;

public class TimeShard extends Item {
	
	public IIcon[] icons = new IIcon[12];
	public static String modid = WomCore.modid;
	private String textureName;
	public TimeShard(String unlocName)
	{
		super();
		this.setUnlocalizedName(unlocName);
		this.setHasSubtypes(true);
		this.setCreativeTab(WomCore.tabWoM);
		textureName = unlocName;
		setNoRepair();
		maxStackSize = 1;
	}
	
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List list) {
	    for (int i = 0; i < 12; i ++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
		@Override
		public void registerIcons(IIconRegister reg) {
			for(int i = 0; i < 12; i++)
			{
				icons[i] = reg.registerIcon(modid + ":" + textureName + "_" + i);
			}
		}
		
		@Override
		public IIcon getIconFromDamage(int meta) {
		    if (meta > 11)
		        meta = 0;

		    return this.icons[meta];
		}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		String name = null;
		switch (stack.getItemDamage())
		{
			case(0): //Thaumcraft
			{
				name = "Thaumic";
				break;
			}
			case(1): //botania
			{
				name = "Floral";
				break;
			}
			case(2): //BloodM
			{
				name = "Bloody";
				break;
			}
			case(3): //AuraC
			{
				name = "Aura";
				break;
			}
			
		}
	    return "item." + name + "TimeShard";
	}

}
