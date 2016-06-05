package com.projectbronze.wom.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import DummyCore.Utils.MiscUtils;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.registry.ItemRegistry;
import com.projectbronze.wom.tileEntity.TradeTileEntity;

public class TradeStation extends BlockContainer {

	public IIcon[] icons = new IIcon[3];
	public TradeStation(String unlocName) {
		super(Material.iron);
		setBlockName(unlocName);
		setHardness(1.0F);
		setCreativeTab(WomCore.tabWoM);
	}

	@Override
	public void registerBlockIcons(IIconRegister reg) {
		for(int i = 0; i < 3; i++)
		{
			icons[i] = reg.registerIcon(WomCore.modid + ":Trade-" + i);
		}
	}
	
	@Override
	public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLivingBase, ItemStack par6ItemStack)
    {
        int l = MathHelper.floor_double((double)(par5EntityLivingBase.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 2, 2);
        }

        if (l == 1)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 5, 2);
        }

        if (l == 2)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 3, 2);
        }

        if (l == 3)
        {
            par1World.setBlockMetadataWithNotify(par2, par3, par4, 4, 2);
        }

    }
	
	
	@Override
	public IIcon getIcon(int side, int meta) {
		return side == 1 ? icons[2] : side == meta ? icons[0] : icons[1];
	}

    
    @Override
    public TileEntity createNewTileEntity(World world, int par2)
    {
        return new TradeTileEntity();
    }
    @Override
    public boolean onBlockActivated(World worldObj, int x, int y, int z, EntityPlayer player, int p_149727_6_, float p_149727_7_, float p_149727_8_, float p_149727_9_) {
    	if(!worldObj.isRemote)
    	{
    		if(player.getHeldItem() != null && player.getHeldItem().getItem().equals(ItemRegistry.tradeEditor))
    		{
    			player.openGui(WomCore.instance, GuiHandler.EditModeID, worldObj, x, y, z);
    		}
    		else
    		player.openGui(WomCore.instance, GuiHandler.TradeID, worldObj, x, y, z);
    	}
    	
    	return true;
    }

    @Override
    public void breakBlock(World world, int x, int y,
    		int z, Block block, int par6) {
    	MiscUtils.dropItemsOnBlockBreak(world, x, y, z, block, par6);
    	super.breakBlock(world, x, y, z,
    			block, par6);
    }
}
