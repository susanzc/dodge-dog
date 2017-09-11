package ui;

import model.DDGame;

import javax.swing.*;
import java.awt.*;

/*
 * Represents the panel in which the scoreboard is displayed.
 */

public class ScorePanel extends JPanel {
    private static final String BONES_TXT = "Bones caught: ";
    private static final String POOPS_TXT = "Poops dodged: ";
    private static final int LBL_WIDTH = 200;
    private static final int LBL_HEIGHT = 30;
    private DDGame game;
    private JLabel bonesLbl;
    private JLabel poopsLbl;

    // Constructs a score panel
    // effects: sets the background colour and draws the initial labels;
    //          updates this with the game whose score is to be displayed
    public ScorePanel(DDGame g) {
        game = g;
        setBackground(new Color(176, 253, 255));
        bonesLbl = new JLabel(BONES_TXT + game.getNumBonesCaught());
        bonesLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        poopsLbl = new JLabel(POOPS_TXT + game.getNumPoopsDodged());
        poopsLbl.setPreferredSize(new Dimension(LBL_WIDTH, LBL_HEIGHT));
        add(bonesLbl);
        add(Box.createHorizontalStrut(10));
        add(poopsLbl);
    }

    // Updates the score panel
    // modifies: this
    // effects:  updates number of bones caught and number of poops
    //           dodged to reflect current state of game
    public void update() {
        bonesLbl.setText(BONES_TXT + game.getNumBonesCaught());
        poopsLbl.setText(POOPS_TXT + game.getNumPoopsDodged());
        repaint();
    }
}
