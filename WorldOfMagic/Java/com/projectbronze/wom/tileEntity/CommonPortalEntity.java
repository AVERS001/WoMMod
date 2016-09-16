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
		if (inputstack.getUnlocalizedName().equals(input.getUnlocalizedName()) && inputstack.getItemDamage() == input.getItemDamage())
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
			if (ticksBeforeItem > 0)
			{
				ticksBeforeItem--;
			}
			else
			{
				List<EntityItem> items = worldObj.getEntitiesWithinAABB(EntityItem.class, this.getRenderBoundingBox().expand(0.6, 0.0, 0.6));
				System.out.println(items.size());
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
		NBTTagList list = new NBTTagList();
		NBTTagCompound input = new NBTTagCompound();
		input.setByte("idx", (byte) 0);
		this.input.writeToNBT(input);
		NBTTagCompound output = new NBTTagCompound();
		output.setByte("idx", (byte) 1);
		this.output.writeToNBT(output);
		list.appendTag(input);
		list.appendTag(output);
		nbt.setTag("recepie", list);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList list = nbt.getTagList("recepie", 10);
		for(int i = 0; i < list.tagCount(); i++)
		{
			if(list.getCompoundTagAt(i).getByte("idx") == 0)
			{
				input = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(i));
			}
			else
			{
				output = ItemStack.loadItemStackFromNBT(list.getCompoundTagAt(i));
			}
		}
	}
}
