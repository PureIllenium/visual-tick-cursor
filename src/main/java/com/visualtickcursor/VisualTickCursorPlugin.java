package com.visualtickcursor;

import com.google.inject.Provides;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.inject.Inject;
import javax.imageio.ImageIO;

import lombok.extern.slf4j.Slf4j;

import net.runelite.api.Client;
import net.runelite.api.events.GameTick;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
		name = "Visual Tick Cursor"
)
public class VisualTickCursorPlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private VisualTickCursorConfig config;

	private BufferedImage baseCursorImage;

	private final Point hotspot = new Point(0, 0);

	private int tickCounter = 0;
	private int colorIndex = 0;

	private Color[] palette = new Color[0];

	@Provides
	VisualTickCursorConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(VisualTickCursorConfig.class);
	}

	@Override
	protected void startUp() throws Exception
	{
		log.info("VisualTickCursor starting...");

		InputStream in = VisualTickCursorPlugin.class.getResourceAsStream("/cursor.png");

		if (in == null)
		{
			log.error("cursor.png not found at /cursor.png");
			return;
		}

		baseCursorImage = ImageIO.read(in);

		log.info("cursor loaded: {}x{}",
				baseCursorImage.getWidth(),
				baseCursorImage.getHeight());

		palette = getActiveColorPalette();

		if (palette.length > 0)
		{
			applyCursor(palette[0]);
		}
	}

	@Override
	protected void shutDown()
	{
		client.getCanvas().setCursor(Cursor.getDefaultCursor());
	}

	@Subscribe
	public void onGameTick(GameTick tick)
	{
		if (baseCursorImage == null || palette.length == 0)
		{
			return;
		}

		if (++tickCounter < Math.max(1, config.ticksPerColor()))
		{
			return;
		}

		tickCounter = 0;

		palette = getActiveColorPalette();

		colorIndex = (colorIndex + 1) % palette.length;

		applyCursor(palette[colorIndex]);
	}

	private BufferedImage tintImage(BufferedImage src, Color color)
	{
		BufferedImage out = new BufferedImage(
				src.getWidth(),
				src.getHeight(),
				BufferedImage.TYPE_INT_ARGB
		);

		for (int y = 0; y < src.getHeight(); y++)
		{
			for (int x = 0; x < src.getWidth(); x++)
			{
				int rgba = src.getRGB(x, y);
				Color original = new Color(rgba, true);

				int a = original.getAlpha();

				int r = color.getRed();
				int g = color.getGreen();
				int b = color.getBlue();

				out.setRGB(x, y, new Color(r, g, b, a).getRGB());
			}
		}

		return out;
	}

	private void applyCursor(Color color)
	{
		if (baseCursorImage == null)
		{
			return;
		}

		BufferedImage tinted = tintImage(baseCursorImage, color);

		Cursor cursor = Toolkit.getDefaultToolkit()
				.createCustomCursor(tinted, hotspot, "vtc");

		client.getCanvas().setCursor(null);
		client.getCanvas().setCursor(cursor);

		log.debug("Applied cursor color: {}", color);
	}

	private Color[] getActiveColorPalette()
	{
		Color[] all =
				{
						config.color1(),
						config.color2(),
						config.color3(),
						config.color4(),
						config.color5(),
						config.color6(),
						config.color7(),
						config.color8(),
						config.color9(),
						config.color10()
				};

		int requested = Math.max(1, Math.min(10, config.numberOfColors()));

		Color[] result = new Color[requested];
		int count = 0;

		for (int i = 0; i < requested; i++)
		{
			Color c = all[i];
			if (c != null)
			{
				result[count++] = c;
			}
		}

		Color[] trimmed = new Color[count];
		System.arraycopy(result, 0, trimmed, 0, count);

		return trimmed;
	}
}