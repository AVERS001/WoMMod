package com.projectbronze.wom.registry;

import java.awt.Color;
import java.util.Arrays;
import java.util.List;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import amerifrance.guideapi.api.GuideRegistry;
import amerifrance.guideapi.api.base.Book;
import amerifrance.guideapi.api.util.BookBuilder;
import amerifrance.guideapi.categories.CategoryResourceLocation;
import amerifrance.guideapi.entries.EntryItemStack;
import amerifrance.guideapi.pages.PageText;
import amerifrance.guideapi.pages.PageTextImage;
import com.projectbronze.wom.core.Core;

public class BookRegistry
{
	public static Book guidebook;
	public static List categories;

	//@formatter:off
	public static void init()
	{
		initCat();
		guidebook = new BookBuilder()
		.setAuthor("Project bronze")
		.setBookColor(new Color(255, 0, 0))
		.setItemTexture(Core.modid + ":book")
		.setSpawnWithBook(true)
		.setUnlocBookTitle("guidebookSmallTitle")
		.setUnlocDisplayName("guidebookName")
		.setUnlocWelcomeMessage("guidebookLargeTitle")
		.setCategories(categories)
		.build();
		GuideRegistry.registerBook(guidebook);
	}

	
	private static void initCat()
	{
		categories = Arrays.asList
		(
			new CategoryResourceLocation
			(
				Arrays.asList
				(
						new EntryItemStack
						(
								Arrays.asList(new PageText("test")),
								"testEnt",
								new ItemStack(BlockRegistry.angelSteelBlock)
						)
				),
				"CATthaum",
				generateBookRL("Thaumcraft")
			),
			new CategoryResourceLocation
			(
					Arrays.asList
					(
							
					),
					"CATBotan",
					generateBookRL("Botania")
			),
			new CategoryResourceLocation
			(
					Arrays.asList
					(
							
					),
					"CATBlood",
					generateBookRL("Blood Magic")
			),
			new CategoryResourceLocation
			(
					Arrays.asList
					(
							
					),
					"CATEnder",
					generateBookRL("EnderIO")
			),
			new CategoryResourceLocation
			(
					Arrays.asList
					(
							
					),
					"CATEssential",
					generateBookRL("Essential")
			),
			new CategoryResourceLocation
			(
					Arrays.asList
					(
							
					),
					"CATTinker",
					generateBookRL("Tinker")
			),
			new CategoryResourceLocation
			(
					Arrays.asList
					(
							
					),
					"CATTwilight",
					generateBookRL("Twilight")
			),
			new CategoryResourceLocation
			(
					Arrays.asList
					(
							
					),
					"CATWitc",
					generateBookRL("Witchery")
			),
			new CategoryResourceLocation
			(
					Arrays.asList
					(
						new EntryItemStack
						(
								Arrays.asList
								(
									new PageTextImage("Structure of floral portal", generateBookRL("BotanStructure"), false)
								),
								"BotanPortal",
								new ItemStack(BlockRegistry.botanCore)
						)
					),
					"CATWoM",
					generateBookRL("WomMod")
			)
		);
	}
	//@formatter:on
	private static ResourceLocation generateBookRL(String texturename)
	{
		return new ResourceLocation(Core.modid, "textures/book/" + texturename + ".png");
	}
}
