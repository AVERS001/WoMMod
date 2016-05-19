package com.projectbronze.wom.tileEntity;

import java.util.UUID;

import javax.sound.midi.SysexMessage;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.projectbronze.wom.gui.slot.BoundGemSlot;
import com.projectbronze.wom.registry.BlockRegistry;

import ec3.api.ITERequiresMRU;
import ec3.common.block.BlocksCore;
import ec3.common.tile.TileMRUGeneric;
import ec3.utils.common.ECUtils;
public class EssentialCoreEntity extends TileMRUGeneric{
	
	private int lastside = 0;
	private int resettime = 20;
	private ItemStack mainblock = new ItemStack(BlocksCore.demonicPlating, 1, 0);
	private Block portalblock = BlockRegistry.essentialPortal;
	private int maxmru = 500000, mrupersecond = 250000;
	private float balance = 0;
	private UUID uuid = UUID.randomUUID();
	
	public EssentialCoreEntity() {
		this.setSlotsNum(1);
		this.setMaxMRU(maxmru);
		this.setBalance(balance);
	}
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote)
		{
			System.out.println(this.getMRU());
			if(resettime > 0)
			{
			resettime--;
			}
			else
			{
				int structure = checkStructure(worldObj, xCoord, yCoord, zCoord, mainblock);
				
				if(this.getMRU() - mrupersecond >= 0 && structure != 0)
				{
					this.setMRU(this.getMRU() - mrupersecond);
						switch (structure)
						{
							case(1): 
							{
									lastside = 1;
									clearx(worldObj);	
									placex(worldObj, portalblock);
								break;
							}
							case(2):
							{
									lastside = 2;
									clearz(worldObj);
									placez(worldObj, portalblock);
								break;
							}
					}
				}
				else
				{
					switch (lastside)
					{
						case(1): 
						{
							clearx(worldObj);
							lastside = 0;
							break;
						}
						case(2):
						{
							clearz(worldObj);
							lastside = 0;
							break;
						}
					}
				}
			
				resettime = 20;
			}
			ECUtils.manage(this, 0);
			super.updateEntity();
	}
}
	@Override
	public int[] getOutputSlots() {
		return new int[]{0};
	}
	
	
	/**
	 * Place blocks in 3x3 over tile in x axis
	 * 
	 * @param worldObj - world
	 * @param block - block to place
	 */
	public void placex(World worldObj, Block block)
	{
		for(int x = xCoord - 1; x < xCoord + 2; x++)
		{
			for(int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(x, y, zCoord, block, 0, 6);
			}
		}
	}
	
	/**
	 * Place blocks in 3x3 over tile in z axis
	 * 
	 * @param worldObj - world
	 * @param block - block to place
	 */
	public void placez(World worldObj, Block block)
	{
		for(int z = zCoord - 1; z < zCoord + 2; z++)
		{
			for(int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(xCoord, y, z, block, 1, 6);
				
			}
		}
	}
	
	/**
	 * Clear blocks in 3x3 over tile in x axis
	 * 
	 * @param worldObj - world
	 * 
	 */
	public void clearx(World worldObj)
	{
		for(int x = xCoord - 1; x < xCoord + 2; x++)
		{
			for(int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(x, y, zCoord, Blocks.air);
			}
		}
	}
	
	
	/**
	 * Clear blocks in 3x3 over tile in z axis
	 * 
	 * @param worldObj - world
	 * 
	 */
	public void clearz(World worldObj)
	{
		for(int z = zCoord - 1; z < zCoord + 2; z++)
		{
			for(int y = yCoord + 1; y < yCoord + 4; y++)
			{
				worldObj.setBlock(xCoord, y, z, Blocks.air);
			}
		}
	}
	
	public int checkStructure(World worldObj, int x, int y, int z, ItemStack mainblock)
	{
		Block block = Block.getBlockFromItem(mainblock.getItem());
		int damage = mainblock.getItemDamage();

		
		
		
		if(worldObj.getBlock(x - 1, y, z) == block && worldObj.getBlockMetadata(x - 1, y, z) == damage)
			if(worldObj.getBlock(x - 2, y + 1, z) == block && worldObj.getBlockMetadata(x - 2, y + 1, z) == damage)
				if(worldObj.getBlock(x - 2, y + 2, z) == block && worldObj.getBlockMetadata(x - 2, y + 2, z) == damage)
					if(worldObj.getBlock(x - 2, y + 3, z) == block && worldObj.getBlockMetadata(x - 2, y + 3, z) == damage)
						if(worldObj.getBlock(x - 1, y + 4, z) == block && worldObj.getBlockMetadata(x - 1, y + 4, z) == damage)
							if(worldObj.getBlock(x, y + 4, z) == block && worldObj.getBlockMetadata(x, y + 4, z) == damage)
								if(worldObj.getBlock(x + 1, y + 4, z) == block && worldObj.getBlockMetadata(x + 1, y + 4, z) == damage)
									if(worldObj.getBlock(x + 2, y + 3, z) == block && worldObj.getBlockMetadata(x + 2, y + 3, z) == damage)
										if(worldObj.getBlock(x + 2, y + 2, z) == block && worldObj.getBlockMetadata(x + 2, y + 2, z) == damage)
											if(worldObj.getBlock(x + 2, y + 1, z) == block && worldObj.getBlockMetadata(x + 2, y + 1, z) == damage)
												if(worldObj.getBlock(x + 1, y, z) == block && worldObj.getBlockMetadata(x + 1, y, z) == damage)
													return 1;
		if(worldObj.getBlock(x, y, z - 1) == block && worldObj.getBlockMetadata(x, y, z - 1) == damage)
			if(worldObj.getBlock(x, y + 1, z - 2) == block && worldObj.getBlockMetadata(x, y + 1, z - 2) == damage)
				if(worldObj.getBlock(x, y + 2, z - 2) == block && worldObj.getBlockMetadata(x, y + 2, z - 2) == damage)
					if(worldObj.getBlock(x, y + 3, z - 2) == block && worldObj.getBlockMetadata(x, y + 3, z - 2) == damage)
						if(worldObj.getBlock(x, y + 4, z - 1) == block && worldObj.getBlockMetadata(x, y + 4, z - 1) == damage)
							if(worldObj.getBlock(x, y + 4, z) == block && worldObj.getBlockMetadata(x, y + 4, z) == damage)
								if(worldObj.getBlock(x, y + 4, z + 1) == block && worldObj.getBlockMetadata(x, y + 4, z + 1) == damage)
									if(worldObj.getBlock(x, y + 3, z + 2) == block && worldObj.getBlockMetadata(x, y + 3, z + 2) == damage)
										if(worldObj.getBlock(x, y + 2, z + 2) == block && worldObj.getBlockMetadata(x, y + 2, z + 2) == damage)
											if(worldObj.getBlock(x, y + 1, z + 2) == block && worldObj.getBlockMetadata(x, y + 1, z + 2) == damage)
												if(worldObj.getBlock(x, y, z + 1) == block && worldObj.getBlockMetadata(x, y, z + 1) == damage)
													return 2;
		return 0;
	}
	
	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return slot == 0 ? BoundGemSlot.isBoundGem(stack) : false;
	}
}
	
	