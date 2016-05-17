package com.projectbronze.wom.blocks;

import java.util.List;

import thaumcraft.api.ItemApi;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.tileEntity.ThaumicFurnaceEntity;

public class ThaumicFurnace extends BlockContainer {
	
	public ThaumicFurnace(String unlocName) {
		super(Material.portal);
		setBlockTextureName(WomCore.modid + ":" + unlocName);
		setBlockName(unlocName);
		setCreativeTab(WomCore.tabWoM);
		setHardness(0.5F);
		setHarvestLevel("pickaxe", 1);		
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new ThaumicFurnaceEntity();
	}
	
	@Override
    public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    	if(!worldObj.isRemote)
    	{
	    		if(player.getHeldItem() != null && player.getHeldItem().getItem() == ItemApi.getBlock("blockTube", 1).getItem())
	    		{
	    			System.out.println("tube");
	    			return false;
	    		}
    			player.openGui(WomCore.instance, GuiHandler.ThaumicFurnaceID, worldObj, x, y, z);
    	}
    	
    	return true;
    }
	

}