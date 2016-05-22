package com.projectbronze.wom.gui.gui;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.container.EditContainer;
import com.projectbronze.wom.gui.container.TradeContainer;
import com.projectbronze.wom.tileEntity.TradeTileEntity;




public class EditGUI extends GuiContainer {
	private TradeTileEntity te;
	public EditGUI(IInventory playerInv, TradeTileEntity te) {
		super(new  EditContainer(playerInv, te));
        this.te = te;
    }
	
	
	private static final ResourceLocation texture =  new ResourceLocation(WomCore.modid, "textures/gui/TradeGUI.png");
	@Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
    	GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
    	mc.getTextureManager().bindTexture(texture);
    	int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
	}
	
	/*@Override
	public void initGui() {
		this.buttonList.add(editmode);
	}*/
	
}
