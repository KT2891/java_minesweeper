package parts;

public class Cell {
  private boolean isMine;
  private boolean isOpen;
  private boolean isFlagged;
  private int adjacentMines;

  public Cell() {
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
  
  public boolean getMine() {
    return isMine;
	}
	
	public boolean getOpen() {
	  return isOpen;
	}
	
	public boolean getFlag() {
		return isFlagged;
	}
	
	public int getAdjacentMines() {
	  return adjacentMines;
	}
}
