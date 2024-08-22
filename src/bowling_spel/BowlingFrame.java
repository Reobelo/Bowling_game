package bowling_spel;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

import bowling_spel.interfaces.GameObserver;

/**
 * Main frame of the Bowling application. Handles the UI layout and user interactions.
 */
public class BowlingFrame extends JFrame implements ActionListener, GameObserver {
    BowlingPanel panel;
    BowlingModel model;
    BowlingInformationPanel infoPanel;
    BowlingToolbar toolbar;

    /**
     * Constructor that initializes the frame with the given model.
     * 
     * @param aModel the game model used to manage the game's state and logic
     */
    public BowlingFrame(BowlingModel aModel) {
        this.model = aModel;
        infoPanel = new BowlingInformationPanel();
        toolbar = new BowlingToolbar();
        panel = new BowlingPanel(model);
        model.registerObserver(this);

        // Set up the frame layout
        this.add(infoPanel, BorderLayout.CENTER);
        this.add(panel, BorderLayout.NORTH);
        this.add(toolbar, BorderLayout.SOUTH);
        this.setTitle("Bowling");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        // Add action listeners to buttons in the toolbar
        toolbar.getNewGameButton().addActionListener(this);
        toolbar.getRollingButton().addActionListener(this);
    }

    /**
     * Handles button click events from the toolbar.
     * 
     * @param e the action event triggered by a button click
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == toolbar.getNewGameButton()) {
            System.out.println("new game requested!");
            model.resetGame();
            model.print(model.getroundCounter());
            this.repaint();
        }
        if (e.getSource() == toolbar.getRollingButton()) {
            model.rollDice();
            this.repaint();
        }
    }

    /**
     * Updates the UI based on changes in the game state.
     * 
     * @param event the event that occurred in the game (e.g., resetGame, strike, etc.)
     */
    @Override
    public void update(String event) {
        switch (event) {
            case "resetGame":
                infoPanel.printEvent("New game");
                break;
            case "strike":
                infoPanel.printEvent(event);
                break;
            case "spärr":
                infoPanel.printEvent(event);
                break;
            case "gameOver":
                infoPanel.printEvent("Game ended!");
                break;
            default:
                infoPanel.printEvent(event);
        }
    }
}
