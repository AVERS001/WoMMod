package com.projectbronze.wom.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.projectbronze.wom.gui.container.BloodyPortalContainer;
import com.projectbronze.wom.gui.container.EditContainer;
import com.projectbronze.wom.gui.container.EssentialCoreContainer;
import com.projectbronze.wom.gui.container.PotionBeltContainer;
import com.projectbronze.wom.gui.container.ThaumicFuranceContainer;
import com.projectbronze.wom.gui.container.TimeReturnerContainer;
import com.projectbronze.wom.gui.container.TradeContainer;
import com.projectbronze.wom.gui.gui.BloodyCoreGUI;
import com.projectbronze.wom.gui.gui.EditGUI;
import com.projectbronze.wom.gui.gui.EssentialCoreGUI;
import com.projectbronze.wom.gui.gui.PotionBeltGUI;
import com.projectbronze.wom.gui.gui.ThaumicFurnaceGUI;
import com.projectbronze.wom.gui.gui.TimeReturnerGUI;
import com.projectbronze.wom.gui.gui.TradeGUI;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;
import com.projectbronze.wom.tileEntity.EssentialCoreEntity;
import com.projectbronze.wom.tileEntity.ThaumicFurnaceEntity;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;
import com.projectbronze.wom.tileEntity.TradeTileEntity;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int TimeRetrunerID = 0;
	public static final int BloodyPortalID = 1;
	public static final int ThaumicFurnaceID = 2;
	public static final int EssentialCoreID = 3;
	public static final int PotionBeltID = 4;
	public static final int TradeID = 5;
	public static final int EditModeID = 6;
    @Override
    public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
        switch (ID)
        {
        case(TimeRetrunerID):
        {
        	return new TimeReturnerContainer(player.inventory, (TimeReturnerEntity) world.getTileEntity(x, y, z));
        }
        case(BloodyPortalID):
        {
        	return new BloodyPortalContainer(player.inventory, (BloodyCoreEntity) world.getTileEntity(x, y, z));
        }
        case(ThaumicFurnaceID):
        {
        	return new ThaumicFuranceContainer(player.inventory, (ThaumicFurnaceEntity) world.getTileEntity(x, y, z));
        }
        case(EssentialCoreID):
        {
        	return new EssentialCoreContainer(player.inventory, (EssentialCoreEntity) world.getTileEntity(x, y, z));
        }
        case(PotionBeltID):
        {
        	return new PotionBeltContainer(player);
        }
        case(TradeID):
        {
        	return new TradeContainer(player.inventory, (TradeTileEntity) world.getTileEntity(x, y, z));
        }
        case(EditModeID):
        {
        	return new EditContainer(player.inventory, (TradeTileEntity) world.getTileEntity(x, y, z));
        }
        default:
        {
        		return null;
        }
        }
    }

    @Override
    public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
    	switch (ID)
        {
        case(TimeRetrunerID):
        {
        	return new TimeReturnerGUI(player.inventory, (TimeReturnerEntity) world.getTileEntity(x, y, z));
        }
        case(BloodyPortalID):
        {
        	return new BloodyCoreGUI(player.inventory, (BloodyCoreEntity) world.getTileEntity(x, y, z));
        }
        case(ThaumicFurnaceID):
        {
        	return new ThaumicFurnaceGUI(player.inventory, (ThaumicFurnaceEntity) world.getTileEntity(x, y, z));
        }
        case(EssentialCoreID):
        {
        	return new EssentialCoreGUI(player.inventory, (EssentialCoreEntity) world.getTileEntity(x, y, z));
        }
        case(PotionBeltID):
        {
        	return new PotionBeltGUI(player);
        }
        case(TradeID):
        {
        	return new TradeGUI(player.inventory, (TradeTileEntity) world.getTileEntity(x, y, z));
        }
        case(EditModeID):
        {
        	return new EditGUI(player.inventory, (TradeTileEntity) world.getTileEntity(x, y, z));
        }
        default:
        {
        		return null;
        }
        }
    }
}