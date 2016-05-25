package com.projectbronze.wom.registry;

import net.minecraft.tileentity.TileEntity;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.tileEntity.AuraCoreEntity;
import com.projectbronze.wom.tileEntity.AuraPortalEntity;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;
import com.projectbronze.wom.tileEntity.BloodyPortalEntity;
import com.projectbronze.wom.tileEntity.BotanCoreEntity;
import com.projectbronze.wom.tileEntity.BotanPortalEntity;
import com.projectbronze.wom.tileEntity.EssentialCoreEntity;
import com.projectbronze.wom.tileEntity.EssentialPortalEntity;
import com.projectbronze.wom.tileEntity.ThaumCoreEntity;
import com.projectbronze.wom.tileEntity.ThaumPortalEntity;
import com.projectbronze.wom.tileEntity.ThaumicFurnaceEntity;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;
import com.projectbronze.wom.tileEntity.TradeTileEntity;

import cpw.mods.fml.common.registry.GameRegistry;

public final class TileEntityRegistery {
	
	public static void registerTile(Class<? extends TileEntity> te)
	{
		GameRegistry.registerTileEntity(te, WomCore.modid + "." + te.getName());
	}
	
	public static final void init()
	{
		registerTile(ThaumCoreEntity.class);
		registerTile(ThaumPortalEntity.class);
		registerTile(BotanCoreEntity.class);
		registerTile(BotanPortalEntity.class);
		registerTile(BloodyCoreEntity.class);
		registerTile(BloodyPortalEntity.class);
		registerTile(AuraCoreEntity.class);
		registerTile(AuraPortalEntity.class);
		registerTile(EssentialCoreEntity.class);
		registerTile(EssentialPortalEntity.class);
		registerTile(TimeReturnerEntity.class);
		registerTile(ThaumicFurnaceEntity.class);
		registerTile(TradeTileEntity.class);
		
	}
	
	
	
}
