package com.visualtickcursor;

import java.awt.Color;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("visualtickcursor")
public interface VisualTickCursorConfig extends Config
{
	@ConfigItem(
			keyName = "color1",
			name = "Color 1",
			description = "First color in the cycle",
			position = 3
	)
	default Color color1()
	{
		return Color.RED;
	}

	@ConfigItem(
			keyName = "color2",
			name = "Color 2",
			description = "Second color in the cycle",
			position = 4
	)
	default Color color2()
	{
		return Color.ORANGE;
	}

	@ConfigItem(
			keyName = "color3",
			name = "Color 3",
			description = "Third color in the cycle",
			position = 5
	)
	default Color color3()
	{
		return Color.YELLOW;
	}

	@ConfigItem(
			keyName = "color4",
			name = "Color 4",
			description = "Fourth color in the cycle",
			position = 6
	)
	default Color color4()
	{
		return Color.GREEN;
	}

	@ConfigItem(
			keyName = "color5",
			name = "Color 5",
			description = "Fifth color in the cycle",
			position = 7
	)
	default Color color5()
	{
		return Color.CYAN;
	}

	@ConfigItem(
			keyName = "color6",
			name = "Color 6",
			description = "Sixth color in the cycle",
			position = 8
	)
	default Color color6()
	{
		return Color.BLUE;
	}

	@ConfigItem(
			keyName = "color7",
			name = "Color 7",
			description = "Seventh color in the cycle",
			position = 9
	)
	default Color color7()
	{
		return Color.MAGENTA;
	}

	@ConfigItem(
			keyName = "color8",
			name = "Color 8",
			description = "Eighth color in the cycle",
			position = 10
	)
	default Color color8()
	{
		return Color.PINK;
	}

	@ConfigItem(
			keyName = "color9",
			name = "Color 9",
			description = "Ninth color in the cycle",
			position = 11
	)
	default Color color9()
	{
		return Color.WHITE;
	}

	@ConfigItem(
			keyName = "color10",
			name = "Color 10",
			description = "Tenth color in the cycle",
			position = 12

	)
	default Color color10()
	{
		return Color.GRAY;
	}

	@Range(
			min = 1,
			max = 20
	)
	@ConfigItem(
			keyName = "ticksPerColor",
			name = "Ticks Per Color",
			description = "Number of game ticks before changing to the next color",
			position = 1
	)
	default int ticksPerColor()
	{
		return 1;
	}

	@Range(
			min = 1,
			max = 10
	)
	@ConfigItem(
			keyName = "numberOfColors",
			name = "Number of Colors",
			description = "How many colors from the list to cycle through",
			position = 2
	)
	default int numberOfColors()
	{
		return 10;
	}
}