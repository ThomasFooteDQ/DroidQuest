package com.droidquest.view.swing.decoration;

import java.awt.Color;
import java.awt.Graphics;

import com.droidquest.decorations.Spark;

/**
 * Swing renderer which paints sparks.
 */
public class SparkView {

    public void draw(Graphics g, Spark spark) {
        g.setColor(getColor(spark));
        g.fillRect(spark.getX(), spark.getY(), 2, 2);
    }

    private Color getColor(Spark spark) {
        if (spark.getAge() < 2)
            return Color.white;
        else if (spark.getAge() < 4)
            return Color.yellow;
        else
            return Color.red;
    }
}
