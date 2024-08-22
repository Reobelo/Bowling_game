package bowling_spel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Toolbar panel containing controls for starting a new game and rolling the ball.
 */
public class BowlingToolbar extends JPanel {

    static final int TOOLBAR_WIDTH = 800;
    static final int TOOLBAR_HEIGHT = 30;
    private JButton newGameButton;
    private JButton rollButton;
    private JLabel playerLabel;

    /**
     * Constructor that initializes the toolbar.
     */
    public BowlingToolbar() {
        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(TOOLBAR_WIDTH, TOOLBAR_HEIGHT));
        this.setBackground(Color.orange);

        // Initialize buttons and label
        newGameButton = new JButton("New Game");
        rollButton = new JButton("Roll!");
        playerLabel = new JLabel(" Player 1 (Magnus)");

        // Add components to the toolbar panel
        this.add(newGameButton, BorderLayout.WEST);
        this.add(rollButton, BorderLayout.EAST);
        this.add(playerLabel, BorderLayout.CENTER);
    }

    /**
     * Retrieves the "New Game" button.
     *
     * @return the new game button
     */
    public JButton getNewGameButton() {
        return newGameButton;
    }

    /**
     * Retrieves the "Roll" button.
     *
     * @return the roll button
     */
    public JButton getRollingButton() {
        return rollButton;
    }

    /**
     * Updates the toolbar label to display the current player.
     *
     * @param name the name of the current player
     */
    public void setPlayer(String name) {
        playerLabel.setText(name);
    }
}
