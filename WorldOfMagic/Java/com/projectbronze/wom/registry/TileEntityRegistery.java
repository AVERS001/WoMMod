package com.projectbronze.wom.registry;

import net.minecraft.tileentity.TileEntity;

import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.tileEntity.AuraCoreEntity;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;
import com.projectbronze.wom.tileEntity.BotanCoreEntity;
import com.projectbronze.wom.tileEntity.CommonPortalEntity;
import com.projectbronze.wom.tileEntity.EssentialCoreEntity;
import com.projectbronze.wom.tileEntity.ThaumCoreEntity;
import com.projectbronze.wom.tileEntity.ThaumicFurnaceEntity;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;
import com.projectbronze.wom.tileEntity.TradeTileEntity;
import com.projectbronze.wom.tileEntity.TwilightCoreEntity;

import cpw.mods.fml.common.registry.GameRegistry;

public final class TileEntityRegistery
{

	public static void registerTile(Class<? extends TileEntity> te)
	{
		GameRegistry.registerTileEntity(te, Core.modid + "." + te.getName());
	}

	public static final void init()
	{
		registerTile(ThaumCoreEntity.class);
		registerTile(BotanCoreEntity.class);
		registerTile(BloodyCoreEntity.class);
		registerTile(AuraCoreEntity.class);
		registerTile(EssentialCoreEntity.class);
		registerTile(TwilightCoreEntity.class);
		registerTile(CommonPortalEntity.class);
		registerTile(TimeReturnerEntity.class);
		registerTile(ThaumicFurnaceEntity.class);
		registerTile(TradeTileEntity.class);

	}

}
