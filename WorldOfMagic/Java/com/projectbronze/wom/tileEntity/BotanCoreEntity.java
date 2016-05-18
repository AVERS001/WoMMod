package com.projectbronze.wom.tileEntity;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import vazkii.botania.common.block.ModBlocks;
import vazkii.botania.common.block.tile.mana.TilePool;

import com.projectbronze.wom.registry.BlockRegistry;
public class BotanCoreEntity extends GenericCoreEntity{
	
	private int lastside = 0;
	private ItemStack mainblock = new ItemStack(ModBlocks.storage, 1, 1);
	private Block portalblock = BlockRegistry.botanPortal;
	
    
   
   
    private int resettime = 20;
	@Override
	public void updateEntity() {
		resettime--;
		if(resettime == 0)
		{
			switch (checkStructure(worldObj, xCoord, yCoord, zCoord, mainblock))
			{
			case(1): 
			{
				clearx(worldObj);
				TileEntity pool1 = worldObj.getTileEntity(xCoord + 2, yCoord, zCoord + 2);
				TileEntity pool2 = worldObj.getTileEntity(xCoord - 2, yCoord, zCoord + 2);
				if(pool1 instanceof TilePool && pool2 instanceof TilePool)
				{
					if(((TilePool) pool1).getCurrentMana() >= 2000 && ((TilePool) pool2).getCurrentMana() >= 2000)
					{
						((TilePool) pool1).recieveMana(-2000);
						((TilePool) pool2).recieveMana(-2000);
						placex(worldObj, portalblock);
						lastside = 1;
					}
				}	
				break;
			}
			case(2):
			{
					clearz(worldObj);
					TileEntity pool1 = worldObj.getTileEntity(xCoord + 2, yCoord, zCoord + 2);
					TileEntity pool2 = worldObj.getTileEntity(xCoord + 2, yCoord, zCoord - 2);
					if(pool1 instanceof TilePool && pool2 instanceof TilePool)
					{
						if(((TilePool) pool1).getCurrentMana() >= 20 && ((TilePool) pool2).getCurrentMana() >= 20)
						{
							((TilePool) pool1).recieveMana(-20);
							((TilePool) pool2).recieveMana(-20);
							placez(worldObj, portalblock);
							lastside = 2;
						}
					}	
				break;
			}
			case(3):
			{
					clearx(worldObj);
					TileEntity pool1 = worldObj.getTileEntity(xCoord + 2, yCoord, zCoord - 2);
					TileEntity pool2 = worldObj.getTileEntity(xCoord - 2, yCoord, zCoord - 2);
					
					if(pool1 instanceof TilePool)
					{
						if(((TilePool) pool1).getCurrentMana() >= 2000 && ((TilePool) pool2).getCurrentMana() >= 2000)
						{
							((TilePool) pool1).recieveMana(-2000);
							((TilePool) pool2).recieveMana(-2000);
							placex(worldObj, portalblock);
							lastside = 1;
						}
					}	
				break;
				}
				case(4):
				{
						clearz(worldObj);
						TileEntity pool1 = worldObj.getTileEntity(xCoord - 2, yCoord, zCoord + 2);
						TileEntity pool2 = worldObj.getTileEntity(xCoord - 2, yCoord, zCoord - 2);
						if(pool1 instanceof TilePool && pool2 instanceof TilePool)
						{
							if(((TilePool) pool1).getCurrentMana() >= 20 && ((TilePool) pool2).getCurrentMana() >= 20)
							{
								((TilePool) pool1).recieveMana(-20);
								((TilePool) pool2).recieveMana(-20);
								placez(worldObj, portalblock);
								lastside = 2;
							}
						}
					break;
				}
				default:
				{
					switch (lastside)
					{
						case(1): 
						{
							clearx(worldObj);
							break;
						}
						case(2):
						{
							clearz(worldObj);
							break;
						}
					}
				}
			}
		resettime = 20;
		}
	}
	
	
	//Additional check for mana pools
	@Override
	public int checkStructure(World worldObj, int x, int y, int z, ItemStack mainblock) {
		Block pool = ModBlocks.pool;
		switch (super.checkStructure(worldObj, x, y, z, mainblock)) 
		{
			case(1):
			{
				if(worldObj.getBlock(x + 2, y, z + 2) == pool)
				{
					if(worldObj.getBlock(x - 2, y, z + 2) == pool)
						return 1; 
					if(worldObj.getBlock(x + 2, y, z - 2) == pool)
						if(worldObj.getBlock(x - 2, y, z - 2) == pool)
							return 3;
				}
			}
			case(2):
			{
				if(worldObj.getBlock(x + 2, y, z + 2) == pool)
					if(worldObj.getBlock(x + 2, y, z - 2) == pool)
					return 2;
				if(worldObj.getBlock(x - 2, y, z + 2) == pool)
					if(worldObj.getBlock(x - 2, y, z - 2) == pool)
					return 4;
			
			}
		}
		return 0;
	}
}
