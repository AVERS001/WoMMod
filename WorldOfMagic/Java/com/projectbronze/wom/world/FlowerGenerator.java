package com.projectbronze.wom.world;

import java.util.Random;

import com.projectbronze.wom.registry.BlockRegistry;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;


public class FlowerGenerator implements IWorldGenerator {
	private WorldGenerator flowergen;
	
	public FlowerGenerator() {
		flowergen = new FlowerGen(BlockRegistry.desertFlower);
	}
	
	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {   
    	if(world.provider.dimensionId == 0)
    	{
			runGenerator(flowergen, world, random, chunkX, chunkZ, 100, 60, 100);
    	}
    	
    }
	

	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
	    if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

	    int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        generator.generate(world, rand, x, y, z);
	    }
	}
}