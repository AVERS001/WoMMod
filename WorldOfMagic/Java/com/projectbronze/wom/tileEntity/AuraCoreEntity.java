package com.projectbronze.wom.tileEntity;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

import com.projectbronze.wom.registry.BlockRegistry;
public class AuraCoreEntity extends GenericCoreEntity{
	
	private int lastside = 0;
	private int resettime = 20;
	private ItemStack mainblock = new ItemStack(BlockRegistry.AngelSteelBlock, 1, 0);
	private Block portalblock = BlockRegistry.auraPortal;
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote)
		{
			if(resettime > 0)
			{
			resettime--;
			}
			else
			{
				int structure = checkStructure(worldObj, xCoord, yCoord, zCoord, mainblock);
				if(structure != 0)
				{
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
			
	}
}
}
	
	