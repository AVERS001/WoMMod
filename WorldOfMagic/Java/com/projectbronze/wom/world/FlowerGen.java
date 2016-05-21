package com.projectbronze.wom.world;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class FlowerGen extends WorldGenerator {

    private Block block;
    private int blockmeta;
    private Block target;

    public FlowerGen(Block block, int meta, Block target) {
        this.block = block;
        this.blockmeta = meta;
        this.target = target;
    }

    public FlowerGen(Block block, Block target) {
        this(block, 0, target);
    }

    public FlowerGen(Block block) {
        this(block, Blocks.air);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        if (world.getBlock(x, y, z).isReplaceableOreGen(world, x, y, z, this.target) && world.getBlock(x, y - 1, z).equals(Blocks.sand))
            world.setBlock(x, y, z, this.block, this.blockmeta, 2);
        return true;
    }
}
