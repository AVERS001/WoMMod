package com.projectbronze.wom.tileEntity.cores;

import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyReceiver;
import com.projectbronze.wom.registry.BlockRegistry;
import crazypants.enderio.EnderIO;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.common.util.ForgeDirection;

public class EnderCoreEntity extends GenericCoreEntity implements IEnergyReceiver
{

	private EnergyStorage storage;
	public static final int MAX_ENERGY = 100000;
	public static final int ENERGY_USE = 10000;
	public EnderCoreEntity()
	{
		super(new ItemStack(EnderIO.blockIngotStorage, 1, 2), BlockRegistry.enderPortal);
		storage = new EnergyStorage(MAX_ENERGY);
	}

	@Override
	protected boolean canWork(int structureret)
	{
		return storage.getEnergyStored() >= ENERGY_USE;
	}

	@Override
	protected void work(int structureret)
	{
		storage.extractEnergy(ENERGY_USE, false);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from)
	{
		return from != ForgeDirection.UP;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate)
	{
		return storage.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from)
	{
		return storage.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from)
	{
		return MAX_ENERGY;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		storage.writeToNBT(nbt);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		storage.readFromNBT(nbt);
	}
	
}
