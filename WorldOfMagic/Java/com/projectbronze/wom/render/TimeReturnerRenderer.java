package com.projectbronze.wom.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

import com.projectbronze.wom.core.Core;
import com.projectbronze.wom.render.model.HologramModel;
import com.projectbronze.wom.render.model.TimeReturnerModel;
import com.projectbronze.wom.tileEntity.TimeReturnerEntity;

public class TimeReturnerRenderer extends TileEntitySpecialRenderer {

	private TimeReturnerModel model;
	private HologramModel hologram;
	public TimeReturnerRenderer() {
		model = new TimeReturnerModel();
		hologram = new HologramModel();
	}
	
	@Override 
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) { 
		render((TimeReturnerEntity) te, x, y, z);
	}
	
	private void render(TimeReturnerEntity te, double x, double y, double z)
	{
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		ResourceLocation textures = (new ResourceLocation(Core.modid, "models/TimeReturnerTexture.png"));
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F); 
		GL11.glRotatef(te.getBlockMetadata() * 90F, 0F, 1F, 0F);
		this.model.render(null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if(te.active)
		{
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Core.modid, "models/Hologram.png"));
			GL11.glTranslated(0, -1D, 0);
			hologram.render(null, 0F, 0F, -0.1F, 0F, 0F, 0.0625F);
			GL11.glDisable(GL11.GL_BLEND);
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
}
