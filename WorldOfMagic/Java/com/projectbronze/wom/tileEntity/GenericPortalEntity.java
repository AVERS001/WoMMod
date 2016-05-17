package com.projectbronze.wom.tileEntity;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GenericPortalEntity extends TileEntity {
	public void spawnItem(ItemStack stack, World worldObj, int x, int y, int z) {
		EntityItem item = new EntityItem(worldObj, x + 0.5, y + 1.5, z + 0.5, stack);
		worldObj.spawnEntityInWorld(item);
}
	
	public void port(World worldObj, int x, int y, int z, ItemStack inputitem, ItemStack retrunstack, List items)
	{
		
			EntityItem entity = (EntityItem) items.get(0);
			ItemStack input = entity.getEntityItem();
			if(input.getUnlocalizedName().equals(inputitem.getUnlocalizedName()) && input.getItemDamage() == inputitem.getItemDamage())
			{
				spawnItem(retrunstack, worldObj, xCoord, yCoord, zCoord);
				input.splitStack(1);
			}
	}
	private static int ticksBeforeItem = 100;
	/*
	 * Call this methot in updateEntity()
	 */
	public void update(World worldObj, int x, int y, int z, ItemStack input, ItemStack output)
	{
		if (!this.worldObj.isRemote)
		{
			if(ticksBeforeItem > 0)
			{
				ticksBeforeItem--;
			}
			else
			{
				List items = worldObj.getEntitiesWithinAABB(EntityItem.class, this.getRenderBoundingBox().expand(0.4, 0.4, 0.4));
				if(!items.isEmpty())
				{
					System.out.println("Item");
						port(worldObj, x, y, z, input, output, items);			
				}
				ticksBeforeItem = 20;
			}
		}
	}

}
