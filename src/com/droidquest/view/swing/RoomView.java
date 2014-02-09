package com.droidquest.view.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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

    public RoomView() {
        smallFont = new Font("Courier", Font.BOLD, 20);
        bigFont = new Font("Courier", Font.BOLD, 45);
    }

    public void draw(Graphics graphics, Room room) {
        drawTextBoxes(graphics, room.getTextBoxes());
        drawGraphix(graphics, room.getGraphix());
        drawArrows(graphics, room.getArrows());
    }

    public void drawTextBoxes(Graphics g, List<TextBox> textBoxes)
    {
        for (TextBox textBox : textBoxes)
        {
            g.setColor(Color.white);
            g.setFont(smallFont);

            int cursX = textBox.x;
            int cursY = textBox.y;
            int advY=0;
            int advX;
            String nextWord;
            int indexFrom=0;
            int indexTo;

            do
            {
                // Get the next word in the string
                if (indexFrom >= textBox.textString.lastIndexOf(" "))
                {indexTo = textBox.textString.length();}
                else
                {indexTo = textBox.textString.indexOf(" ",indexFrom+1);}
                nextWord = textBox.textString.substring(indexFrom, indexTo);
                if (nextWord.startsWith(" "))
                {
                    nextWord = nextWord.substring(1,nextWord.length());
                }
                if (!nextWord.endsWith(" "))
                {
                    nextWord = nextWord + " ";
                }

                if (nextWord.startsWith("{BIG}"))
                {
                    g.setFont(bigFont);
                }

                else if (nextWord.startsWith("{SML}"))
                {
                    g.setFont(smallFont);
                }

                else if (nextWord.startsWith("{BSP}"))
                {
                    FontMetrics fm = g.getFontMetrics();
                    advX = fm.stringWidth(" ");
                    cursX-=advX;
                }

                // if (nextWord fits "{rrr,ggg,bbb} "
                else if (nextWord.startsWith("{")
                        && nextWord.endsWith("} ")
                        && nextWord.length()==14)
                {
                    // extract rrr,ggg,bbb
                    Integer rr = new Integer(nextWord.substring(1,4));
                    Integer gg = new Integer(nextWord.substring(5,8));
                    Integer bb = new Integer(nextWord.substring(9,12));
                    g.setColor(new Color(rr.intValue(),
                            gg.intValue(),
                            bb.intValue()));
                }
                else
                {
                    FontMetrics fm = g.getFontMetrics();
                    if (fm.getAscent() > advY)
                    {advY = fm.getAscent() ;}
                    advX = fm.stringWidth(nextWord);
                    if (cursX+advX > textBox.width + textBox.x)
                    {
                        cursX=textBox.x;
                        cursY+=advY;
                        advY=fm.getAscent();
                    }
                    g.drawString(nextWord, cursX, cursY);
                    cursX+=advX;
                    if (cursX+advX > textBox.width + textBox.x)
                    {
                        cursX=textBox.x;
                        cursY+=advY;
                        advY=fm.getAscent();
                    }
                }
                indexFrom = indexTo;
            }
            while (indexFrom < textBox.textString.length());
        }
    }

    public void drawGraphix(Graphics g, List<Graphix> graphix)
    {
        for (Graphix grx : graphix) {
            grx.Draw(g);
        }
    }

    public void drawArrows(Graphics g, List<Arrow> arrows)
    {
        for (Arrow arrow : arrows)
            arrow.Draw(g);
    }

}
