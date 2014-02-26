package com.droidquest.view.swing.item;

import java.awt.Dimension;
import java.awt.Graphics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.droidquest.items.Item;

/**
 * Swing Renderer for painting items.
 */
public class ItemView {
    private static Logger LOG = LoggerFactory.getLogger(ItemView.class);

    public void draw(Graphics g, Item item) {
        if (!item.isVisible()) {
            return;
        }

        Dimension d = item.GetXY();
        if (item.getIcon() != null)
            g.drawImage(item.getIcon(), d.width - item.getOrgX(), d.height - item.getOrgY(), null);
        else
            LOG.warn("Cannot draw {}", item.getClass());

        if (item.getOutline() != null) {
            g.setColor(item.getOutline());
            g.drawRect(d.width, d.height, item.getWidth() + 1, item.getHeight() + 1);
            g.drawRect(d.width + 1, d.height + 1, item.getWidth() - 1, item.getHeight() - 1);
            item.setOutline(null);  // FIXME Renderer shouldn't affect model state!
        }
    }
}
