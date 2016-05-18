package com.projectbronze.wom.gui;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.projectbronze.wom.gui.client.BloodyCoreGUI;
import com.projectbronze.wom.gui.client.TimeReturnerGUI;
import com.projectbronze.wom.gui.container.BloodyPortalContainer;
import com.projectbronze.wom.gui.container.ThaumicFuranceContainer;
import com.projectbronze.wom.gui.container.TimeReturnerContainer;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;
import com.projectbronze.wom.tileEntity.ThaumicFurnaceEntity;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;

import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	public static final int TimeRetrunerID = 0;
	public static final int BloodyPortalID = 1;
	public static final int ThaumicFurnaceID = 2;
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
        	//return new ThaumicFuranceContainer(player.inventory, (ThaumicFurnaceEntity) world.getTileEntity(x, y, z));
        	return null;
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
        	//return new ThaumicFurnaceGUI(player.inventory, (ThaumicFurnaceEntity) world.getTileEntity(x, y, z));
        	return null;
        }
        default:
        {
        		return null;
        }
        }
    }
}