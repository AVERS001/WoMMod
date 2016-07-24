package com.projectbronze.wom.gui.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.gui.container.ThaumicFuranceContainer;
import com.projectbronze.wom.tileEntity.ThaumicFurnaceEntity;

public class ThaumicFurnaceGUI extends GuiContainer
{
	private ThaumicFurnaceEntity te;
	private static final ResourceLocation texture = new ResourceLocation(Core.modid, "textures/gui/ThaumicFurance.png");

	public ThaumicFurnaceGUI(IInventory playerInv, ThaumicFurnaceEntity te)
	{
		super(new ThaumicFuranceContainer(playerInv, te));
		this.xSize = 176;
		this.ySize = 166;
		this.te = te;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY)
	{
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		mc.getTextureManager().bindTexture(texture);
		int k = (width - xSize) / 2;
		int l = (height - ySize) / 2;
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize);

	}
}
