package model;

import java.awt.*;

/**
 * Created by susanchen on 2017-06-04.
 * Represents a dog
 */
public class Dog extends Item {

    public static final int DY = 2;
    private static final int SIZE_X = 30;
    private static final int SIZE_Y = 20;
    private static final int DOG_X = 40;
    private static final Color COLOR = new Color(255, 253, 150);
    private static final int UP = -1;
    private static final int DOWN = 1;

    private int direction;

    // Constructs a dog
    // Effects: dog is located at position (DOG_X, y)
    public Dog(int y) {
        super(DOG_X, y, SIZE_X, SIZE_Y);
        direction = UP;
    }

    // EFFECTS: returns true if dog is facing up, false otherwise
    public boolean isFacingUp() {return direction == UP;}

    // Faces dog upwards
    // modifies: this
    // effects: dog is facing upwards
    public void faceUp() {direction = UP;}

    // Faces dog downwards
    // modifies: this
    // effects: dog is facing downwards
    public void faceDown() {direction = DOWN;}

    @Override
    public void move() {
        y = y + direction * DY;

        super.move();
    }

    @Override
    public void draw(Graphics g) {
        Color savedCol = g.getColor();
        g.setColor(COLOR);
        g.fillOval(getX() - SIZE_X / 2, getY() - SIZE_Y / 2, SIZE_X, SIZE_Y);
        //g.fillOval(getX() + SIZE_X, getY() - 3, SIZE_X / 2, SIZE_Y);
        //Polygon tail = createTail();
        //g.fillPolygon(tail);
        g.setColor(savedCol);
    }

    // EFFECTS: returns a polygon that represents front of dog
    private Polygon createTail() {
        Polygon tail = new Polygon();

        tail.addPoint(DOG_X - SIZE_X / 2, y + SIZE_Y / 6);
        tail.addPoint(DOG_X - SIZE_X, y);
        tail.addPoint(DOG_X - SIZE_X / 2, y - SIZE_Y / 6);

        return tail;
    }


}
