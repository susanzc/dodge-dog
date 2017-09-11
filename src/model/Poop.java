package model;

import java.awt.*;

/**
 * Created by susanchen on 2017-06-04.
 */
public class Poop extends Item {
    public static final int DX = -2;
    public static final int SIZE_X = 30;
    public static final int SIZE_Y = 30;
    private static final Color COLOR = new Color(163, 99, 38);
    private static final int JIGGLE_Y = 1;

    // Constructor
    // Effects: poop is at position (x, y)
    public Poop(int x, int y) {super(x, y, SIZE_X, SIZE_Y);}

    @Override
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(COLOR);
        g.fillOval(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
        g.setColor(savedCol);

    }

    @Override
    public void move() {
        x = x + DX;
        y = y + DDGame.RND.nextInt(2 * JIGGLE_Y + 1) - JIGGLE_Y;
        super.move();
    }

    // Has poop collided with another item?
    // Effects: returns true if this poop has collided with other item; false otherwise
    public boolean collidedWith(Item other) {
        Rectangle thisBoundingRect = new Rectangle(getX() - getWidth() / 2, getY() - getHeight() / 2, getWidth(), getHeight());
        Rectangle otherBoundingRect = new Rectangle(other.getX() - other.getWidth() / 2, other.getY() - other.getHeight() / 2,
                other.getWidth(), other.getHeight());
        return thisBoundingRect.intersects(otherBoundingRect);
    }

}
