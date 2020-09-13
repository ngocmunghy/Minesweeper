package minesweeper.component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import minesweeper.Minesweeper;

public class MenuComponent extends JComponent {
	private static final long serialVersionUID = 3282255206L;
	
	private JButton resetGame = new JButton();
	private JButton easyGame = new JButton();
	private JButton normalGame = new JButton();
	private JButton hardGame = new JButton();
	private JButton customGame = new JButton();
	
	public MenuComponent(int x, int y, int boomNum) {
		if(x * y != 0) {
			setResetGameButton(x, y, boomNum);
		}
		setEasyGameButton();
		setNormalGameButton();
		setHardGameButton();
		setCustomGameButton(x, y, boomNum);
		
		setSize(400,500);
	}
	
	private void setResetGameButton(int x, int y, int boomNum) {
		resetGame.setBounds(100, 100, 200, 50);
		resetGame.setText("Reset Game");
		resetGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Minesweeper(x, y, boomNum);
				((Minesweeper) SwingUtilities.getWindowAncestor(MenuComponent.this)).dispose();
			}
		});
		add(resetGame);
	}
	
	private void setEasyGameButton() {
		easyGame.setBounds(100, 175, 200, 50);
		easyGame.setText("Easy");
		easyGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Minesweeper(9, 9, 10);
				((Minesweeper) SwingUtilities.getWindowAncestor(MenuComponent.this)).dispose();
			}	
		});
		add(easyGame);
	}
	
	private void setNormalGameButton() {
		normalGame.setBounds(100, 250, 200, 50);
		normalGame.setText("Normal");
		normalGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Minesweeper(16, 16, 40);
				((Minesweeper) SwingUtilities.getWindowAncestor(MenuComponent.this)).dispose();
			}	
		});
		add(normalGame);
	}
	
	private void setHardGameButton() {
		hardGame.setBounds(100, 325, 200, 50);
		hardGame.setText("Hard");
		hardGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Minesweeper(30, 16, 99);
				((Minesweeper) SwingUtilities.getWindowAncestor(MenuComponent.this)).dispose();
			}	
		});
		add(hardGame);
	}
	
	private void setCustomGameButton(int x, int y, int boomNum) {
		customGame.setBounds(100, 400, 200, 50);
		customGame.setText("Custom Game");
		customGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int r, b, c;
				try {  
					r = Integer.parseInt(JOptionPane.showInputDialog("Input number of row"));
				  } catch(NumberFormatException e){  
				    r = x;
				  } 
				if(0 >= r || r > 30) {
					r = x;
				}
				
				try {  
					c = Integer.parseInt(JOptionPane.showInputDialog("Input number of column"));
				  } catch(NumberFormatException e){  
				    c = y;
				  } 
				if(0 >= c || c > 24) {
					c = y;
				}
				
				try {  
					b = Integer.parseInt(JOptionPane.showInputDialog("Input number of boom"));
				  } catch(NumberFormatException e){  
				    b = boomNum;
				  } 
				if(0 >= b || b > r * c - 9) {
					b = r * c / 6;
				}
				
				new Minesweeper(r, c, b);
				((Minesweeper) SwingUtilities.getWindowAncestor(MenuComponent.this)).dispose();
			}	
		});
		add(customGame);
	}
}
