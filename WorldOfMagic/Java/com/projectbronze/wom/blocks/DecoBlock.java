package com.projectbronze.wom.blocks;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.projectbronze.wom.core.WomCore;

public class DecoBlock extends Block{
	public IIcon[] icons;
	public int maxmeta = 0;
	public ArrayList <String> names = new ArrayList();
	public DecoBlock() {
		super(Material.iron);
		setCreativeTab(WomCore.tabWoM);
		setBlockName("DecoBlock");
	}

	/**
	 * Used to see meta of all decorative blocks in debug.
	 */
	public void printMetaWithNames()
	{
		for(int i = 0; i < maxmeta; i++)
		{
			System.out.println(i + " - " + names.get(i));
		}
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
	public String getUnlocalizedName() {
		return "tile.DecoBlock";
	}
	
	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	public void registerBlockIcons(IIconRegister reg) {
		icons = new IIcon[maxmeta];
		for(int i = 0; i < maxmeta; i++)
		{
			icons[i] = reg.registerIcon(WomCore.modid + ":" + names.get(i));
		}
	}
	
	@Override
	public IIcon getIcon(int side, int meta) {
		if(meta > maxmeta)
		{
			meta = maxmeta;
		}
		return icons[meta];
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < maxmeta; i++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
}
