package com.projectbronze.wom.gui.gui;

import net.minecraft.entity.player.EntityPlayer;
import DummyCore.Client.GuiCommon;

import com.projectbronze.wom.gui.container.PotionBeltContainer;

public class PotionBeltGUI extends GuiCommon
{
	public PotionBeltGUI(EntityPlayer player)
	{
		super(new PotionBeltContainer(player));

	}

}
