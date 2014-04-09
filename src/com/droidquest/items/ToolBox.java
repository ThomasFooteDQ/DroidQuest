package com.droidquest.items;

import com.droidquest.Room;
import com.droidquest.devices.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ToolBox extends Item {
    public boolean open;

    public ToolBox(int X, int Y, Room r) {
        x = X;
        y = Y;
        room = r;
        width = 26;
        height = 22;
//	width=166; height=94;
        open = false;
        GenerateIcons();
        currentIcon = icons[0].getImage();

    }

    public void GenerateIcons() {
        // Executes once during initialization
        icons = new ImageIcon[2];
        icons[0] = new ImageIcon(new BufferedImage(26, 22, BufferedImage.TYPE_4BYTE_ABGR));
        icons[1] = new ImageIcon(new BufferedImage(166, 94, BufferedImage.TYPE_4BYTE_ABGR));
        Graphics g;
        Graphics2D g2;
        try {
            g = icons[0].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to Remote Image[0]");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.clearRect(0, 0, width, height);
        g2.setColor(Color.blue);
        g2.fillRect(4, 0, 18, 2);
        g2.fillRect(0, 2, 26, 20);
        g2.setColor(Color.black);
        g2.fillRect(6, 2, 14, 2);
        g2.fillRect(0, 6, 26, 2);
        g2.fillRect(6, 10, 14, 2);
        g2.fillRect(10, 12, 6, 2);

        try {
            g = icons[1].getImage().getGraphics();
        }
        catch (NullPointerException e) {
            System.out.println("Could not get Graphics pointer to Remote Image[0]");
            return;
        }
        g2 = (Graphics2D) g;
        g2.setBackground(new Color(0, 0, 0, 0));
        g2.clearRect(0, 0, 166, 94);
        g2.setColor(Color.white);
        g2.drawRect(0, 0, 165, 93);
        g2.setColor(Color.blue);
        g2.fillRect(4, 72 + 0, 18, 2);
        g2.fillRect(0, 72 + 2, 26, 20);
        g2.setColor(Color.black);
        g2.fillRect(6, 72 + 2, 14, 2);
        g2.fillRect(0, 72 + 6, 26, 2);
        g2.fillRect(6, 72 + 10, 14, 2);
        g2.fillRect(10, 72 + 12, 6, 2);

        // Create temporary Gates, call their Function() and Animate() to
        // draw them, and then transfer their images into icons[0].

        // ToolBox 26,22
        // AND     28,50
        // OR      28,50
        // XOR     28,50
        // NOT     28,50
        // FF      48,32
        // NODE    36,32
        // NODE90  36,32
        // NODE3   36,32

        // Width  = 48 + 36 + 28 + 28 + 6*4 + 26 = 190
        // Height = 50 + 50 + 6 = 106

        ANDGate ag = new ANDGate(0, 0, null);
        ag.Function();
        ag.Decorate();
        g2.drawImage(ag.icons[0].getImage(), 30, 4, level);

        ORGate og = new ORGate(0, 0, null);
        og.Function();
        og.Decorate();
        g2.drawImage(og.icons[0].getImage(), 64, 4, level);

        XORGate xg = new XORGate(0, 0, null);
        xg.Function();
        xg.Decorate();
        g2.drawImage(xg.icons[0].getImage(), 98, 4, level);

        NOTGate ng = new NOTGate(0, 0, null);
        ng.Function();
        ng.Decorate();
        g2.drawImage(ng.icons[0].getImage(), 132, 4, level);


        FlipFlop ff = new FlipFlop(0, 0, null);
        ff.Function();
        ff.Decorate();
        g2.drawImage(ff.icons[0].getImage(), 30, 58, level);

        Node n1 = new Node(0, 0, null, Node.TYPE_STRAIGHT);
        n1.Function();
        n1.Decorate();
        g2.drawImage(n1.icons[0].getImage(), 74, 58, level);

        Node n2 = new Node(0, 0, null, Node.TYPE_RIGHT);
        n2.Function();
        n2.Decorate();
        g2.drawImage(n2.icons[0].getImage(), 98, 58, level);

        Node n3 = new Node(0, 0, null, Node.TYPE_THREE);
        n3.Function();
        n3.Decorate();
        g2.drawImage(n3.icons[0].getImage(), 128, 58, level);

        if (open) {
            currentIcon = icons[1].getImage();
        }
        else {
            currentIcon = icons[0].getImage();
        }
    }

    public void Toggle() {
        if (open) {
            // Close ToolBox
            width = 26;
            height = 22;
            open = false;
            currentIcon = icons[0].getImage();
            y += 72;
            if (y > 320) {
                y = 320;
            }
        }
        else {
            // Open ToolBox
            width = 166;
            height = 94;
            open = true;
            currentIcon = icons[1].getImage();
            y -= 72;
            if (y < 32) {
                y = 32;
            }
            if (x > 338) {
                x = 338;
            }
        }
    }

    public boolean CanBePickedUp(Item item) {
        if (item != level.gameCursor) {
            return false;
        }
        if (open) {
            int ix = item.x - x + item.width / 2;
            int iy = item.y - y + item.height / 2;
            if (ix < 26 && iy > 72) {
                return true;
            }

            if (ix > 30 && ix < (30 + 28) && iy > 4 && iy < (4 + 50)) {
                ANDGate ag = new ANDGate(x + 30, y + 4, item.room);
                level.items.addElement(ag);
                item.PicksUp(ag);
                return false;
            }

            if (ix > 64 && ix < (64 + 28) && iy > 4 && iy < (4 + 50)) {
                ORGate og = new ORGate(x + 64, y + 4, item.room);
                level.items.addElement(og);
                item.PicksUp(og);
                return false;
            }

            if (ix > 98 && ix < (98 + 28) && iy > 4 && iy < (4 + 50)) {
                XORGate xg = new XORGate(x + 98, y + 4, item.room);
                level.items.addElement(xg);
                item.PicksUp(xg);
                return false;
            }

            if (ix > 132 && ix < (132 + 28) && iy > 4 && iy < (4 + 50)) {
                NOTGate ng = new NOTGate(x + 132, y + 4, item.room);
                level.items.addElement(ng);
                ng.ports[1].value = true;
                item.PicksUp(ng);
                return false;
            }

            if (ix > 30 && ix < (30 + 48) && iy > 58 && iy < (58 + 32)) {
                FlipFlop ff = new FlipFlop(x + 30, y + 58, item.room);
                level.items.addElement(ff);
                ff.ports[3].value = true;
                item.PicksUp(ff);
                return false;
            }

            if (ix > 74 && ix < (74 + 22) && iy > 58 && iy < (58 + 32)) {
                Node n1 = new Node(x + 74, y + 58, item.room, Node.TYPE_STRAIGHT);
                level.items.addElement(n1);
                item.PicksUp(n1);
                return false;
            }

            if (ix > 98 && ix < (98 + 28) && iy > 58 && iy < (58 + 32)) {
                Node n2 = new Node(x + 98, y + 58, item.room, Node.TYPE_RIGHT);
                level.items.addElement(n2);
                item.PicksUp(n2);
                return false;
            }

            if (ix > 128 && ix < (128 + 28) && iy > 58 && iy < (58 + 32)) {
                Node n3 = new Node(x + 128, y + 58, item.room, Node.TYPE_THREE);
                level.items.addElement(n3);
                item.PicksUp(n3);
                return false;
            }
        }
        else {
            return true;
        }
        return false;
    }

}
