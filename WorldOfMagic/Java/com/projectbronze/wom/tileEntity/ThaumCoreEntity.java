package com.projectbronze.wom.tileEntity;

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
public class ThaumCoreEntity extends GenericCoreEntity implements IAspectContainer, IEssentiaTransport{
	
	private int lastside = 0;
	private ItemStack[] inventory;
    private String customName;
    public AspectList aspects = new AspectList();
	public int maxAspects = 64;
	private int resettime = 20;
	private int reqamount = 16;
	private Aspect reqaspect = Aspect.ELDRITCH;
	
	@Override
	public void updateEntity() {
		if(!worldObj.isRemote)
		{
			resettime--;
			for(int i  = 0; i < 6; ++i)
			{
				ForgeDirection facing = ForgeDirection.getOrientation(i);
				TileEntity te = ThaumcraftApiHelper.getConnectableTile(getWorldObj(), xCoord, yCoord, zCoord, facing);
				if(te != null)
				{
					IEssentiaTransport ic = (IEssentiaTransport) te;
		            if(!ic.canOutputTo(facing.getOpposite()))
		            {
		            	
		            }else
			            if(this.getAspects().getAmount(reqaspect)+1 < this.maxAspects && ic.getSuctionAmount(facing.getOpposite()) < getSuctionAmount(facing) && ic.takeEssentia(reqaspect, 1, facing.getOpposite()) == 1)
			            {
			            	this.addEssentia(reqaspect, 1, facing);
			            }
				}
			}
			if(resettime == 0)
			{
				if(doesContainerContainAmount(reqaspect, reqamount))
				{
					switch (checkStructure(worldObj, xCoord, yCoord, zCoord, ItemApi.getBlock("blockCosmeticSolid", 4)))
					{
					case(1): 
					{
						if(resettime == 0)
						{
							lastside = 1;
							takeFromContainer(reqaspect, reqamount);
							clearx(worldObj);	
							lastside = 1;
							placex(worldObj, BlockRegistry.thaumPortal);
						}
						break;
					}
					case(2):
					{
						if(resettime == 0)
						{
							lastside = 2;
							takeFromContainer(reqaspect, reqamount);
							clearz(worldObj);
							lastside = 2;
							placez(worldObj, BlockRegistry.thaumPortal);
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
				}
				}
				else
				{
					if(lastside == 1)
					{
						clearx(worldObj);
					}
					else if (lastside == 2)
					{
						clearz(worldObj);
					}
				}
			}
			if(resettime == 0)
			{
				resettime = 20;
			}
	}
}
	
	@Override
	public void writeToNBT(NBTTagCompound compound) {
		super.writeToNBT(compound);
		aspects.writeToNBT(compound);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
		aspects.readFromNBT(compound);
	}
	
		
		@Override
		public AspectList getAspects() {
			return aspects;
		}

		@Override
		public void setAspects(AspectList aspects) {
			this.aspects = aspects;
			
		}

		@Override
		public boolean doesContainerAccept(Aspect aspect) {
			return aspect == reqaspect;
		}

		@Override
		public int addToContainer(Aspect aspects, int amount) {
			if(amount > maxAspects)
				amount = maxAspects;
			if(this.aspects.getAmount(aspects) == 0)
			{
				this.aspects.add(aspects, amount);
				return amount;
			}else
			{
				if(this.aspects.getAmount(aspects)+amount < maxAspects)
				{
					this.aspects.merge(aspects, this.aspects.getAmount(aspects)+amount);
					return amount;
				}
			}
			return 0;
		}

		@Override
		public boolean takeFromContainer(Aspect tag, int amount) {
			if(this.aspects.getAmount(tag) > 0 && this.aspects.getAmount(tag)-amount >= 0)
			{
				this.aspects.reduce(tag, amount);
				return true;
			}
			return false;
		}

		@Override
		public boolean takeFromContainer(AspectList aspects) {
			if(this.doesContainerContain(aspects))
			{
				for(Aspect apt : aspects.getAspectsSortedAmount())
				{
					this.takeFromContainer(apt, aspects.getAmount(apt));
				}
			}
			return false;
		}

		@Override
		public boolean doesContainerContainAmount(Aspect tag, int amount) {
			return this.aspects.getAmount(tag) >= amount;
		}

		@Override
		public boolean doesContainerContain(AspectList aspects) {
			for(int o = 0; o < aspects.size(); o++)
			{
				Aspect apt = aspects.getAspectsSortedAmount()[o];
				if(this.aspects.getAmount(apt) == 0)
					return false;
			}
			return true;
		}

		@Override
		public int containerContains(Aspect tag) {
			return this.aspects.getAmount(tag);
		}
		
		
		
		@Override
		public boolean isConnectable(ForgeDirection face) {
			return true;
		}

		@Override
		public boolean canInputFrom(ForgeDirection face) {
			return true;
		}

		@Override
		public boolean canOutputTo(ForgeDirection face) {
			return false;
		}

		@Override
		public void setSuction(Aspect aspect, int amount) {
			
		}

		@Override
		public Aspect getSuctionType(ForgeDirection face) {
			return reqaspect;
		}

		@Override
		public int getSuctionAmount(ForgeDirection face) {
			return 64;
		}

		@Override
		public int takeEssentia(Aspect aspect, int amount, ForgeDirection face) {
			if(this.getSuctionType(face) == aspect)
				return this.addEssentia(aspect, amount, face);
			return 0;
		}

		@Override
		public int addEssentia(Aspect aspect, int amount, ForgeDirection face) {
			return this.addToContainer(aspect, amount);
		}

		@Override
		public Aspect getEssentiaType(ForgeDirection face) {
			return null;
		}
		
		

		@Override
		public int getEssentiaAmount(ForgeDirection face) {
			if(this.aspects.size() != 0)
				return this.aspects.getAmount(this.aspects.getAspectsSortedAmount()[0]);
			else
				return 0;
		}

		@Override
		public int getMinimumSuction() {
			return 0;
		}

		@Override
		public boolean renderExtendedTube() {
			return true;
		}

		
}
