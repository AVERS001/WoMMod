package com.projectbronze.wom.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.projectbronze.wom.core.WomCore;
/*
 * standart for portal blocks.
 */
public class DecoBlock extends Block{
	public ArrayList<IIcon> icons = new ArrayList<IIcon>();
	public int maxmeta = 0;
	public ArrayList <String> names = new ArrayList();
	public DecoBlock() {
		super(Material.iron);
		setCreativeTab(WomCore.tabWoM);
	}

	public void addDecoBlock(String unlocName)
	{
		maxmeta++;
		names.add(unlocName);
	}
	
	public void addDecoBlocks(String[] unlocNames)
	{
		for(int i = 0; i < unlocNames.length; i++)
		{
			addDecoBlock(unlocNames[i]);
		}
	}
	
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for(int i = 0; i < maxmeta; i++)
		{
			icons.add(reg.registerIcon(WomCore.modid + ":" + names.get(i)));
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(meta > maxmeta)
		{
			meta = maxmeta;
		}
		return icons.get(meta);
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < maxmeta; i++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
}
