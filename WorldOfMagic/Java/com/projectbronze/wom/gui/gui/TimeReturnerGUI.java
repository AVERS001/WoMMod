package com.projectbronze.wom.gui.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.gui.container.TimeReturnerContainer;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;

public class TimeReturnerGUI extends GuiContainer
{
	private TimeReturnerEntity te;
	private static final ResourceLocation texture = new ResourceLocation(Core.modid, "textures/gui/TimeReturner.png");

	public TimeReturnerGUI(IInventory playerInv, TimeReturnerEntity te)
	{
		super(new TimeReturnerContainer(playerInv, te));
		this.xSize = 176;
		this.ySize = 171;
		this.te = te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		mc.getTextureManager().bindTexture(texture);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);
		final int holox = 123, holoy = 8;
		if (this.te.active)
		{
			this.drawTexturedModalRect(k + holox, l + holoy, 180, 0, 32, 64);
		}
		else
		{
			this.drawTexturedModalRect(k + holox, l + holoy, 180, 65, 32, 64);
		}
		GL11.glDisable(GL11.GL_BLEND);
	}
}
