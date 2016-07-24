package com.projectbronze.wom.gui.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.gui.container.TradeContainer;
import com.projectbronze.wom.tileEntity.TradeTileEntity;

public class TradeGUI extends GuiContainer
{
	private TradeTileEntity te;

	public TradeGUI(IInventory playerInv, TradeTileEntity te)
	{
		super(new TradeContainer(playerInv, te));
		this.te = te;
	}

	private static final ResourceLocation texture = new ResourceLocation(Core.modid, "textures/gui/TradeGUI.png");

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
	}

	/*
	 * @Override
	 * public void initGui() {
	 * this.buttonList.add(editmode);
	 * }
	 */

}
