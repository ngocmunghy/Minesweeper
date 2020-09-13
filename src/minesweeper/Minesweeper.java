package minesweeper;

import java.awt.Color;
import javax.swing.JFrame;

import minesweeper.component.MenuComponent;
import minesweeper.component.PlayComponent;
import minesweeper.label.GameLabel;
import minesweeper.label.GameTitle;
import minesweeper.label.GameTime;

public class Minesweeper extends JFrame{
	private static final long serialVersionUID = 3282255206L;	
	
	private static GameLabel header = null;
	private PlayComponent bGUI;
	private MenuComponent cdGUI;

	public Minesweeper(int x, int y, int boomNum) {
		getContentPane().setBackground(Color.darkGray);
		setTitle("Minesweeper");
	
		bGUI = new PlayComponent(x, y, boomNum);
		bGUI.setBounds(0, 0, bGUI.getWidth(), bGUI.getHeight());
		add(bGUI);
		
		cdGUI = new MenuComponent(x, y, boomNum);
		cdGUI.setBounds(bGUI.getWidth(),0,400,500);
		if(x * y != 0) {
			header = new GameTime();
		}
		else {
			header = new GameTitle();
		}
		cdGUI.add(header);
		add(cdGUI);
		
		setSize(bGUI.getWidth() + 400, bGUI.getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);  
		setVisible(true);
	}
	
	public static void TimeStart() {
		new Thread((GameTime)header).start();
	}
	
	public static void main(String args[]) {
		 new Minesweeper(0, 0, 0);
	}
}
