package com.projectbronze.wom.items;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPotion;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import vazkii.botania.common.core.helper.ItemNBTHelper;
import baubles.api.BaubleType;
import baubles.api.IBauble;

import com.projectbronze.wom.core.WomCore;
import com.projectbronze.wom.gui.GuiHandler;
import com.projectbronze.wom.registry.ItemRegistry;

public class PotionBelt extends Item implements IBauble {

	private static final String TAG_ITEMS = "BELTInvItems";
	private static final String TAG_SLOT = "BELTSlot";
	
	public PotionBelt(String unlocName) {
		setCreativeTab(WomCore.tabWoM);
		setMaxStackSize(1);
		setTextureName(WomCore.modid + ":" + unlocName);
		setUnlocalizedName(unlocName);
	}
	
	
	@Override
	public BaubleType getBaubleType(ItemStack itemstack) {
		return BaubleType.BELT;
	}

	@Override
	public void onWornTick(ItemStack itemstack, EntityLivingBase player) {
		if(itemstack.getItem().equals(ItemRegistry.potionBelt))
		{
			ItemStack[] potions = loadStacks(itemstack);
			for(int i = 0; i < potions.length; i++)
			{
				if(potions[i] != null)
				{
					Potion[] effecttypes = Potion.potionTypes;
					ItemPotion potion = (ItemPotion) potions[i].getItem();
					if(potion.hasEffect(potions[i]))
					{
						List <PotionEffect> effects = (List<PotionEffect>) potion.getEffects(potions[i].getItemDamage());
						effects.add(new PotionEffect(24, 202, 1));
						effects.add(new PotionEffect(25, 202, 1));
						for(int j = 0; j < effects.size(); j++)
						{
							if(!effecttypes[effects.get(j).getPotionID()].isInstant())
							{
							//Creating the same effect, but with 10 second and 2 ticks duration (Just enough for good night vision)
							PotionEffect effect = new PotionEffect(effects.get(j).getPotionID(), 202, effects.get(j).getAmplifier());
							player.addPotionEffect(effect);
							}
						}
					}
				}
			}
		}
		
	}

	
	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if(!entityItem.worldObj.getBlock((int)Math.round(entityItem.posX), (int)Math.round(entityItem.posY) -1 , (int)Math.round(entityItem.posZ)).equals(Blocks.air))
		{
			ItemStack[] inventory = loadStacks(entityItem.getEntityItem());
			if(inventory != null && inventory[0] != null)
			{
				ItemNBTHelper.injectNBT(entityItem.getEntityItem(), new NBTTagCompound());
				for(int i = 0; i < 500; i++)
				{
					entityItem.worldObj.spawnParticle("spell", entityItem.posX, entityItem.posY, entityItem.posZ, 0D, 0D + i, 0D);
				}
				entityItem.worldObj.playAuxSFX(2002, (int)Math.round(entityItem.posX), (int)Math.round(entityItem.posY), (int)Math.round(entityItem.posZ), 1);

			}
		}
		return false;
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player) {
		player.openGui(WomCore.instance, GuiHandler.PotionBeltID, world, 0, 0, 0);
		return stack;
	}
	
	@Override
	public void onEquipped(ItemStack itemstack, EntityLivingBase player) {
		
	}

	@Override
	public void onUnequipped(ItemStack itemstack, EntityLivingBase player) {
		
	}

	@Override
	public boolean canEquip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}

	@Override
	public boolean canUnequip(ItemStack itemstack, EntityLivingBase player) {
		return true;
	}
	
	public static ItemStack[] loadStacks(ItemStack stack) {
		NBTTagList nbtlist = ItemNBTHelper.getList(stack, TAG_ITEMS, 10, false);
		ItemStack[] inventorySlots = new ItemStack[36];
		for(int i = 0; i < nbtlist.tagCount(); ++i) {
			NBTTagCompound nbt = nbtlist.getCompoundTagAt(i);
			byte b = nbt.getByte(TAG_SLOT);
			if(b >= 0 && b < inventorySlots.length)
				inventorySlots[b] = ItemStack.loadItemStackFromNBT(nbt);
		}

		return inventorySlots;
	}

	public static void setStacks(ItemStack stack, ItemStack[] inventorySlots) {
		NBTTagList nbtlist = new NBTTagList();
		for(int i = 0; i < inventorySlots.length; i++)
			if(inventorySlots[i] != null) {
				NBTTagCompound nbt = new NBTTagCompound();
				nbt.setByte(TAG_SLOT, (byte)i);
				inventorySlots[i].writeToNBT(nbt);
				nbtlist.appendTag(nbt);
			}

		ItemNBTHelper.setList(stack, TAG_ITEMS, nbtlist);
	}
	
	

}
