package com.projectbronze.wom.tileEntity;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import thaumcraft.api.TileThaumcraft;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

public class ThaumicFurnaceEntity
  extends TileThaumcraft
  implements ISidedInventory
{
  private static final int[] slots_bottom = { 1 };
  private static final int[] slots_top = new int[0];
  private static final int[] slots_sides = { 0 };
  public AspectList aspects = new AspectList();
  public int vis;
  private int maxVis = 50;
  public int smeltTime = 100;
  int bellows = -1;
  boolean speedBoost = false;
  private ItemStack[] furnaceItemStacks = new ItemStack[2];
  public int furnaceBurnTime;
  public int currentItemBurnTime;
  public int furnaceCookTime;
  private String customName;
  
  
  public int getSizeInventory()
  {
    return this.furnaceItemStacks.length;
  }
  
  public ItemStack getStackInSlot(int par1)
  {
    return this.furnaceItemStacks[par1];
  }
  
  public ItemStack decrStackSize(int par1, int par2)
  {
    if (this.furnaceItemStacks[par1] != null)
    {
      if (this.furnaceItemStacks[par1].stackSize <= par2)
      {
        ItemStack itemstack = this.furnaceItemStacks[par1];
        this.furnaceItemStacks[par1] = null;
        return itemstack;
      }
      ItemStack itemstack = this.furnaceItemStacks[par1].splitStack(par2);
      if (this.furnaceItemStacks[par1].stackSize == 0) {
        this.furnaceItemStacks[par1] = null;
      }
      return itemstack;
    }
    return null;
  }
  
  public ItemStack getStackInSlotOnClosing(int par1)
  {
    if (this.furnaceItemStacks[par1] != null)
    {
      ItemStack itemstack = this.furnaceItemStacks[par1];
      this.furnaceItemStacks[par1] = null;
      return itemstack;
    }
    return null;
  }
  
  public void setInventorySlotContents(int par1, ItemStack par2ItemStack)
  {
    this.furnaceItemStacks[par1] = par2ItemStack;
    if ((par2ItemStack != null) && (par2ItemStack.stackSize > getInventoryStackLimit())) {
      par2ItemStack.stackSize = getInventoryStackLimit();
    }
  }
  
  public String getInventoryName()
  {
    return hasCustomInventoryName() ? this.customName : "container.alchemyfurnace";
  }
  
  public boolean hasCustomInventoryName()
  {
    return (this.customName != null) && (this.customName.length() > 0);
  }
  
  public void setGuiDisplayName(String par1Str)
  {
    this.customName = par1Str;
  }
  
  public void readCustomNBT(NBTTagCompound nbttagcompound)
  {
    this.furnaceBurnTime = nbttagcompound.getShort("BurnTime");
    this.vis = nbttagcompound.getShort("Vis");
  }
  
  public void writeCustomNBT(NBTTagCompound nbttagcompound)
  {
    nbttagcompound.setShort("BurnTime", (short)this.furnaceBurnTime);
    nbttagcompound.setShort("Vis", (short)this.vis);
  }
  
  public void readFromNBT(NBTTagCompound nbtCompound)
  {
    super.readFromNBT(nbtCompound);
    NBTTagList nbttaglist = nbtCompound.getTagList("Items", 10);
    this.furnaceItemStacks = new ItemStack[getSizeInventory()];
    for (int i = 0; i < nbttaglist.tagCount(); i++)
    {
      NBTTagCompound nbttagcompound1 = nbttaglist.getCompoundTagAt(i);
      byte b0 = nbttagcompound1.getByte("Slot");
      if ((b0 >= 0) && (b0 < this.furnaceItemStacks.length)) {
        this.furnaceItemStacks[b0] = ItemStack.loadItemStackFromNBT(nbttagcompound1);
      }
    }
    this.speedBoost = nbtCompound.getBoolean("speedBoost");
    this.furnaceCookTime = nbtCompound.getShort("CookTime");
    this.currentItemBurnTime = TileEntityFurnace.getItemBurnTime(this.furnaceItemStacks[1]);
    if (nbtCompound.hasKey("CustomName")) {
      this.customName = nbtCompound.getString("CustomName");
    }
    this.aspects.readFromNBT(nbtCompound);
    this.vis = this.aspects.visSize();
  }
  
  public void writeToNBT(NBTTagCompound nbtCompound)
  {
    super.writeToNBT(nbtCompound);
    nbtCompound.setBoolean("speedBoost", this.speedBoost);
    nbtCompound.setShort("CookTime", (short)this.furnaceCookTime);
    NBTTagList nbttaglist = new NBTTagList();
    for (int i = 0; i < this.furnaceItemStacks.length; i++) {
      if (this.furnaceItemStacks[i] != null)
      {
        NBTTagCompound nbttagcompound1 = new NBTTagCompound();
        nbttagcompound1.setByte("Slot", (byte)i);
        this.furnaceItemStacks[i].writeToNBT(nbttagcompound1);
        nbttaglist.appendTag(nbttagcompound1);
      }
    }
    nbtCompound.setTag("Items", nbttaglist);
    if (hasCustomInventoryName()) {
      nbtCompound.setString("CustomName", this.customName);
    }
    this.aspects.writeToNBT(nbtCompound);
  }
  
  public int getInventoryStackLimit()
  {
    return 64;
  }
  
  @SideOnly(Side.CLIENT)
  public int getCookProgressScaled(int par1)
  {
    if (this.smeltTime <= 0) {
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
    if (this.currentItemBurnTime == 0) {
      this.currentItemBurnTime = 200;
    }
    return this.furnaceBurnTime * par1 / this.currentItemBurnTime;
  }
  
  public boolean isBurning()
  {
    return this.furnaceBurnTime > 0;
  }
  
  public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt)
  {
    super.onDataPacket(net, pkt);
    if (this.worldObj != null) {
      this.worldObj.updateLightByType(EnumSkyBlock.Block, this.xCoord, this.yCoord, this.zCoord);
    }
  }
  
  public boolean canUpdate()
  {
    return true;
  }
  
  int count = 0;
  
 /*public void updateEntity()
  {
    boolean flag = this.furnaceBurnTime > 0;
    boolean flag1 = false;
    this.count += 1;
    if (this.furnaceBurnTime > 0) {
      this.furnaceBurnTime -= 1;
    }
    if (!this.worldObj.isRemote)
    {
      if (this.bellows < 0) {
        getBellows();
      }
      if ((this.count % (this.speedBoost ? 20 : 40) == 0) && (this.aspects.size() > 0))
      {
        AspectList exlude = new AspectList();
        int deep = 0;
        TileEntity tile = null;
        while (deep < 5)
        {
          deep++;
          tile = this.worldObj.getTileEntity(this.xCoord, this.yCoord + deep, this.zCoord);
          if (!(tile instanceof TileAlembic)) {
            break;
          }
          TileAlembic alembic = (TileAlembic)tile;
          if ((alembic.aspect != null) && (alembic.amount < alembic.maxAmount) && (this.aspects.getAmount(alembic.aspect) > 0))
          {
            takeFromContainer(alembic.aspect, 1);
            alembic.addToContainer(alembic.aspect, 1);
            exlude.merge(alembic.aspect, 1);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
            this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord + deep, this.zCoord);
          }
          tile = null;
        }
        deep = 0;
        while (deep < 5)
        {
          deep++;
          tile = this.worldObj.getTileEntity(this.xCoord, this.yCoord + deep, this.zCoord);
          if (!(tile instanceof TileAlembic)) {
            break;
          }
          TileAlembic alembic = (TileAlembic)tile;
          if ((alembic.aspect == null) || (alembic.amount == 0))
          {
            Aspect as = null;
            if (alembic.aspectFilter == null) {
              as = takeRandomAspect(exlude);
            } else if (takeFromContainer(alembic.aspectFilter, 1)) {
              as = alembic.aspectFilter;
            }
            if (as != null)
            {
              alembic.addToContainer(as, 1);
              this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
              this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord + deep, this.zCoord);
              break;
            }
          }
        }
      }
      if ((this.furnaceBurnTime == 0) && (canSmelt()))
      {
        this.currentItemBurnTime = (this.furnaceBurnTime = TileEntityFurnace.getItemBurnTime(this.furnaceItemStacks[1]));
        if (this.furnaceBurnTime > 0)
        {
          flag1 = true;
          this.speedBoost = false;
          if (this.furnaceItemStacks[1] != null)
          {
            if (this.furnaceItemStacks[1].isItemEqual(new ItemStack(ConfigItems.itemResource, 1, 0))) {
              this.speedBoost = true;
            }
            this.furnaceItemStacks[1].stackSize -= 1;
            if (this.furnaceItemStacks[1].stackSize == 0) {
              this.furnaceItemStacks[1] = this.furnaceItemStacks[1].getItem().getContainerItem(this.furnaceItemStacks[1]);
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
      if (flag != this.furnaceBurnTime > 0)
      {
        flag1 = true;
        this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
      }
    }
    if (flag1) {
      markDirty();
    }
  }
  
  private boolean canSmelt()
  {
    if (this.furnaceItemStacks[0] == null) {
      return false;
    }
    AspectList al = ThaumcraftCraftingManager.getObjectTags(this.furnaceItemStacks[0]);
    al = ThaumcraftCraftingManager.getBonusTags(this.furnaceItemStacks[0], al);
    if ((al == null) || (al.size() == 0)) {
      return false;
    }
    int vs = al.visSize();
    if (vs > this.maxVis - this.vis) {
      return false;
    }
    this.smeltTime = ((int)(vs * 10 * (1.0F - 0.125F * this.bellows)));
    return true;
  }
  
  public void getBellows()
  {
    this.bellows = TileBellows.getBellows(this.worldObj, this.xCoord, this.yCoord, this.zCoord, ForgeDirection.VALID_DIRECTIONS);
  }
  
  public void smeltItem()
  {
    if (canSmelt())
    {
      AspectList al = ThaumcraftCraftingManager.getObjectTags(this.furnaceItemStacks[0]);
      al = ThaumcraftCraftingManager.getBonusTags(this.furnaceItemStacks[0], al);
      for (Aspect a : al.getAspects()) {
        this.aspects.add(a, al.getAmount(a));
      }
      this.vis = this.aspects.visSize();
      
      this.furnaceItemStacks[0].stackSize -= 1;
      if (this.furnaceItemStacks[0].stackSize <= 0) {
        this.furnaceItemStacks[0] = null;
      }
    }
  }*/
  
  public static boolean isItemFuel(ItemStack par0ItemStack)
  {
    return TileEntityFurnace.getItemBurnTime(par0ItemStack) > 0;
  }
  
  public boolean isUseableByPlayer(EntityPlayer par1EntityPlayer)
  {
    return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) == this;
  }
  
  public void openInventory() {}
  
  public void closeInventory() {}
  
  public boolean isItemValidForSlot(int par1, ItemStack par2ItemStack)
  {
    /*if (par1 == 0)
    {
      AspectList al = ThaumcraftCraftingManager.getObjectTags(par2ItemStack);
      al = ThaumcraftCraftingManager.getBonusTags(par2ItemStack, al);
      if ((al != null) && (al.size() > 0)) {
        return true;
      }
    }*/
    return par1 == 1 ? isItemFuel(par2ItemStack) : false;
  }
  
  public int[] getAccessibleSlotsFromSide(int par1)
  {
    return par1 == 1 ? slots_top : par1 == 0 ? slots_bottom : slots_sides;
  }
  
  public boolean canInsertItem(int par1, ItemStack par2ItemStack, int par3)
  {
    return par3 == 1 ? false : isItemValidForSlot(par1, par2ItemStack);
  }
  
  public boolean canExtractItem(int par1, ItemStack par2ItemStack, int par3)
  {
    return (par3 != 0) || (par1 != 1) || (par2ItemStack.getItem() == Items.bucket);
  }
  
  public Aspect takeRandomAspect(AspectList exlude)
  {
    if (this.aspects.size() > 0)
    {
      AspectList temp = this.aspects.copy();
      if (exlude.size() > 0) {
        for (Aspect a : exlude.getAspects()) {
          temp.remove(a);
        }
      }
      if (temp.size() > 0)
      {
        Aspect tag = temp.getAspects()[this.worldObj.rand.nextInt(temp.getAspects().length)];
        this.aspects.remove(tag, 1);
        this.vis -= 1;
        return tag;
      }
    }
    return null;
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
}
