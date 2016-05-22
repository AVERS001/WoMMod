package com.projectbronze.wom.world;

import java.util.Random;

import com.projectbronze.wom.registry.BlockRegistry;
import com.projectbronze.wom.world.generators.FlowerGen;
import com.projectbronze.wom.world.generators.IslandGen;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import cpw.mods.fml.common.IWorldGenerator;


public class WomWorldGenerator implements IWorldGenerator {
	private WorldGenerator flowergen;
	private IslandGen islandgen;
	private WorldGenMinable irongen, goldgen, diamondgen, redstonegen, emeraldgen, lapisgen;
	
	public WomWorldGenerator() {
		flowergen = new FlowerGen(BlockRegistry.desertFlower);
		islandgen = new IslandGen();
		irongen = new WorldGenMinable(Blocks.iron_ore, 10);
		goldgen = new WorldGenMinable(Blocks.gold_ore, 10);
		diamondgen = new WorldGenMinable(Blocks.diamond_ore, 7);
		redstonegen = new WorldGenMinable(Blocks.redstone_ore, 20);
		lapisgen = new WorldGenMinable(Blocks.lapis_ore, 30);
	}
	
	@Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {   
    	if(world.provider.dimensionId == 0)
    	{
			runGenerator(flowergen, world, random, chunkX, chunkZ, 100, 60, 100);
			if (random.nextInt(800) == 376)
			{
                 islandgen.generateIsland(world, random, chunkX * 16, chunkZ * 16);
                 runGenerator(irongen, world, random, chunkX, chunkZ, 40, 100, 256);
                 runGenerator(goldgen, world, random, chunkX, chunkZ, 10, 100, 256);
                 runGenerator(diamondgen, world, random, chunkX, chunkZ, 5, 100, 256);
                 runGenerator(redstonegen, world, random, chunkX, chunkZ, 20, 100, 256);
                 runGenerator(lapisgen, world, random, chunkX, chunkZ, 20, 100, 256);
			}
    	}
    	
    }
	

	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
	    if (minHeight < 0 || maxHeight > world.getActualHeight() || minHeight > maxHeight)
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