package com.projectbronze.wom.tileEntity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.EnumSkyBlock;
import thaumcraft.api.ItemApi;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.api.aspects.IAspectContainer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ThaumicFurnaceEntity extends TileThaumcraft implements ISidedInventory, IAspectContainer
{
	private static final int[] slots_bottom =
	{ 1 };
	private static final int[] slots_top =
	{};
	private static final int[] slots_sides =
	{ 0 };
	public AspectList aspects = new AspectList();
	public int vis = 0;
	private int maxVis = 100;
	public int smeltTime = 100;
	int bellows = -1;
	boolean speedBoost = false;
	private ItemStack[] inventory = new ItemStack[2];
	public int furnaceBurnTime;
	public int currentItemBurnTime;
	public int furnaceCookTime;
	private String customName;

	@Override
	public int getSizeInventory()
	{
		return this.inventory.length;
	}

	@Override
	public ItemStack getStackInSlot(int par1)
	{
		return this.inventory[par1];
	}

	@Override
	public ItemStack decrStackSize(int par1, int par2)
	{
		if (this.inventory[par1] != null)
		{
			if (this.inventory[par1].stackSize <= par2)
			{
				ItemStack itemstack = this.inventory[par1];
				this.inventory[par1] = null;
				return itemstack;
			}
			ItemStack itemstack = this.inventory[par1].splitStack(par2);
			if (this.inventory[par1].stackSize == 0)
			{
				this.inventory[par1] = null;
			}
			return itemstack;
		}
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int par1)
	{
		if (this.inventory[par1] != null)
		{
			ItemStack itemstack = this.inventory[par1];
			this.inventory[par1] = null;
			return itemstack;
		}
		return null;
	}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
	{
		this.inventory[par1] = par2ItemStack;
		if ((par2ItemStack != null) && (par2ItemStack.stackSize > getInventoryStackLimit()))
		{
			par2ItemStack.stackSize = getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName()
	{
		return hasCustomInventoryName() ? this.customName : "container.alchemyfurnace";
	}

	@Override
	public boolean hasCustomInventoryName()
	{
		return (this.customName != null) && (this.customName.length() > 0);
	}

	public void setGuiDisplayName(String par1Str)
	{
		this.customName = par1Str;
	}

	@Override
	public void readCustomNBT(NBTTagCompound nbt)
	{
		this.furnaceBurnTime = nbt.getShort("BurnTime");
		this.vis = nbt.getShort("Vis");
		aspects.readFromNBT(nbt, "aspects");
	}

	@Override
	public void writeCustomNBT(NBTTagCompound nbt)
	{
		nbt.setShort("BurnTime", (short) this.furnaceBurnTime);
		nbt.setShort("Vis", (short) this.vis);
		aspects.writeToNBT(nbt, "aspects");
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt)
	{
		super.readFromNBT(nbt);
		NBTTagList nbttaglist = nbt.getTagList("Items", 10);
		this.inventory = new ItemStack[getSizeInventory()];
		for (int i = 0; i < nbttaglist.tagCount(); i++)
		{
			NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
			byte b0 = nbttagcompound1.getByte("Slot");
			if ((b0 >= 0) && (b0 < this.inventory.length))
			{
				this.inventory[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
			}
		}
		this.speedBoost = nbt.getBoolean("speedBoost");
		this.furnaceCookTime = nbt.getShort("CookTime");
		this.currentItemBurnTime = TileEntityFurnace.getItemBurnTime(this.inventory[1]);
		if (nbt.hasKey("CustomName"))
		{
			this.customName = nbt.getString("CustomName");
		}
		this.aspects.readFromNBT(nbt);
		this.vis = this.aspects.visSize();
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt)
	{
		super.writeToNBT(nbt);
		nbt.setBoolean("speedBoost", this.speedBoost);
		nbt.setShort("CookTime", (short) this.furnaceCookTime);
		NBTTagList nbttaglist = new NBTTagList();
		for (int i = 0; i < this.inventory.length; i++)
		{
			if (this.inventory[i] != null)
			{
				NBTTagCompound nbttagcompound1 = new NBTTagCompound();
				nbttagcompound1.setByte("Slot", (byte) i);
				this.inventory[i].writeToNBT(nbttagcompound1);
				nbttaglist.appendTag(nbttagcompound1);
			}
		}
		nbt.setTag("Items", nbttaglist);
		if (hasCustomInventoryName())
		{
			nbt.setString("CustomName", this.customName);
		}
		this.aspects.writeToNBT(nbt);
	}

	public int getInventoryStackLimit()
	{
		return 64;
	}

	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1)
	{
		if (this.smeltTime <= 0)
		{
			this.smeltTime = 1;
		}
		return this.furnaceCookTime * par1 / this.smeltTime;
	}

	@SideOnly(Side.CLIENT)
	public int getContentsScaled(int par1)
	{
		return this.vis * par1 / this.maxVis;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1)
	{
		if (this.currentItemBurnTime == 0)
		{
			this.currentItemBurnTime = 200;
		}
		return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
	}

	public boolean isBurning()
	{
		return this.furnaceBurnTime > 0;
	}

	@Override
	public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
	{
		super.onDataPacket(net, pkt);
		if (this.worldObj != null)
		{
			this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
		}
	}

	int count = 0;

	public void updateEntity()
	{
		boolean burning = isBurning();
		boolean flag1 = false;
		this.count += 1;
		if (this.furnaceBurnTime > 0)
		{
			this.furnaceBurnTime -= 1;
		}
		if (!this.worldObj.isRemote)
		{
			if ((this.furnaceBurnTime == 0) && (canSmelt()))
			{
				this.currentItemBurnTime = (this.furnaceBurnTime = TileEntityFurnace.getItemBurnTime(this.inventory[1]));
				if (this.furnaceBurnTime > 0)
				{
					flag1 = true;
					this.speedBoost = false;
					if (this.inventory[1] != null)
					{
						if (this.inventory[1].isItemEqual(ItemApi.getItem("itemResource", 0)))
						{
							this.speedBoost = true;
						}
						this.inventory[1].stackSize -= 1;
						if (this.inventory[1].stackSize == 0)
						{
							this.inventory[1] = this.inventory[1].getItem().getContainerItem(this.inventory[1]);
						}
					}
				}
			}
			if ((isBurning()) && (canSmelt()))
			{
				this.furnaceCookTime += 1;
				if (this.furnaceCookTime >= this.smeltTime)
				{
					this.furnaceCookTime = 0;
					smeltItem();
					flag1 = true;
				}
			}
			else
			{
				this.furnaceCookTime = 0;
			}
			if (burning != isBurning())
			{
				flag1 = true;
				this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
			}
		}
		if (flag1)
		{
			markDirty();
		}
	}

	private boolean canSmelt()
	{
		if (this.inventory[0] == null)
		{
			return false;
		}
		AspectList al = new AspectList(getStackInSlot(0));
		if ((al == null) || (al.size() == 0))
		{
			return false;
		}
		int vs = al.visSize();
		if (vs > this.maxVis - this.vis)
		{
			return false;
		}
		this.smeltTime = ((int) (vs * 10 * (1.0F - 0.125F * this.bellows)));
		return true;
	}

	public void smeltItem()
	{
		if (canSmelt())
		{
			AspectList al = new AspectList(getStackInSlot(0));
			for (Aspect a : al.getAspects())
			{
				this.aspects.add(a, al.getAmount(a));
			}
			this.vis = this.aspects.visSize();

			this.inventory[0].stackSize -= 1;
			if (this.inventory[0].stackSize <= 0)
			{
				this.inventory[0] = null;
			}
		}
	}

	public static boolean isItemFuel(ItemStack par0ItemStack)
	{
		return TileEntityFurnace.getItemBurnTime(par0ItemStack) > 0;
	}

	public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
	{
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this;
	}

	public void openInventory()
	{
	}

	public void closeInventory()
	{
	}

	public boolean isItemValidForSlot(int slot, ItemStack stack)
	{

		if (slot == 0)
		{
			AspectList al = new AspectList(stack);
			if ((al != null) && (al.size() > 0))
			{
				return true;
			}
		}

		return slot == 1 ? isItemFuel(stack) : false;
	}

	public int[] getAccessibleSlotsFromSide(int side)
	{
		return side == 1 ? slots_top : side == 0 ? slots_bottom : slots_sides;
	}

	public boolean canInsertItem(int slot, ItemStack stack, int side)
	{
		return side == 1 ? false : isItemValidForSlot(slot, stack);
	}

	public boolean canExtractItem(int slot, ItemStack stack, int side)
	{
		return (side != 0) || (slot != 1) || (stack.getItem() == Items.bucket);
	}

	public boolean takeFromContainer(Aspect tag, int amount)
	{
		if ((this.aspects != null) && (this.aspects.getAmount(tag) >= amount))
		{
			this.aspects.remove(tag, amount);
			this.vis -= amount;
			return true;
		}
		return false;
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
	public boolean doesContainerAccept(Aspect tag)
	{
		return aspects.getAmount(tag) != 0;
	}

	@Override
	public int addToContainer(Aspect tag, int amount)
	{
		int ret = 0;
		if (vis + amount > maxVis)
		{
			ret = amount - (maxVis - vis);
			amount = maxVis - vis;
		}
		aspects.add(tag, amount);
		return ret;
	}

	@Override
	public boolean takeFromContainer(AspectList ot)
	{
		if (!doesContainerContain(ot))
		{
			return false;
		}
		for (Aspect a : ot.getAspects())
		{
			aspects.reduce(a, ot.getAmount(a));
		}
		return true;
	}

	@Override
	public boolean doesContainerContainAmount(Aspect tag, int amount)
	{
		return aspects.getAmount(tag) >= amount;
	}

	@Override
	public boolean doesContainerContain(AspectList ot)
	{
		for (Aspect a : ot.getAspects())
		{
			if (!doesContainerContainAmount(a, ot.getAmount(a)))
			{
				return false;
			}
		}
		return true;
	}

	@Override
	public int containerContains(Aspect tag)
	{
		return aspects.getAmount(tag);
	}

}
