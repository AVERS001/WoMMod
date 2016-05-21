package com.projectbronze.wom.blocks;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.BlockFlower;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.oredict.OreDictionary;

import com.projectbronze.wom.core.WomCore;

public class DesertFlower extends BlockFlower
{


	static HashSet<Block> blockSands = null;
    
    /**
     * @param meta
     */
    public DesertFlower(String name)
    {
    	super(1);
        setCreativeTab(WomCore.tabWoM);
        setBlockName("TestFlower");
        setBlockTextureName("TestFlower");
        setBlockName(name);
		setBlockTextureName(name);
		if(blockSands == null)
		{
			Collection<ItemStack> itemStackSands = OreDictionary.getOres("sand", false);
			blockSands = new HashSet<Block>(itemStackSands.size());
			for (ItemStack itemStack : itemStackSands){
				Block oreBlock = Block.getBlockFromItem(itemStack.getItem());
				if (oreBlock != Blocks.air){
					blockSands.add(oreBlock);
				}
			}
		}
    }
    
    @Override
	public void registerBlockIcons(IIconRegister reg){
		this.blockIcon = reg.registerIcon(WomCore.modid + ":" + textureName);
	}
    
    @Override
	public IIcon getIcon(IBlockAccess p_149673_1_, int p_149673_2_, int p_149673_3_, int p_149673_4_, int p_149673_5_){
		return this.blockIcon;
	}
    
    @Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_){
		return blockIcon;
	}
    
    @Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list){
		list.add(new ItemStack(Item.getItemFromBlock(this), 1, 0));
	}

    public boolean canGrowOn(World worldIn, int x, int y, int z) {
		return canBlockStay(worldIn, x, y, z);
	}
    
    @Override
    protected boolean canPlaceBlockOn(Block block) {
    	return block == Blocks.sand || blockSands != null && blockSands.contains(block); 
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
    	return EnumPlantType.Desert;
    }
    
}

