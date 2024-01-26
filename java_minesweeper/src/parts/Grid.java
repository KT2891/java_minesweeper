package parts;

public class Grid {
	
	private Cell[][] grid;
	private int rows;
	private int cols;
	
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		grid = new Cell[rows][cols];
		initializeGrid();
	}
	
	public void initializeGrid() {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				grid[i][j] = new Cell(i , j);
	}

}
