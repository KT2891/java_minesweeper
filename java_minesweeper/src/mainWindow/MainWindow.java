package mainWindow;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import parts.Grid;

public class MainWindow {	
	
	private final JFrame frame;
	
	private final JButton resetButton;
	private final JButton[][] cellButton;
	
	private Grid grid;
	
	public MainWindow(int x, int y) {
		this.frame = new JFrame("マインスイーパ");
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setBounds(200, 200, 1000, 800);
		
		var pane = this.frame.getContentPane();
		
		var canvas = new JPanel();
		canvas.setLayout(null);
		
		this.resetButton = new JButton("One more");
		this.resetButton.setBounds((1000 - 100)/2, 20, 100, 40);
		this.resetButton.addActionListener((e) -> this.reset());
		canvas.add(this.resetButton);
		
		this.cellButton = new JButton[x][y];
		
		for(int i = 0; i < y; i++) {
			for(int j = 0; j < x; j++) {
				this.cellButton[i][j] = new JButton();
				this.cellButton[i][j].setBounds(150 + j*40, 100 + i*40, 40, 40);
				
				canvas.add(this.cellButton[i][j]);
			}
		}
		
		pane.add(canvas);
	}

	
	public void show() {
		this.init();
		this.frame.setVisible(true);
	}
	
	public void init() {
		
	}
	
	public void reset() {
		
	}
}
