package bowling_spel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel that displays the current game status and scoreboard.
 */
public class BowlingPanel extends JPanel {

    // Instance variables to hold the game model and panel dimensions
    private JPanel descriptionPanel;
    private BowlingModel model;
    private ScoreBoardPanel mainScoreBoard;

    /**
     * Constructor that initializes the panel with the given model.
     * 
     * @param aModel the game model used to manage the game's state and logic
     */
    public BowlingPanel(BowlingModel aModel) {
        this.model = aModel;
        initialize();
    }

    /**
     * Initializes the UI components of the panel.
     */
    public void initialize() {
        descriptionPanel = new JPanel();
        descriptionPanel.setPreferredSize(new Dimension(150, 150));
        descriptionPanel.setBackground(Color.gray);

        // Labels to display rounds, chances, and total score
        JLabel rounds = new JLabel("Rounds: ");
        JLabel roundPartScore = new JLabel("Chance: ");
        JLabel roundTotalScore = new JLabel("Total: ");

        descriptionPanel.setLayout(new BoxLayout(descriptionPanel, BoxLayout.Y_AXIS));

        // Set a larger font size for the labels
        Font labelFont = new Font("Arial", Font.PLAIN, 30);
        rounds.setFont(labelFont);
        roundPartScore.setFont(labelFont);
        roundTotalScore.setFont(labelFont);

        // Add padding around the labels
        rounds.setBorder(BorderFactory.createEmptyBorder(10, 30, 0, 0));
        roundPartScore.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 0));
        roundTotalScore.setBorder(BorderFactory.createEmptyBorder(10, 60, 30, 0));

        // Add labels to the description panel
        descriptionPanel.add(rounds);
        descriptionPanel.add(roundPartScore);
        descriptionPanel.add(roundTotalScore);

        // Initialize the scoreboard panel and add it to the main panel
        mainScoreBoard = new ScoreBoardPanel(model);
        mainScoreBoard.setPreferredSize(new Dimension(605, 185));
        mainScoreBoard.setBackground(Color.pink);

        this.setBackground(Color.gray);
        this.setDoubleBuffered(true);
        this.add(descriptionPanel, BorderLayout.EAST);
        this.add(mainScoreBoard, BorderLayout.CENTER);
    }
}
