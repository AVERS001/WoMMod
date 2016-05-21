package com.projectbronze.wom.gui.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import DummyCore.Client.GuiCommon;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.container.BloodyPortalContainer;
import com.projectbronze.wom.gui.container.EssentialCoreContainer;
import com.projectbronze.wom.gui.container.PotionBeltContainer;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;
import com.projectbronze.wom.tileEntity.EssentialCoreEntity;

import ec3.api.ITEHasMRU;
import ec3.client.gui.element.GuiBalanceState;
import ec3.client.gui.element.GuiMRUState;
import ec3.client.gui.element.GuiMRUStorage;




public class PotionBeltGUI extends GuiCommon {
	public PotionBeltGUI(EntityPlayer player) {
		super(new PotionBeltContainer(player));
        
    }
	
}
