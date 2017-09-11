package model;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Created by susanchen on 2017-06-04.
 * represents a dodge doge game
 */
public class DDGame {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;
    public static final Random RND = new Random();

    private List<Item> items;
    private Dog dog;
    private boolean isGameOver;
    private int numBonesCaught;
    private int numPoopsDodged;

    // Constructor
    // EFFECTS: sets up the game
    public DDGame() {
        items = new ArrayList<Item>();
        initializeItems();
        reset();
    }

    // Updates the game on clock tick
    // modifies: this
    // effects: updates dog, bones, and poops
    public void update() {
        moveItems();
        addBone();
        addPoop();
        checkCollisions();
        checkDodges();
        checkGameOver();
    }

    // Responds to key press codes
    // modifies: this
    // effects:  turns dog and resets game in response to
    //           given key pressed code
    public void keyPressed(int keyCode) {
        if (keyCode == KeyEvent.VK_KP_UP|| keyCode == KeyEvent.VK_UP)
            dog.faceUp();
        else if (keyCode == KeyEvent.VK_KP_DOWN || keyCode == KeyEvent.VK_DOWN)
            dog.faceDown();
        else if (keyCode == KeyEvent.VK_R && isGameOver)
            reset();
        else if (keyCode == KeyEvent.VK_X)
            System.exit(0);
    }

    public void draw(Graphics g) {
        for (Item anItem: items)
            anItem.draw(g);
    }

    // Is game over?
    // Effects: returns true if game is over, false otherwise
    public boolean isOver() {
        return isGameOver;
    }

    public int getNumBonesCaught() {
        return numBonesCaught;
    }

    public int getNumPoopsDodged() {return numPoopsDodged;}

    public List<Item> getItems() {
        return items;
    }

    public Dog getDog() {
        return dog;
    }

    // moves the items
    // modifies: this
    // effects: moves items to location at next time
    private void moveItems() {
        Iterator<Item> itr = items.iterator();

        while (itr.hasNext()) {
            Item next = itr.next();
            next.move();
        }
    }

    // Sets / resets the game
    // modifies: this
    // effects:  resets number of bones caught and poops dodged;
    //           game is not over
    private void reset() {
        isGameOver = false;
        numBonesCaught = 0;
        numPoopsDodged = 0;
    }

    // Initializes items
    // modifies: this
    // effects: sets up list of items with no bones, no poops, and dog halfway on the screen
    private void initializeItems() {
        items.clear();
        dog = new Dog(HEIGHT/2);
        items.add(dog);
    }

    // Add new bone onto screen
    // modifies: this
    // effects: randomly generates new bone at right of screen with random y coordinate
    private void addBone() {
        if (RND.nextInt(500) < 1) {
            Bone b = new Bone(WIDTH - 20, HEIGHT/4 + RND.nextInt(HEIGHT/2));
            items.add(b);
        }
    }

    // Add new poop onto screen
    // modifies: this
    // effects: randomly generates new poop at right of screen with random y coordinate
    private void addPoop() {
        if (RND.nextInt(250) < 1) {
            Poop p = new Poop(WIDTH - 20, RND.nextInt(HEIGHT));
            items.add(p);
        }
    }


    // Check for collisions between dog and bone or poop
    // modifies: this
    // effects: removes any bone that has been caught by the dog and adds to bone count,
    //          game is over if dog collides with poop
    private void checkCollisions() {
        List<Item> toBeRemoved = new ArrayList<Item>();
        for (Item next : items) {
            if (next.getClass() == Poop.class)
                checkPoopHit((Poop) next);
            if (next.getClass() == Bone.class)
                checkBoneCatch((Bone) next, toBeRemoved);
        }
        items.removeAll(toBeRemoved);
    }

    // Has the dog been hit by a poop?
    // modifies: this
    // effects: if the dog has been hit by a poop, the game is over
    private void checkPoopHit(Poop p) {
        if (p.collidedWith(dog))
            isGameOver =  true;
    }

    // Has the dog dodged a poop?
    // modifies: this
    // effects: if the dog has dodged a poop, remove poop from play and
    //          increment the number of poops dodged
    private void checkDodges() {
        for (Item next : items) {
            if (next.getClass() == Poop.class && next.getX() == 0)
                numPoopsDodged++;
        }
    }

    // Has the dog caught a bone?
    // modifies: this, bonesToRemove
    // effects: if dog has caught a bone, removes bone from play, increments
    //          number of bones caught
    private void checkBoneCatch(Bone b, List<Item> bonesToRemove) {
        if (b.collidedWith(dog)) {
            bonesToRemove.add(b);
            numBonesCaught++;
        }
    }

    // Is game over? (has the dog missed a bone or hit a poop)
    // modifies: this
    // effects: if dog has missed a bone or been hit by a poop, game is marked as over
    //          and lists of bones and poops cleared
    private void checkGameOver() {
        for (Item next : items) {
            if (next.getClass() == Bone.class && next.getX() < 0)
                isGameOver = true;
        }
        if (isGameOver)
            initializeItems();
    }
}
