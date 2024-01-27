package mainWindow;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;

import enums.Status;
import parts.Grid;

public class MainWindow {	
	
	private final JFrame frame;
	
	private final JButton resetButton;
	private final JButton[][] cellButton;
	
	private final JLabel gameLabel;
	private final JLabel mineLabel;
	private final JLabel flagLabel;
	
	private Status state;
	private Grid grid;
	private int lows;
	private int cols;
	
	public MainWindow(int x, int y) {
		try {
	    UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
		} catch (Exception e) {
	    e.printStackTrace();
		}
		this.lows = x;
		this.cols = y;
		
		this.frame = new JFrame("マインスイーパ");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setBounds(10, 10, 1000, 1000);
		
		var pane = this.frame.getContentPane();
		
		var canvas = new JPanel();
		canvas.setLayout(null);
		
		this.gameLabel = new JLabel("ゲーム進行中");
		this.gameLabel.setBounds(150, 20, 150, 20);
		canvas.add(this.gameLabel);
		
		this.resetButton = new JButton("One more");
		this.resetButton.setBounds((1000 - 100)/2, 50, 100, 40);
		this.resetButton.addActionListener((e) -> this.init(this.lows, this.cols));
		canvas.add(this.resetButton);
		
		this.cellButton = new JButton[x][y];
		
		for(int i = 0; i < this.lows; i++) {
			for(int j = 0; j < this.cols; j++) {
				this.cellButton[i][j] = new JButton();
				this.cellButton[i][j].setFont(new Font("ＭＳ ゴシック", Font.BOLD, 16));
				this.setButton(i, j);
        canvas.add(this.cellButton[i][j]);
			}
		}
		
		pane.add(canvas);
		
		this.mineLabel = new JLabel();
		this.mineLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 24));
		this.mineLabel.setBounds(100, 50, 100, 40);		
		canvas.add(mineLabel);
		
		this.flagLabel = new JLabel();
		this.flagLabel.setFont(new Font("ＭＳ ゴシック", Font.BOLD, 24));
		this.flagLabel.setBounds(800, 50, 100, 40);
		canvas.add(flagLabel);
	}
	
	public void setButton(int i, int j) {
		this.cellButton[i][j].setText("");
		this.cellButton[i][j].setBounds(100 + i*52, 100 + j*52, 50, 50);
		this.cellButton[i][j].setForeground(Color.WHITE);
		this.cellButton[i][j].setBackground(Color.DARK_GRAY);
    final int finalI = i; // 最終的な変数にiの値をコピー
    final int finalJ = j; // 最終的な変数にjの値をコピー

// ラムダ式内で finalI と finalJ を使用
    this.cellButton[i][j].addActionListener((e) -> this.clickCell(finalI, finalJ));
// 右クリック用のアクションリスナー
    this.cellButton[i][j].addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent f) {
        if (f.getButton() == MouseEvent.BUTTON3) { // 右クリックの場合
            actionFlag(finalI, finalJ);
        }
      }
    });
	}
	
	public void show(int x, int y) {
		this.frame.setVisible(true);
		this.init(x, y);
	}
	
	public void init(int x, int y) {
		this.state = Status.Done;
		this.grid = new Grid(x ,y);
		this.gameLabel.setText("ゲーム進行中");
		this.mineLabel.setText(String.format("%s", this.grid.countMines()));
		this.flagLabel.setText(String.format("%s", this.grid.countFlags()));
		for(int i = 0; i < this.lows; i++) {
			for(int j = 0; j < this.cols; j++)
				this.setButton(i, j);
		}
	}
	
	public void gameOver() {
		this.state = Status.End;
		this.gameLabel.setText("そこは爆弾です。");
		for(int i = 0; i < this.lows; i++) {
			for(int j = 0; j < this.cols; j++) {
				this.updateButton(i, j);
				if (i >= 0 && i < this.lows && j >= 0 && j < this.cols) {
					if(this.grid.getCell(i, j).getMine()) {
						this.cellButton[i][j].setText("X");
					} else if (this.grid.getCount(i, j) != 0) {
						this.showCount(i, j);
					}
				}
			}
		}
	}
	
//	左クリックのアクション
//	爆弾の場合はゲームオーバーとして全ての爆弾やカウントを表示
//	爆弾でない場合はそのマスのカウントを表示
	public void clickCell(int x, int y) {
		if (this.grid.getCell(x, y).getOpen() || 	this.state == Status.End )
			return;
		
		if(this.grid.getCell(x, y).getMine() ) {
			this.gameOver();
		} else if (this.grid.getCount(x, y) != 0) {
			this.grid.getCell(x, y).setOpen(true);
			this.updateButton(x, y);
			this.showCount(x, y);
		} else {
			this.updateButton(x, y);
		}
	}
	
//	右クリックのアクション
	public void actionFlag(int x, int y) {
		if (this.grid.getCell(x, y).getOpen() || 	this.state == Status.End )
			return;
		
		if (this.grid.getCell(x, y).getFlag()) {
			this.delFlag(x, y);
		} else {
			this.setFlag(x, y);
		}
		this.flagLabel.setText(String.format("%s", this.grid.countFlags()));
	}
	
	public void setFlag(int x, int y) {
		this.grid.getCell(x, y).setFlagged(true);
		this.cellButton[x][y].setText("✓");
	}
	
	public void delFlag(int x, int y) {
		this.grid.getCell(x, y).setFlagged(false);
		this.cellButton[x][y].setText("");
	}

	public void updateButton(int x, int y) {
		this.cellButton[x][y].setBackground(Color.WHITE);
		this.cellButton[x][y].setForeground(Color.BLACK);
	}
	public void showCount(int x, int y) {
		this.cellButton[x][y].setText(String.format("%s", this.grid.getCount(x, y)));
	}
}
