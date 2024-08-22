package bowling_spel;

import java.awt.geom.Rectangle2D;

public class Tile {

    private static int x = 0;
    private static int y = 0;
    private static int DEFAULT_SIZE = 60;
    private int size;
    private String value1;
    private String value2;
    
    public Tile() {
    	size = DEFAULT_SIZE;
    	resetAllValues();
    }
    
    public void setTileSize(int aSize) {
        size = aSize;
    }
    
    
    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, size, size);
    }
    
    public String getValue1() {
        return value1;
    }
    
    public String getValue2() {
        return value2;
    }
    
    
    public void setValue1(String value1) {
        this.value1 = value1;
        
    }
    
    public void resetAllValues() {
    	value1 = "";
    	value2 = "";
    }
    
    public void setValue2(String value2) {
        this.value2 = value2;
        
    }
    
    public int getTileSize() {
        return size;
    }
    
    public String toString() {
        return value1 + ", " + value2;
    }
    
}
