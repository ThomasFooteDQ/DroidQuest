package com.droidquest.view.swing.room;

import java.awt.Font;
import java.awt.Graphics;
import java.util.Collection;
import java.util.List;

import com.droidquest.Room;
import com.droidquest.decorations.Arrow;
import com.droidquest.decorations.Graphix;
import com.droidquest.decorations.TextBox;
import com.droidquest.items.Item;
import com.droidquest.view.swing.item.ItemView;
import com.droidquest.view.swing.material.MaterialView;

/**
 * Swing Renderer class which renders rooms.
 */
public class RoomView {
    private final Font smallFont;
    private final Font bigFont;
    private final ArrowView arrowView;
    private final GraphixView graphixView;
    private final TextBoxView textBoxView;
    private final MaterialView materialView;
    private final ItemView itemView;

    public RoomView() {
        smallFont = new Font("Courier", Font.BOLD, 20);
        bigFont = new Font("Courier", Font.BOLD, 45);

        arrowView = new ArrowView();
        graphixView = new GraphixView();
        textBoxView = new TextBoxView();
        materialView = new MaterialView();
        itemView = new ItemView();
    }

    public void draw(Graphics graphics, Room room, Collection<Item> items) {
        drawMaterials(graphics, room);
        drawTextBoxes(graphics, room.getTextBoxes());
        drawGraphix(graphics, room.getGraphix());
        drawArrows(graphics, room.getArrows());
        drawItems(graphics, items);
    }

    private void drawMaterials(Graphics g, Room room) {
        for (int y = 0; y < 12; y++) {
            for (int x = 0; x < 20; x++) {
                materialView.draw(g, room.getMaterial(x, y), x, y);
            }
        }
    }

    public void drawTextBoxes(Graphics g, List<TextBox> textBoxes)
    {
        for (TextBox textBox : textBoxes) {
            textBoxView.draw(g, textBox);
        }
    }

    public void drawGraphix(Graphics g, List<Graphix> graphix)
    {
        for (Graphix grx : graphix) {
            graphixView.draw(g, grx);
        }
    }

    public void drawArrows(Graphics g, List<Arrow> arrows)
    {
        for (Arrow arrow : arrows)
            arrowView.draw(g, arrow);
    }

    private void drawItems(Graphics g, Collection<Item> items) {
        for (Item item : items) {
            itemView.draw(g, item);
        }
    }
}
