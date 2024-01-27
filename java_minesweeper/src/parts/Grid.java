package parts;

import java.util.Random;

public class Grid {
	
	private Cell[][] cells;
	private int rows;
	private int cols;
	
	public Grid(int rows, int cols) {
		this.rows = rows;
		this.cols = cols;
		cells = new Cell[rows][cols];
		initializeGrid();
		randomSetCell(rows, cols);
	}
	
//	グリッドにセルを定義
	public void initializeGrid() {
		for (int i = 0; i < rows; i++)
			for (int j = 0; j < cols; j++)
				cells[i][j] = new Cell();
	}
	
	public void resetCell() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				cells[i][j] = new Cell();
			}
		}
	}
	
//	ランダムに爆弾を配置
//	とりあえず面積の10%を爆弾に設定
	public void randomSetCell(int x, int y) {
		Random rand = new Random();
		while (countMines() < (rows*cols/10)) {	
			cells[rand.nextInt(x)][rand.nextInt(y)].setMine(true);
		}
	}

//	爆弾の総数を取得
	public int countMines() {
	  int mineCount = 0;
	  for (int i = 0; i < rows; i++) {
	      for (int j = 0; j < cols; j++) {
	          if (cells[i][j].getMine()) {
	              mineCount++;
	          }
	      }
	  }
	  return mineCount;
	}
	
//	フラグの総数を取得
	public int countFlags() {
	  int flagCount = 0;
	  for (int i = 0; i < rows; i++) {
	      for (int j = 0; j < cols; j++) {
	          if (cells[i][j].getFlag()) {
	              flagCount++;
	          }
	      }
	  }
	  return flagCount;
	}
	
//	周囲の爆弾の数を取得
	public int getCount(int x, int y) {
		
		int aroundMines = 0;
			for(int i = x - 1; i <= x + 1; i++) {
				for(int j = y - 1; j <= y + 1; j++) {
          if (i >= 0 && i < rows && j >= 0 && j < cols && (i != x || j != y)) {
            if (cells[i][j].getMine()) {
                aroundMines++;
            }
          }
				}
			}
		return aroundMines;
	}
	
	public Cell getCell(int x, int y) {
		return cells[x][y];
	}
}
