package com.projectbronze.wom.blocks;

import com.projectbronze.wom.registry.BlockRegistry;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockMetaBlock extends ItemBlockWithMetadata {
	public ItemBlockMetaBlock(Block block) {
		super(block, block);
	}

	@Override
	public String getUnlocalizedName(ItemStack item) {
		if(item.getItem().equals(BlockRegistry.decoBlock))
		{
			DecoBlock block = (DecoBlock) Block.getBlockFromItem(item.getItem());
			return block.names.get(item.getItemDamage());
		}
		return this.getUnlocalizedName() + "-" + item.getItemDamage();
	}

}
