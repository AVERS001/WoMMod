package com.projectbronze.wom.gui.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import DummyCore.Client.GuiCommon;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.container.BloodyPortalContainer;
import com.projectbronze.wom.gui.container.EssentialCoreContainer;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;
import com.projectbronze.wom.tileEntity.EssentialCoreEntity;

import ec3.api.ITEHasMRU;
import ec3.client.gui.element.GuiBalanceState;
import ec3.client.gui.element.GuiMRUState;
import ec3.client.gui.element.GuiMRUStorage;




public class EssentialCoreGUI extends GuiCommon {
	private EssentialCoreEntity te;
	public EssentialCoreGUI(IInventory playerInv, EssentialCoreEntity te) {
		super(new EssentialCoreContainer(playerInv, te), te);
        this.xSize = 176;
        this.ySize = 171;
        this.te = te;
        this.elementList.add(new GuiMRUStorage(7, 4, te));
		this.elementList.add(new GuiMRUState(25, 58, te, 0));
		this.elementList.add(new GuiBalanceState(25, 4, te));
    }
	
}
