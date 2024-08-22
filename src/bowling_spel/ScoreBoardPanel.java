package bowling_spel;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;

public class ScoreBoardPanel extends JPanel {
    private BowlingModel model;  // Reference to the game model containing the scoreboard data

    // Constructor initializes the panel with the game model
    public ScoreBoardPanel(BowlingModel model) {
        this.model = model;
    }

    // Overriding the paintComponent method to draw the custom scoreboard
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawTiles(g);  // Call method to draw the grid tiles
    }

    // Method to draw the tiles (individual grid cells) on the scoreboard
    private void drawTiles(Graphics g) {
        if (model != null) {  // Check if the model is not null
            int tileSize = model.getTileSize();  // Get the size of each tile
            int margin = 3;  // Margin between tiles

            // Set the font for the tile values
            g.setFont(new Font("Arial", Font.BOLD, 24));

            // Iterate over the rows and columns of the grid
            for (int i = 0; i < model.getRows(); i++) {
                for (int j = 0; j < model.getColumns(); j++) {
                    
                    if (i == 1) {  // Special case for row 1 (second row)
                        // Draw two smaller rectangles side by side for each tile in row 1
                        for (int k = 0; k < 2; k++) {
                            int x = (j * 2 + k) * tileSize / 2 + margin;  // Calculate x position
                            int y = i * tileSize + margin;  // Calculate y position

                            // Draw the rectangle
                            g.setColor(Color.LIGHT_GRAY);
                            g.fillRect(x, y, tileSize / 2 - margin, tileSize - margin);

                            // Get the tile at the current position
                            Tile tile = model.getGrid()[i][j];
                            
                            // Check if the tile is not null and contains a value
                            if (tile != null && !tile.getValue1().isEmpty()) {
                                // Determine which value to draw (k == 0 for value1, k == 1 for value2)
                                g.setColor(Color.BLACK);
                                String value = (k == 0) ? tile.getValue1() : tile.getValue2();

                                // Calculate the position for the text
                                int stringWidth = g.getFontMetrics().stringWidth(value);
                                int stringHeight = g.getFontMetrics().getAscent();
                                int stringX = x + (tileSize / 2 - margin - stringWidth) / 2;
                                int stringY = y + (tileSize - margin + stringHeight) / 2 - g.getFontMetrics().getDescent();

                                // Draw the value inside the rectangle
                                g.drawString(value, stringX, stringY);
                            }
                        }
                    } else {
                        // Normal case for other rows (i != 1)
                        Tile tile = model.getGrid()[i][j];  // Get the tile at the current position

                        int x = j * tileSize + margin;  // Calculate x position
                        int y = i * tileSize + margin;  // Calculate y position

                        // Draw the rectangle
                        g.setColor(Color.LIGHT_GRAY);
                        g.fillRect(x, y, tileSize - margin, tileSize - margin);

                        // Draw the tile value if it exists
                        if (tile != null) {
                            g.setColor(Color.BLACK);
                            String value = tile.getValue1();

                            // Calculate the position for the text
                            int stringWidth = g.getFontMetrics().stringWidth(value);
                            int stringHeight = g.getFontMetrics().getAscent();
                            int stringX = x + (tileSize - margin - stringWidth) / 2;
                            int stringY = y + (tileSize - margin + stringHeight) / 2 - g.getFontMetrics().getDescent();

                            // Draw the value inside the rectangle
                            g.drawString(value, stringX, stringY);
                        }
                    }
                }
            }
        }
    }
}
