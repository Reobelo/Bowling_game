package bowling_spel;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bowling_spel.interfaces.GameObserver;
import bowling_spel.interfaces.GameSubject;



public class BowlingModel implements GameSubject{

	private Tile[][] scoreBoard;
	private static final int ROW = 3;
	private static final int COL = 10;
	private int finalScore;
	private int indexCounter;
	private int tempRollScore;
	private int tempPinsLeft;
	private int rollChance;
	private static final int STRIKE_SCORE = 20;
	private static final int SPARR_SCORE = 10;
	Random random;
	
	// List of observers
    private List<GameObserver> observers = new ArrayList<>();
	
	public BowlingModel() {
		random = new Random();
		scoreBoard = new Tile[ROW][COL];
		resetGame();
	}
	
	public int getroundCounter() {
		return indexCounter;
	}
	
	public int getTotalScore() {
		return finalScore;
	}
	
	public void resetGame() {
		notifyObservers("resetGame");
		indexCounter = 0;
		finalScore = 0;
		tempRollScore = 0;
		tempPinsLeft = 10;
		rollChance = 2;
		fillGrid();
	}
	
	public void print(String some) {
		System.out.println(some);
	}
	public void print(int some) {
		System.out.println(some);
	}
	
	private int rollDice(int pins) {
	    tempRollScore = random.nextInt(pins) + 1;
	    print("TempScore: " + tempRollScore);
	    tempPinsLeft -= tempRollScore;
	    print("Pins left: " + tempPinsLeft);
	    print("Index: " + indexCounter);
	    print("Chances left: " + rollChance);
	    print("------------------------------");

	    if (rollChance == 2) {
	        if (tempRollScore == 10) { // Strike condition
	            notifyObservers("strike");
	            finalScore += STRIKE_SCORE;
	            scoreBoard[1][indexCounter].setValue1("X");
	            scoreBoard[2][indexCounter].setValue1(Integer.toString(finalScore));
	            indexCounter++;
	            rollChance = 2;
	            tempPinsLeft = 10;
	            
	        } else {
	            notifyObservers("+"+tempRollScore);
	        	finalScore += tempRollScore;
	            scoreBoard[1][indexCounter].setValue1(tempRollScore == 0 ? "-" : Integer.toString(tempRollScore));
	            scoreBoard[2][indexCounter].setValue1(Integer.toString(finalScore));
	            rollChance--;
	        }
	    } else if (rollChance == 1) {
	        if (tempPinsLeft == 0) { // Spare condition
	        	notifyObservers("spärr");
	        	finalScore += pins+SPARR_SCORE;
	            scoreBoard[1][indexCounter].setValue2("/");
	            scoreBoard[2][indexCounter].setValue1(Integer.toString(finalScore));
	        } else {
	        	notifyObservers("+"+tempRollScore);
	        	finalScore += tempRollScore;
	            scoreBoard[1][indexCounter].setValue2(tempRollScore == 0 ? "-" : Integer.toString(tempRollScore));
	            scoreBoard[2][indexCounter].setValue1(Integer.toString(finalScore));
	        }
	        rollChance = 2;
	        indexCounter++;
	        tempPinsLeft = 10;
	    }

	    return tempRollScore;
	}


	
	public void rollDice() {
		
		if(indexCounter < COL) {
			
			 rollDice(tempPinsLeft);

		}else {
			notifyObservers("gameOver");
		}	

	}
	
	
	
	public void fillGrid() {
	    for (int i = 0; i < getRows(); i++) {
	        for (int j = 0; j < getColumns(); j++) {
	            Tile newTile = new Tile(); // Ensure this is a new Tile object for each cell

	            if (i == 0) {
	                newTile.setValue1(Integer.toString(j + 1)); // Frame number
	            } else if (i == 1) {
	                newTile.setValue1(""); // Placeholder for first roll
	                newTile.setValue2(""); // Placeholder for second roll
	            } else if (i == 2) {
	                newTile.setValue1(""); // Placeholder for cumulative score
	            }

	            scoreBoard[i][j] = newTile; // Assign the new Tile to the grid cell
	        }
	    }
	}

	
    public int getTileSize() {
        return scoreBoard[0][0].getTileSize();
    }
	
	public int getRows() {
		return ROW;
	}
	
	public int getColumns() {
		return COL;
	}
	
	public Tile[][] getGrid() {
        return this.scoreBoard;
    }
	
	/**
     * Retrieves the list of observers.
     *
     * @return an ArrayList of GameObserver objects
     */
    public ArrayList<GameObserver> getObservers() {
        return (ArrayList<GameObserver>) observers;
    }

	@Override
	public void registerObserver(GameObserver observer) {
		if (observer != null)
	        observers.add(observer);
	}

	@Override
	public void removeObserver(GameObserver observer) {
		if (observer != null)
            observers.remove(observer);
	}

	@Override
	public void notifyObservers(String event) {
		for (GameObserver observer : observers) {
            observer.update(event);
        }
	}
}
