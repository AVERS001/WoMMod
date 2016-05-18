package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import com.projectbronze.wom.core.WomCore;

public class AngelSteelBlock extends Block {

	public AngelSteelBlock(String unlocName) {
		super(Material.iron);
		setBlockName(unlocName);
		setBlockTextureName(WomCore.modid + ":" + unlocName);
		setHardness(0.2F);
		setHarvestLevel("pickaxe", 3);
		setCreativeTab(WomCore.tabWoM);
	}

}
