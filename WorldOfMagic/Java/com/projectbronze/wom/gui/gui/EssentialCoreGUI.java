package com.projectbronze.wom.gui.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.container.BloodyPortalContainer;
import com.projectbronze.wom.gui.container.EssentialCoreContainer;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;
import com.projectbronze.wom.tileEntity.EssentialCoreEntity;




public class EssentialCoreGUI extends GuiContainer {
	private EssentialCoreEntity te;
	private static final ResourceLocation texture =  new ResourceLocation(WomCore.modid, "textures/gui/EssentialCore.png");
	public EssentialCoreGUI(IInventory playerInv, EssentialCoreEntity te) {
		super(new EssentialCoreContainer(playerInv, te));
        this.xSize = 176;
        this.ySize = 171;
        this.te = te;
    }
	
    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	mc.getTextureManager().bindTexture(texture);
    	int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
    	
    }
}
