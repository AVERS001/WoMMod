package com.projectbronze.wom.gui.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.gt22.gt22core.baseclasses.gui.GuiContainerGeneric;
import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.gui.container.BloodyPortalContainer;
import com.projectbronze.wom.tileEntity.BloodyCoreEntity;

public class BloodyCoreGUI extends GuiContainerGeneric
{
	private BloodyCoreEntity te;
	private static final ResourceLocation texture = new ResourceLocation(Core.modid, "textures/gui/BloodyPortal.png");

	public BloodyCoreGUI(IInventory playerInv, BloodyCoreEntity te)
	{
		super(new BloodyPortalContainer(playerInv, te));
		this.xSize = 176;
		this.ySize = 171;
		this.te = te;
	}
}
