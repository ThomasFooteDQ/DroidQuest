package com.droidquest.view.swing.room;

import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import com.droidquest.Room;
import com.droidquest.decorations.Arrow;
import com.droidquest.decorations.Graphix;
import com.droidquest.decorations.TextBox;

/**
 * Swing Renderer class which renders rooms.
 */
public class RoomView {
    private final Font smallFont;
    private final Font bigFont;
    private final ArrowView arrowView;
    private final GraphixView graphixView;
    private final TextBoxView textBoxView;

    public RoomView() {
        smallFont = new Font("Courier", Font.BOLD, 20);
        bigFont = new Font("Courier", Font.BOLD, 45);

        arrowView = new ArrowView();
        graphixView = new GraphixView();
        textBoxView = new TextBoxView();
    }

    public void draw(Graphics graphics, Room room) {
        drawTextBoxes(graphics, room.getTextBoxes());
        drawGraphix(graphics, room.getGraphix());
        drawArrows(graphics, room.getArrows());
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

}
