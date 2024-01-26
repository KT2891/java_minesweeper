package parts;

public class Cell {
  private int row;
  private int col;
  private boolean isMine;
  private boolean isOpen;
  private boolean isFlagged;
  private int adjacentMines;

  public Cell(int row, int col) {
      this.row = row;
      this.col = col;
      this.isMine = false;
      this.isOpen = false;
      this.isFlagged = false;
      this.adjacentMines = 0;
  }

  // セッター、ゲッターメソッドなど、他の必要なメソッド

  public void setMine(boolean isMine) {
    this.isMine = isMine;
  }

  public void setOpen(boolean isOpen) {
    this.isOpen = isOpen;
  }

  public void setFlagged(boolean isFlagged) {
    this.isFlagged = isFlagged;
  }

  public void setAdjacentMines(int adjacentMines) {
    this.adjacentMines = adjacentMines;
  }
  
  public boolean getMine(boolean isMine) {
    return isMine;
	}
	
	public boolean getOpen(boolean isOpen) {
	  return isOpen;
	}
	
	public boolean getFlagged(boolean isFlagged) {
		return isFlagged;
	}
	
	public int getAdjacentMines(int adjacentMines) {
	  return adjacentMines;
	}

  // 他のメソッド（例：セルを開く、フラグを設定するなど）
}
