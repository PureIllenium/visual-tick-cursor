package com.visualtickcursor;

import java.awt.Dimension;
import java.awt.Graphics2D;

import javax.inject.Inject;

import net.runelite.api.Client;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;

public class VisualTickCursorOverlay extends Overlay
{
    private final Client client;

    @Inject
    public VisualTickCursorOverlay(Client client)
    {
        this.client = client;

        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_WIDGETS);
    }

    @Override
    public Dimension render(Graphics2D graphics)
    {
        return null;
    }
}