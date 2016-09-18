package com.projectbronze.wom.tileEntity;

import java.util.List;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class CommonPortalEntity extends TileEntity
{
	private ItemStack input, output;

	/**
	 * Should not be used, it's added just becouse minecraft need empty constructor in tile
	 */
	@Deprecated
	public CommonPortalEntity()
	{
		
	}
	
	public CommonPortalEntity(ItemStack input, ItemStack output)
	{
		this.input = input;
		this.output = output;
	}

	private void spawnItem(ItemStack stack, World worldObj, int x, int y, int z)
	{
		worldObj.spawnEntityInWorld(new EntityItem(worldObj, x + 0.5, y + 1.5, z + 0.5, stack.copy()));
	}

	private void port(List<EntityItem> items)
	{
		EntityItem entity = items.get(0);
		ItemStack inputstack = entity.getEntityItem();
		if (ItemStack.areItemStacksEqual(inputstack, input))
		{
			spawnItem(output, worldObj, xCoord, yCoord, zCoord);
			inputstack.splitStack(1);
		}
	}

	private static int ticksBeforeItem = 100;

	@Override
	public void updateEntity()
	{
		if (!this.worldObj.isRemote)
		{
			if (ticksBeforeItem-- <= 0)
			{
				List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, this.getRenderBoundingBox().expand(0.6, 0.0, 0.6));
				if (!items.isEmpty())
				{
					port(items);
				}
				ticksBeforeItem = 20;
			}
		}
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		NBTTagCompound input = new NBTTagCompound();
		NBTTagCompound output = new NBTTagCompound();
		this.input.writeToNBT(input);
		this.output.writeToNBT(output);
		NBTTagCompound rec = new NBTTagCompound();
		rec.setTag("in", input);
		rec.setTag("out", output);
		nbt.setTag("recepie", rec);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagCompound rec = nbt.getCompoundTag("recepie");
		input = ItemStack.loadItemStackFromNBT(rec.getCompoundTag("in"));
		output = ItemStack.loadItemStackFromNBT(nbt.getCompoundTag("out"));
	}
}
