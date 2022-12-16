import java.util.*;
public class Grid {
	private boolean[][] bombGrid;
	private int[][] countGrid;
	private int numColumns;
	private int numRows;
	private int numBombs;

	public Grid() {
		this.numRows = 10;
		this.numColumns = 10;
		this.numBombs = 25;
		createBombGrid();
		createCountGrid();
	}
	public Grid(int rows, int columns) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = 25;
		createBombGrid();
		createCountGrid();
	}
	public Grid(int rows, int columns, int numBombs) {
		this.numRows = rows;
		this.numColumns = columns;
		this.numBombs = numBombs;
		createBombGrid();
		createCountGrid();
	}
	public int getNumRows() {
		return numRows;
	}
	public int getNumColumns() {
		return numColumns;
	}
	public int getNumBombs() {
		return numBombs;
	}
	public boolean[][] getBombGrid() {
		boolean[][] temp = new boolean[numRows][];
		int i;
		for(i = 0; i < temp.length; i++) {
			temp[i] = Arrays.copyOf(bombGrid[i], bombGrid[i].length);
		}
		return temp;
	}
	public int[][] getCountGrid() {
		int[][] temp = new int[numRows][];
		int i;
		for(i = 0; i < temp.length; i++) {
			temp[i] = Arrays.copyOf(countGrid[i], countGrid[i].length);
		}
		return temp;
	}
	public boolean isBombAtLocation(int row, int column) {
		return bombGrid[row][column];
	}
	public int getCountAtLocation(int row, int column) {
		return countGrid[row][column];
	}
	
	private void createBombGrid() {

		//Counter for loop
		int i, j;
		int rRow, rCol;
		//Random object to generate random positions
		Random rand = new Random();

		//Creating bombGrid
		bombGrid = new boolean[numRows][numColumns];

		//populating all bombGrid cells with false
		for(i = 0; i < numRows; i++) {
			for(j = 0; j<numColumns; j++) {
				bombGrid[i][j] = false;
			}	
		}

		//Generating and placing bombs randomly on bombCount
		i = 0;
		while(i < numBombs) {
			rRow = rand.nextInt(numRows-1);
			rCol = rand.nextInt(numColumns-1);
			if(bombGrid[rRow][rCol] != true) {
				bombGrid[rRow][rCol] = true;
				i++;
			}
		}
	}
	private void createCountGrid() {
		int i, j;
		int count;
		countGrid = new int[numRows][numColumns];
		for(i = 0; i < numRows; i++) {
			for(j = 0; j<numColumns; j++) {
				count=0;
				if(bombGrid[i][j]) count++;
				if(j == 0) {
					if(i == 0) {
						if(bombGrid[i][j+1]) count++;
						if(bombGrid[i+1][j+1]) count++;
						if(bombGrid[i+1][j]) count++;
					}
					else if(i == (numRows-1)) {
						if(bombGrid[i-1][j]) count++;
						if(bombGrid[i-1][j+1]) count++;
						if(bombGrid[i][j+1]) count++;
					}
					else {
						if(bombGrid[i-1][j]) count++;
						if(bombGrid[i-1][j+1]) count++;
						if(bombGrid[i][j+1]) count++;
						if(bombGrid[i+1][j+1]) count++;
						if(bombGrid[i+1][j]) count++;
					}
				}
				else if(j == (numColumns-1)) {
					if(i == 0) {
						if(bombGrid[i][j-1]) count++;
						if(bombGrid[i+1][j-1]) count++;
						if(bombGrid[i+1][j]) count++;
					}
					else if(i == (numRows-1)) {
						if(bombGrid[i-1][j-1]) count++;
						if(bombGrid[i-1][j]) count++;
						if(bombGrid[i][j-1]) count++;
					}
					else {
						if(bombGrid[i][j-1]) count++;
						if(bombGrid[i+1][j-1]) count++;
						if(bombGrid[i+1][j]) count++;

						if(bombGrid[i-1][j]) count++;
						if(bombGrid[i-1][j-1]) count++;
					}
				}
				else {
					if(i == 0) {
						if(bombGrid[i][j-1]) count++;
						if(bombGrid[i+1][j-1]) count++;
						if(bombGrid[i+1][j]) count++;
						if(bombGrid[i+1][j+1]) count++;
						if(bombGrid[i][j+1]) count++;
					}
					else if(i == (numRows-1)) {
						if(bombGrid[i][j-1]) count++;
						if(bombGrid[i-1][j-1]) count++;
						if(bombGrid[i-1][j]) count++;
						if(bombGrid[i-1][j+1]) count++;
						if(bombGrid[i][j+1]) count++;
					}
					else {
						if(bombGrid[i][j-1]) count++;
						if(bombGrid[i-1][j-1]) count++;
						if(bombGrid[i-1][j]) count++;

						if(bombGrid[i-1][j+1]) count++;
						if(bombGrid[i][j+1]) count++;

						if(bombGrid[i+1][j-1]) count++;
						if(bombGrid[i+1][j]) count++;
						if(bombGrid[i+1][j+1]) count++;
					}
				}
				countGrid[i][j] = count;
			}	
		}
	}
}