package model;

import java.awt.*;

/**
 * Created by susanchen on 2017-06-04.
 * Represents an item
 */
public abstract class Item {
    protected int x;
    protected int y;
    protected int width;
    protected int height;

    //Constructs an item
    //Effects: item is at the specified location with given width and height
    public Item(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() { return x; }

    public int getY() { return y; }

    public int getWidth() {return width;}

    public int getHeight() {return height;}

    // Moves the item
    // Modifies: this
    // Effects: Item has been moved

    public void move() {
        handleBondary();
    }

    // Draws the item
    // modifies: g
    // effects: draws the item on the Graphics object g
    public abstract void draw(Graphics g);

    // Constrains item so that it doesn't travel off sides of screen
    // modifies: this
    // effects: item is constrained to remain within vertical boundaries of game
    protected void handleBondary() {
        if (y < 0)
            y = 0;
        else if (y > DDGame.HEIGHT)
            y = DDGame.HEIGHT;
    }
}
