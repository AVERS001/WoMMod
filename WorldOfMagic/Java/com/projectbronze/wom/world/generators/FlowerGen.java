package com.projectbronze.wom.world.generators;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class FlowerGen extends WorldGenerator {

    private Block block;
    private Block target;

    public FlowerGen(Block block, Block target) {
        this.block = block;
        this.target = target;
    }

    public FlowerGen(Block block) {
        this(block, Blocks.air);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if (world.getBlock(x, y, z).isReplaceableOreGen(world, x, y, z, this.target) && block.canBlockStay(world, x, y - 1, z))
            world.setBlock(x, y, z, this.block, random.nextInt(3), 2);
        return true;
    }
}
