package com.projectbronze.wom.registry;

import net.minecraft.tileentity.TileEntity;
import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.tileEntity.CommonPortalEntity;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;
import com.projectbronze.wom.tileEntity.TradeTileEntity;
import com.projectbronze.wom.tileEntity.cores.AuraCoreEntity;
import com.projectbronze.wom.tileEntity.cores.BloodyCoreEntity;
import com.projectbronze.wom.tileEntity.cores.BotanCoreEntity;
import com.projectbronze.wom.tileEntity.cores.EnderCoreEntity;
import com.projectbronze.wom.tileEntity.cores.EssentialCoreEntity;
import com.projectbronze.wom.tileEntity.cores.ThaumCoreEntity;
import com.projectbronze.wom.tileEntity.cores.TwilightCoreEntity;
import cpw.mods.fml.common.registry.GameRegistry;

public final class TileEntityRegistery
{

	public static void registerTile(Class<? extends TileEntity> te)
	{
		GameRegistry.registerTileEntity(te, Core.modid + "." + te.getSimpleName());
	}

	public static final void init()
	{
		registerTile(ThaumCoreEntity.class);
		registerTile(BotanCoreEntity.class);
		registerTile(BloodyCoreEntity.class);
		registerTile(AuraCoreEntity.class);
		registerTile(EssentialCoreEntity.class);
		registerTile(TwilightCoreEntity.class);
		registerTile(EnderCoreEntity.class);
		registerTile(CommonPortalEntity.class);
		registerTile(TimeReturnerEntity.class);
		registerTile(TradeTileEntity.class);

	}

}
