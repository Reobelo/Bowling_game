package bowling_spel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Panel that displays information about game events, such as strikes, spares, and game over messages.
 */
public class BowlingInformationPanel extends JPanel {

    private static final int SCREEN_WIDTH = 800;
    private static final int SCREEN_HEIGHT = 400;
    private static String gameEvent = "Välkommen"; // Default welcome message

    /**
     * Constructor that initializes the panel.
     */
    public BowlingInformationPanel() {
        initialize();
    }

    /**
     * Initializes the panel with default settings.
     */
    public void initialize() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.setFocusable(false);
    }

    /**
     * Updates the panel with a new event message.
     * 
     * @param anEvent the event message to be displayed
     */
    public void printEvent(String anEvent) {
        this.gameEvent = anEvent;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    /**
     * Draws the game events and credit (name) on the panel.
     *
     * @param g the Graphics object used for drawing
     */
    private void draw(Graphics g) {
        if (!gameEvent.isEmpty()) {
            drawGameStatus(g, gameEvent);
        }
        drawCredit(g);
    }

    /**
     * Draws the game status on the panel.
     *
     * @param g the Graphics object used for drawing
     * @param state the state to be displayed (e.g., "Game Over", "Congrats! You won!")
     */
    public void drawGameStatus(Graphics g, String state) {
        // Set font and color for the game event text
        g.setColor(Color.RED);
        g.setFont(new Font("Ink Free", Font.BOLD, (int) (this.getPanelWidth() * 0.15)));
        FontMetrics metrics = getFontMetrics(g.getFont());

        // Draw event centered horizontally
        int xGameOver = (this.getPanelWidth() - metrics.stringWidth(state)) / 2;
        int yGameOver = this.getPanelHeight() / 2;
        g.drawString(state, xGameOver, yGameOver);
    }

    /**
     * Draws the credit text on the panel.
     *
     * @param g the Graphics object used for drawing
     */
    private void drawCredit(Graphics g) {
        // Set font and color for the credit text
        String creditText = "By Haben A.";
        g.setFont(new Font("Ink Free", Font.BOLD, 20));
        FontMetrics creditMetrics = getFontMetrics(g.getFont());
        int xCredit = this.getPanelWidth() - creditMetrics.stringWidth(creditText);
        int yCredit = (int) (this.getPanelHeight() - (creditMetrics.getHeight() * 0.3));
        g.setColor(Color.CYAN);
        g.drawString(creditText, xCredit, yCredit);
    }

    /**
     * Retrieves the total width of the panel.
     *
     * @return the total panel width
     */
    public int getPanelWidth() {
        return this.SCREEN_WIDTH;
    }

    /**
     * Retrieves the total height of the panel.
     *
     * @return the total panel height
     */
    public int getPanelHeight() {
        return this.SCREEN_HEIGHT;
    }
}
