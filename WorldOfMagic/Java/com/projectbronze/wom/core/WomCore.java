package com.projectbronze.wom.core;

import net.minecraft.creativetab.CreativeTabs;

import com.projectbronze.wom.creativetabs.WoMTab;
import com.projectbronze.wom.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;





@Mod(useMetadata = false, name = WomCore.name, modid = WomCore.modid, version = WomCore.version)
public class WomCore {
		public static final String name = "World of Magic Mod";
		public static final String modid = "wommod";
		public static final int globalversion = 1;
		public static final int minorversion = 0;
		public static final int gameversion = 1710;
		public static final int bugfixversion = 0;
		public static final String version = globalversion + "." + minorversion + "." + gameversion + "." + bugfixversion;
		
		public static CreativeTabs tabWoM = new WoMTab("tabWoM");
		
		@SidedProxy(clientSide = "com.projectbronze.wom.proxy.ClientProxy", serverSide="com.projectbronze.wom.proxy.ServerProxy")
		public static CommonProxy proxy;
		
		@Instance(modid)
		public static WomCore instance = new WomCore();
		
		@EventHandler
		public void preInit(FMLPreInitializationEvent e) {
		    proxy.preInit(e);
		}

		@EventHandler
		public void init(FMLInitializationEvent e) {
		    proxy.init(e);
		}

		@EventHandler
		public void postInit(FMLPostInitializationEvent e) {
		    proxy.postInit(e);
		}



}
