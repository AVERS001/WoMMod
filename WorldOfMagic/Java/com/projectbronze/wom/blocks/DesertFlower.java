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
    private IIcon[] icons = new IIcon[3];
    /**
     * @param meta
     */
    public DesertFlower(String name)
    {
    	super(1);
        setCreativeTab(WomCore.tabWoM);
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
    public int damageDropped(int meta) {
    	return meta;
    }
    
    @Override
	public void registerBlockIcons(IIconRegister reg){
		for(int i = 0; i < 3; i++)
		{
			icons[i] = reg.registerIcon(WomCore.modid + ":DesertFlower-" + i);
		}
	}
    
    @Override
	public IIcon getIcon(int side, int meta){
		return icons[meta];
	}
    
    @Override
	public void getSubBlocks(Item item, CreativeTabs tab, List list){
    	for(int i = 0; i < 3; i++)
    	{
    		list.add(new ItemStack(Item.getItemFromBlock(this), 1, i));
    	}
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

