package com.projectbronze.wom.registry;
import static com.projectbronze.wom.nei.PortalRecipe.createRecipe;
import WayofTime.alchemicalWizardry.ModItems;
import com.gt22.gt22core.utils.ResourceLocGenerator;
import com.projectbronze.wom.core.Core;
import crazypants.enderio.EnderIO;
import ec3.common.block.BlocksCore;
import ec3.common.item.ItemsCore;
import net.minecraft.item.ItemStack;
import thaumcraft.api.ItemApi;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.config.ConfigBlocks;
import thaumcraft.common.config.ConfigItems;
import thaumcraft.common.items.ItemEssence;
import twilightforest.block.TFBlocks;
import vazkii.botania.common.block.ModBlocks;
public class PortalRecipeRegistry
{
	public static void init()
	{
		createRecipe(BlockRegistry.thaumCore, ItemApi.getBlock("blockCosmeticSolid", 4), getAlienisPhial());
		createRecipe(BlockRegistry.botanCore, new ItemStack(ModBlocks.storage, 1, 1), new ItemStack(ModBlocks.pool));
		createRecipe(BlockRegistry.bloodyCore, new ItemStack(WayofTime.alchemicalWizardry.ModBlocks.blockStabilityGlyph), new ItemStack(ModItems.masterBloodOrb));
		createRecipe(BlockRegistry.auraCore, new ItemStack(BlockRegistry.angelSteelBlock), (ItemStack)null);
		createRecipe(BlockRegistry.essentialCore, new ItemStack(BlocksCore.demonicPlating), new ItemStack(ItemsCore.genericItem, 1, 16));
		createRecipe(BlockRegistry.twilightCore, new ItemStack(TFBlocks.underBrick), new ItemStack(TFBlocks.sapling, 1, 1));
		createRecipe(BlockRegistry.enderCore, new ItemStack(EnderIO.blockIngotStorage, 1, 2), new ItemStack(EnderIO.blockCapBank, 1, 3));
	}
	
	private static ItemStack getAlienisPhial()
	{
		ItemStack ret = new ItemStack(ConfigItems.itemEssence, 1, 1);
		((ItemEssence) ConfigItems.itemEssence).setAspects(ret, new AspectList().add(Aspect.ELDRITCH, 8));
		return ret;
	}
}
