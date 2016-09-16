package com.projectbronze.wom.registry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import twilightforest.item.TFItems;
import vazkii.botania.common.item.ModItems;
import WayofTime.alchemicalWizardry.ModBlocks;
import com.gt22.gt22core.baseclasses.block.GenericBlock;
import com.projectbronze.wom.blocks.AngelSteelBlock;
import com.projectbronze.wom.blocks.CloudBlock;
import com.projectbronze.wom.blocks.CommonPortal;
import com.projectbronze.wom.blocks.DesertFlower;
import com.projectbronze.wom.blocks.ItemBlockMetaBlock;
import com.projectbronze.wom.blocks.ThaumicFurnace;
import com.projectbronze.wom.blocks.TimeReturner;
import com.projectbronze.wom.blocks.TradeStation;
import com.projectbronze.wom.blocks.cores.BloodyCore;
import com.projectbronze.wom.blocks.cores.EssentialCore;
import com.projectbronze.wom.blocks.cores.GenericCore;
import com.projectbronze.wom.tileEntity.cores.AuraCoreEntity;
import com.projectbronze.wom.tileEntity.cores.BotanCoreEntity;
import com.projectbronze.wom.tileEntity.cores.EnderCoreEntity;
import com.projectbronze.wom.tileEntity.cores.ThaumCoreEntity;
import com.projectbronze.wom.tileEntity.cores.TwilightCoreEntity;
import cpw.mods.fml.common.registry.GameRegistry;
import crazypants.enderio.EnderIO;
import ec3.common.item.ItemsCore;

public final class BlockRegistry
{

	//Cores
	public static Block thaumCore;
	public static Block botanCore;
	public static Block bloodyCore;
	public static Block auraCore;
	public static Block essentialCore;
	public static Block twilightCore;
	public static Block enderCore;
	//Portals
	public static Block thaumPortal;
	public static Block botanPortal;
	public static Block bloodyPortal;
	public static Block auraPortal;
	public static Block essentialPortal;
	public static Block twilightPortal;
	public static Block enderPortal;
	//Misc
	public static Block timeReturner;
	public static Block angelSteelBlock;
	public static Block thaumicFurnace;
	public static Block desertFlower;
	public static Block tradeStation;
	public static Block cloudBlock;
	public static GenericBlock decoBlock;

	public static void register(Block block)
	{
		GameRegistry.registerBlock(block, block.getUnlocalizedName());
	}

	public static void register(Block block, Class<? extends ItemBlock> itemblock)
	{
		GameRegistry.registerBlock(block, itemblock, block.getUnlocalizedName());
	}

	public static final void init()
	{
		register(thaumPortal = new CommonPortal("thaumPortal", ItemApi.getItem("itemEldritchObject", 3), new ItemStack(ItemRegistry.timeShard, 1, 0)), ItemBlockMetaBlock.class);
		register(thaumCore = new GenericCore("thaumCore", thaumCore, ThaumCoreEntity.class));
		register(botanPortal = new CommonPortal("botanPortal", new ItemStack(ModItems.laputaShard, 1, 19), new ItemStack(ItemRegistry.timeShard, 1, 1)), ItemBlockMetaBlock.class);
		register(botanCore = new GenericCore("botanCore", botanPortal, BotanCoreEntity.class));
		register(bloodyPortal = new CommonPortal("bloodyPortal", new ItemStack(ModBlocks.blockStabilityGlyph, 1, 0), new ItemStack(ItemRegistry.timeShard, 1, 2)), ItemBlockMetaBlock.class);
		register(bloodyCore = new BloodyCore("bloodyCore", bloodyPortal));
		register(angelSteelBlock = new AngelSteelBlock("AngelSteelBlock"));
		register(auraPortal = new CommonPortal("auraPortal", new ItemStack(angelSteelBlock, 1, 0), new ItemStack(ItemRegistry.timeShard, 1, 3)), ItemBlockMetaBlock.class);
		register(auraCore = new GenericCore("auraCore", auraPortal, AuraCoreEntity.class));
		register(essentialPortal = new CommonPortal("essentialPortal", new ItemStack(ItemsCore.genericItem, 1, 56), new ItemStack(ItemRegistry.timeShard, 1, 4)), ItemBlockMetaBlock.class);
		register(essentialCore = new EssentialCore("essentialCore", essentialPortal));
		register(twilightPortal = new CommonPortal("twilightPortal", new ItemStack(TFItems.charmOfKeeping3, 1, 0), new ItemStack(ItemRegistry.timeShard, 1, 5)), ItemBlockMetaBlock.class);
		register(twilightCore = new GenericCore("twilightCore", twilightPortal, TwilightCoreEntity.class));
		register(enderPortal = new CommonPortal("enderPortal", new ItemStack(EnderIO.itemFrankenSkull, 1, 4), new ItemStack(ItemRegistry.timeShard, 1, 6)));
		register(enderCore = new GenericCore("enderCore", enderPortal, EnderCoreEntity.class));
		register(timeReturner = new TimeReturner("timeReturner"));
		register(tradeStation = new TradeStation("TradeStation"));
		register(desertFlower = new DesertFlower("DesertFlower"), ItemBlockMetaBlock.class);
		register(thaumicFurnace = new ThaumicFurnace("ThaumicFurnace"));
		// decoBlock = new GenericBlock();
		// register(decoBlock, ItemBlockMetaBlock.class);
		register(cloudBlock = new CloudBlock("CloudBlock"));

	}

}
