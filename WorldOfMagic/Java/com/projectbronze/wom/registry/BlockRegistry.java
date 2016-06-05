package com.projectbronze.wom.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;

import com.projectbronze.wom.blocks.AngelSteelBlock;
import com.projectbronze.wom.blocks.AuraCore;
import com.projectbronze.wom.blocks.AuraPortal;
import com.projectbronze.wom.blocks.BloodyCore;
import com.projectbronze.wom.blocks.BloodyPortal;
import com.projectbronze.wom.blocks.BotanCore;
import com.projectbronze.wom.blocks.BotanPortal;
import com.projectbronze.wom.blocks.CloudBlock;
import com.projectbronze.wom.blocks.DecoBlock;
import com.projectbronze.wom.blocks.DesertFlower;
import com.projectbronze.wom.blocks.EssentialCore;
import com.projectbronze.wom.blocks.EssentialPortal;
import com.projectbronze.wom.blocks.ItemBlockMetaBlock;
import com.projectbronze.wom.blocks.ThaumCore;
import com.projectbronze.wom.blocks.ThaumPortal;
import com.projectbronze.wom.blocks.ThaumicFurnace;
import com.projectbronze.wom.blocks.TimeReturner;
import com.projectbronze.wom.blocks.TradeStation;
import com.projectbronze.wom.blocks.TwilightCore;
import com.projectbronze.wom.blocks.TwilightPortal;

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
	public static Block essentialCore;
	public static Block essentialPortal;
	public static Block twilightCore;
	public static Block twilightPortal;
	public static Block timeReturner;
	public static Block AngelSteelBlock;
	public static Block thaumicFurnace;
	public static Block desertFlower;
	public static Block tradeStation;
	//public static DecoBlock decoBlock;
	public static Block cloudBlock;
	
	public static void register(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}
	
	public static void register(Block block, Class <? extends ItemBlock> itemblock)
	{
		GameRegistry.registerBlock(block, itemblock, block.getUnlocalizedName());
	}
	
	public static final void init()
	{
	
		register(thaumPortal = new ThaumPortal("portalThaumPortal"), ItemBlockMetaBlock.class);
		register(thaumCore = new ThaumCore("portalThaum", thaumPortal));
		register(botanPortal = new BotanPortal("portalBotanPortal"), ItemBlockMetaBlock.class);
		register(botanCore = new BotanCore("portalBotan", botanPortal));
		register(BloodyPortal = new BloodyPortal("portalBloodyPortal"), ItemBlockMetaBlock.class);
		register(bloodyCore = new BloodyCore("portalBloody", BloodyPortal));
		register(AngelSteelBlock = new AngelSteelBlock("AngelSteelBlock"));
		register(auraPortal = new AuraPortal("auraPortal"), ItemBlockMetaBlock.class);
		register(auraCore = new AuraCore("auraCore", auraPortal));
		register(twilightPortal = new TwilightPortal("twilightPortal"), ItemBlockMetaBlock.class);
		register(twilightCore = new TwilightCore("twilightCore", twilightPortal));
		register(essentialPortal = new EssentialPortal("essentialPortal"), ItemBlockMetaBlock.class);
		register(essentialCore = new EssentialCore("essentialCore", essentialPortal));
		register(timeReturner = new TimeReturner("timeReturner"), ItemBlock.class);
		register(tradeStation = new TradeStation("TradeStation"));
		register(desertFlower = new DesertFlower("DesertFlower"), ItemBlockMetaBlock.class);
		register(thaumicFurnace = new ThaumicFurnace("ThaumicFurnace"));
		//decoBlock = new DecoBlock();
		//register(decoBlock,  ItemBlockMetaBlock.class);
		register(cloudBlock = new CloudBlock("CloudBlock"));
	
	}

	
}
