package com.projectbronze.wom.tileEntity.cores;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.ItemApi;
import thaumcraft.api.ThaumcraftApiHelper;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import thaumcraft.api.aspects.IEssentiaTransport;
import com.projectbronze.wom.registry.BlockRegistry;

public class ThaumCoreEntity extends GenericCoreEntity implements IAspectContainer, IEssentiaTransport
{

	public AspectList aspects = new AspectList();
	public static final int maxAspects = 128;
	private static final int reqamount = 16;
	private static final Aspect reqaspect = Aspect.ELDRITCH;

	public ThaumCoreEntity()
	{
		super(ItemApi.getBlock("blockCosmeticSolid", 4), BlockRegistry.thaumPortal);
	}

	@Override
	public void updateEntity()
	{
		for (int i = 0; i < 6; ++i)
		{
			ForgeDirection facing = ForgeDirection.getOrientation(i);
			TileEntity te = ThaumcraftApiHelper.getConnectableTile(getWorldObj(), xCoord, yCoord, zCoord, facing);
			if (te != null)
			{
				IEssentiaTransport ic = (IEssentiaTransport) te;
				if (ic.canOutputTo(facing.getOpposite()))
				{
					if (this.getAspects().getAmount(reqaspect) + 1 < this.maxAspects && ic.getSuctionAmount(facing.getOpposite()) < getSuctionAmount(facing) && ic.takeEssentia(reqaspect, 1, facing.getOpposite()) == 1)
					{
						this.addEssentia(reqaspect, 1, facing);
					}
				}
			}
		}
		super.updateEntity();
	}

	@Override
	public void writeToNBT(NBTTagCompound compound)
	{
		super.writeToNBT(compound);
		aspects.writeToNBT(compound);
	}

	@Override
	public void readFromNBT(NBTTagCompound compound)
	{
		super.readFromNBT(compound);
		aspects.readFromNBT(compound);
	}

	@Override
	public AspectList getAspects()
	{
		return aspects;
	}

	@Override
	public void setAspects(AspectList aspects)
	{
		this.aspects = aspects;

	}

	@Override
	public boolean doesContainerAccept(Aspect aspect)
	{
		return doesContainerContainAmount(aspect, 1);
	}

	@Override
	public int addToContainer(Aspect aspects, int amount)
	{
		if (amount > maxAspects)
			amount = maxAspects;
		if (this.aspects.getAmount(aspects) == 0)
		{
			this.aspects.add(aspects, amount);
			return amount;
		}
		else
		{
			if (this.aspects.getAmount(aspects) + amount < maxAspects)
			{
				this.aspects.merge(aspects, this.aspects.getAmount(aspects) + amount);
				return amount;
			}
		}
		return 0;
	}

	@Override
	public boolean takeFromContainer(Aspect tag, int amount)
	{
		if (this.aspects.getAmount(tag) > 0 && this.aspects.getAmount(tag) - amount >= 0)
		{
			this.aspects.reduce(tag, amount);
			return true;
		}
		return false;
	}

	@Override
	public boolean takeFromContainer(AspectList aspects)
	{
		if (this.doesContainerContain(aspects))
		{
			for (Aspect apt : aspects.getAspectsSortedAmount())
			{
				this.takeFromContainer(apt, aspects.getAmount(apt));
			}
		}
		return false;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect tag, int amount)
	{
		return this.aspects.getAmount(tag) >= amount;
	}

	@Override
	public boolean doesContainerContain(AspectList aspects)
	{
		for (int o = 0; o < aspects.size(); o++)
		{
			Aspect apt = aspects.getAspectsSortedAmount()[o];
			if (this.aspects.getAmount(apt) == 0)
				return false;
		}
		return true;
	}

	@Override
	public int containerContains(Aspect tag)
	{
		return this.aspects.getAmount(tag);
	}

	@Override
	public boolean isConnectable(ForgeDirection face)
	{
		return face != ForgeDirection.UP;
	}

	@Override
	public boolean canInputFrom(ForgeDirection face)
	{
		return face != ForgeDirection.UP;
	}

	@Override
	public boolean canOutputTo(ForgeDirection face)
	{
		return false;
	}

	@Override
	public void setSuction(Aspect aspect, int amount)
	{

	}

	@Override
	public Aspect getSuctionType(ForgeDirection face)
	{
		return reqaspect;
	}

	@Override
	public int getSuctionAmount(ForgeDirection face)
	{
		return 128;
	}

	@Override
	public int takeEssentia(Aspect aspect, int amount, ForgeDirection face)
	{
		if (this.getSuctionType(face) == aspect)
			return this.addEssentia(aspect, amount, face);
		return 0;
	}

	@Override
	public int addEssentia(Aspect aspect, int amount, ForgeDirection face)
	{
		return this.addToContainer(aspect, amount);
	}

	@Override
	public Aspect getEssentiaType(ForgeDirection face)
	{
		return aspects.size() == 0 ? null : reqaspect;
	}

	@Override
	public int getEssentiaAmount(ForgeDirection face)
	{
		if (this.aspects.size() != 0)
			return this.aspects.getAmount(this.aspects.getAspectsSortedAmount()[0]);
		else
			return 0;
	}

	@Override
	public int getMinimumSuction()
	{
		return 0;
	}

	@Override
	public boolean renderExtendedTube()
	{
		return true;
	}

	@Override
	protected boolean canWork(int str)
	{
		return doesContainerContainAmount(reqaspect, reqamount);
	}

	@Override
	protected void work(int str)
	{
		takeFromContainer(reqaspect, reqamount);
	}

}
