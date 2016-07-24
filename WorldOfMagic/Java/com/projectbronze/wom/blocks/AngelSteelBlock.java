package com.projectbronze.wom.blocks;

import net.minecraft.block.material.Material;

import com.gt22.gt22core.baseclasses.block.BlockBase;
import com.gt22.gt22core.utils.ToolClass;
import com.projectbronze.wom.core.Core;

public class AngelSteelBlock extends BlockBase
{

	public AngelSteelBlock(String unlocName)
	{
		super(Material.iron, 10F, 10F, unlocName, Core.instance, ToolClass.pickaxe, 2);
	}

}
