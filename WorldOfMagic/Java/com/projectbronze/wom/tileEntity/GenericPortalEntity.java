package com.projectbronze.wom.tileEntity;

import java.util.List;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class GenericPortalEntity extends TileEntity {
	private void spawnItem(ItemStack stack, World worldObj, int x, int y, int z) {
		EntityItem item = new EntityItem(worldObj, x + 0.5, y + 1.5, z + 0.5, stack);
		worldObj.spawnEntityInWorld(item);
}
	
	private void port(World worldObj, int x, int y, int z, ItemStack inputitem, ItemStack retrunstack, List <EntityItem> items)
	{
		
			EntityItem entity = items.get(0);
			ItemStack input = entity.getEntityItem();
			if(input.getUnlocalizedName().equals(inputitem.getUnlocalizedName()) && input.getItemDamage() == inputitem.getItemDamage())
			{
				spawnItem(retrunstack, worldObj, xCoord, yCoord, zCoord);
				input.splitStack(1);
			}
	}
	private static int ticksBeforeItem = 100;
	/**
	 * Call in updateEntity
	 * @param worldObj
	 * @param x
	 * @param y
	 * @param z
	 * @param input - ItemStack that must be throwed in portal
	 * @param output - ItemStack that portal will give to you afte you throw input stack
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
				List <EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, this.getRenderBoundingBox().expand(0.1, 0.1, 0.1));
				if(!items.isEmpty())
				{
						port(worldObj, x, y, z, input, output, items);			
				}
				ticksBeforeItem = 20;
			}
		}
	}

}
