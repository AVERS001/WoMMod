package com.projectbronze.wom.blocks;

import java.util.List;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.tileEntity.ThaumPortalEntity;

public class GenericPortal extends BlockContainer{
	
	
	public GenericPortal(String unlocName) {
		super(Material.portal);
		setBlockTextureName(WomCore.modid + ":" + unlocName);
		setBlockName(unlocName);
		setBlockUnbreakable();
		setLightOpacity(8);
		setLightLevel(1.0F);
		setBlockBoundsForItemRender();
		setCreativeTab(null);
	
		
	}
	
	@Override
	public boolean isBlockSolid(IBlockAccess world, int x,
			int y, int z, int p_149747_5_) {
		return false;
	}

	@Override
	public int damageDropped(int meta) {
		return meta;
	}
	
	@Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 2; i++) {
	        list.add(new ItemStack(item, 1, i));
	    }
	}
	
	
	@Override
	public void setBlockBoundsBasedOnState(net.minecraft.world.IBlockAccess world, int x, int y, int z) {
		int meta = world.getBlockMetadata(x, y, z);
		if(meta == 0)
		{
			setBlockBounds(0.0F, 0.0F, 0.4F, 1.0F, 1.0F, 0.6F);
		}
		else
		{
			setBlockBounds(0.4F, 0.0F, 0.0F, 0.6F, 1.0F, 1.0F);
		}
	};
	
	
	@Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new ThaumPortalEntity();
    }
	
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	};
	
	@Override
	public boolean renderAsNormalBlock()
    {
        return false;
    }

}
