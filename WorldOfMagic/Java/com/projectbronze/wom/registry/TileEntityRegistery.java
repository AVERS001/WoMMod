package com.projectbronze.wom.registry;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.tileEntity.AuraCoreEntity;
import com.projectbronze.wom.tileEntity.AuraPortalEntity;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;
import com.projectbronze.wom.tileEntity.BloodyPortalEntity;
import com.projectbronze.wom.tileEntity.BotanCoreEntity;
import com.projectbronze.wom.tileEntity.BotanPortalEntity;
import com.projectbronze.wom.tileEntity.ThaumCoreEntity;
import com.projectbronze.wom.tileEntity.ThaumPortalEntity;
import com.projectbronze.wom.tileEntity.ThaumicFurnaceEntity;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;

import cpw.mods.fml.common.registry.GameRegistry;

public final class TileEntityRegistery {
	
	
	public static final void init()
	{
		GameRegistry.registerTileEntity(ThaumCoreEntity.class, WomCore.modid + ".coreThaum");
		GameRegistry.registerTileEntity(ThaumPortalEntity.class, WomCore.modid + ".portalThaum");
		GameRegistry.registerTileEntity(BotanCoreEntity.class, WomCore.modid + ".coreBotan");
		GameRegistry.registerTileEntity(BotanPortalEntity.class, WomCore.modid + ".portalBotan");
		GameRegistry.registerTileEntity(BloodyCoreEntity.class, WomCore.modid + ".coreBloody");
		GameRegistry.registerTileEntity(BloodyPortalEntity.class, WomCore.modid + ".portalBloody");
		GameRegistry.registerTileEntity(AuraCoreEntity.class, WomCore.modid + ".coreAura");
		GameRegistry.registerTileEntity(AuraPortalEntity.class, WomCore.modid + ".portalAura");
		GameRegistry.registerTileEntity(TimeReturnerEntity.class, WomCore.modid + ".timeReturner");
		GameRegistry.registerTileEntity(ThaumicFurnaceEntity.class, WomCore.modid + ".thaumicFurnace");
	
	}
	
	
	
}
