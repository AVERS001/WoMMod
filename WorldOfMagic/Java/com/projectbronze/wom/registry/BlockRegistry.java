package com.projectbronze.wom.registry;

import net.minecraft.block.Block;

import com.projectbronze.wom.blocks.AngelSteelBlock;
import com.projectbronze.wom.blocks.AuraCore;
import com.projectbronze.wom.blocks.AuraPortal;
import com.projectbronze.wom.blocks.BloodyCore;
import com.projectbronze.wom.blocks.BloodyPortal;
import com.projectbronze.wom.blocks.BotanCore;
import com.projectbronze.wom.blocks.BotanPortal;
import com.projectbronze.wom.blocks.ItemBlockMetaBlock;
import com.projectbronze.wom.blocks.ThaumCore;
import com.projectbronze.wom.blocks.ThaumPortal;
import com.projectbronze.wom.blocks.ThaumicFurnace;
import com.projectbronze.wom.blocks.TimeReturner;

import cpw.mods.fml.common.registry.GameRegistry;

public final class BlockRegistry {
	
	public static Block thaumCore;
	public static Block thaumPortal;
	public static Block botanCore;
	public static Block botanPortal;
	public static Block bloodyCore;
	public static Block BloodyPortal;
	public static Block auraCore;
	public static Block auraPortal;
	public static Block timeReturner;
	public static Block AngelSteelBlock;
	public static Block thaumicFurnace;
	public static final void init()
	{
	
		GameRegistry.registerBlock(thaumPortal = new ThaumPortal("portalThaumPortal"), ItemBlockMetaBlock.class, "portalThaumPortal");
		GameRegistry.registerBlock(thaumCore = new ThaumCore("portalThaum", thaumPortal), "portalThaum");
		GameRegistry.registerBlock(botanPortal = new BotanPortal("portalBotanPortal"), ItemBlockMetaBlock.class, "portalBotanPortal");
		GameRegistry.registerBlock(botanCore = new BotanCore("portalBotan", botanPortal), "portalBotan");
		GameRegistry.registerBlock(BloodyPortal = new BloodyPortal("portalBloodyPortal"), ItemBlockMetaBlock.class, "portalBloodyPortal");
		GameRegistry.registerBlock(bloodyCore = new BloodyCore("portalBloody", BloodyPortal), "portalBloody");
		GameRegistry.registerBlock(timeReturner = new TimeReturner("timeReturner"), "timeReturner");
		GameRegistry.registerBlock(AngelSteelBlock = new AngelSteelBlock("AngelSteelBlock"), "AngelSteelBlock");
		GameRegistry.registerBlock(auraPortal = new AuraPortal("auraPortal"), "auraPortal");
		GameRegistry.registerBlock(auraCore = new AuraCore("auraCore", auraPortal), ItemBlockMetaBlock.class, "auraCore");
		//GameRegistry.registerBlock(thaumicFurnace = new ThaumicFurnace("ThaumicFurnace"), "ThaumicFurnace");
	
	}

	
}
