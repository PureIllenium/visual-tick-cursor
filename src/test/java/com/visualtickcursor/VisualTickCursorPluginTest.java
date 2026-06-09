package com.visualtickcursor;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class VisualTickCursorPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(VisualTickCursorPlugin.class);
		RuneLite.main(args);
	}
}