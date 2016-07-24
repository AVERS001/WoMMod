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
	
	private void adjustRotatePivotViaMeta(World world, int x, int y, int z) 
	{
		int meta = world.getBlockMetadata(x, y, z);
		GL11.glPushMatrix();
		GL11.glRotatef(meta * (-90), 0.0F, 0.0F, 1.0F); 
		GL11.glPopMatrix(); 
	}
	
	@Override 
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float scale) { 
		render((TimeReturnerEntity) te, x, y, z, scale);
	}
	
	private void render(TimeReturnerEntity te, double x, double y, double z, float scale)
	{
		
		GL11.glPushMatrix();
		GL11.glTranslatef((float) x + 0.5F, (float) y + 1.5F, (float) z + 0.5F);
		ResourceLocation textures = (new ResourceLocation(Core.modid, "models/TimeReturnerTexture.png"));
		Minecraft.getMinecraft().renderEngine.bindTexture(textures);
		GL11.glPushMatrix();
		GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F); 
		GL11.glRotatef(te.blockMetadata * 90F, 0F, 1F, 0F);
		this.model.render((Entity)null, 0.0F, 0.0F, -0.1F, 0.0F, 0.0F, 0.0625F);
		if(te.active)
		{
			GL11.glPushMatrix();
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
			Minecraft.getMinecraft().renderEngine.bindTexture(new ResourceLocation(Core.modid, "models/Hologram.png"));
			GL11.glTranslated(0, -1D, 0);
			hologram.render(null, 0F, 0F, -0.1F, 0F, 0F, 0.0625F);
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
		GL11.glPopMatrix();
	}
	
	
	private void adjustLightFixture(World world, int i, int j, int k, Block block) 
	{ 
		Tessellator tess = Tessellator.instance;
		float brightness = block.getLightValue(world, i, j, k); 
		int skyLight = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
		int modulousModifier = skyLight % 65536;
		int divModifier = skyLight / 65536;
		tess.setColorOpaque_F(brightness, brightness, brightness);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) modulousModifier, divModifier); 
	}
}
