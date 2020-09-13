package minesweeper.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;

import minesweeper.boardgame.GameBoard;


public class PlayComponent extends JComponent {
	
	private static final long serialVersionUID = 3282255206L;
	
	public static int GameStatus;
	private int buttonSize;
	private GameBoard gameboard = null;
	private JButton jbutton[] = null;
	private ImageIcon num[] = new ImageIcon[11];
	
	public PlayComponent(int x, int y, int boomNum) {
		gameboard = new GameBoard(x, y, boomNum);
		jbutton = new JButton[x * y];
		
		if(x <= 20 && y <= 16) {
			buttonSize = 45;
		}
		else {
			buttonSize = 30;
		}
		createIcon();
		setBoardGame();

		int height = y * buttonSize + 40;
		setSize(gameboard.getRow() * buttonSize, height < 500 ? 500 : height);
	}
	
	private void createIcon() {
		for(int i = 0; i < 9; i++) {
			num[i] = new ImageIcon("src/image/num"+i+"_"+buttonSize+".png");
		}
		num[9] = new ImageIcon("src/image/flag_"+buttonSize+".png");
		num[10] = new ImageIcon("src/image/boom_"+buttonSize+".png");
	}
	
	private void setBoardGame() {
		GameStatus = 0;
		for(int i = 0; i < gameboard.getRow(); i++) {			
			for(int j = 0; j < gameboard.getColumn(); j++) {				
				createBoxButton(i, j);
			}
		}
	}	
	private void createBoxButton(int x, int y) {
		int index = x * gameboard.getColumn() + y;
		jbutton[index] = new JButton();
		jbutton[index].setBounds(buttonSize*x,buttonSize*y,buttonSize,buttonSize);
		jbutton[index].setBackground(Color.white);
		add(jbutton[index]);	
		jbutton[index].addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {							
			}
			public void mouseEntered(MouseEvent e) {
			}
			public void mouseExited(MouseEvent e) {	
			}
			public void mousePressed(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON3) {
					switch(gameboard.getStatus(x, y)) {
					case 0:
						gameboard.flag(x, y);
						jbutton[index].setIcon(num[9]);
						break;
					case 1:
						gameboard.flag(x, y);
						jbutton[index].setIcon(null);
						break;
					default: break;
					}
				}
			}
			public void mouseReleased(MouseEvent e) {
				if(e.getButton() == MouseEvent.BUTTON1) {
					if(gameboard.getStatus(x, y) != 2) {
						openButton(x, y);
					}
				}
			}
		});
	}
	
	private void openButton(int x, int y) {
		if(gameboard.getStatus(x, y)== 0) {
			gameboard.open(x, y);
			if(gameboard.getBoard(x, y)== -1) {
				jbutton[x*gameboard.getColumn()+y].setIcon(num[10]);
				gameOver(-1);	
			}
			else {
				jbutton[x*gameboard.getColumn()+y].setIcon(num[gameboard.getBoard(x, y)]);
				if(gameboard.checkWin()) {
					gameOver(1);
				}
			}
			
			if(gameboard.getBoard(x, y)== 0) {
				openAround(x, y);
			}
		}
	}
		
	private void gameOver(int status) {
		if(status == 1) {
			show(num[9]);
			showMessage("Congration! You Win!");
		}
		else if(status == -1){
			show(num[10]);
			showMessage("Game Over! You lose");
		}
		GameStatus = status;
	}
	
	private void showMessage(String string) {
		JFrame message = new JFrame();
		message.setTitle("Thong bao");
		message.setSize(300, 200);
		message.setLayout(null); 
		
		JLabel lbl = new JLabel(string);
		lbl.setFont(new Font("serif", Font.CENTER_BASELINE, 30));
		lbl.setBounds(0, 30, 290, 50);
		message.add(lbl);
		
		JButton OKbtn = new JButton("OK");
		OKbtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				message.dispose();
			}});
		OKbtn.setBounds(110,90,60,40);
		message.add(OKbtn);
		
		message.setVisible(true);
		message.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	private void show(ImageIcon icon) {
		for(int i = 0; i < gameboard.getRow(); i++) {			
			for(int j = 0; j < gameboard.getColumn(); j++) {
				if(gameboard.getBoard(i, j) == -1 && gameboard.getStatus(i, j) == 0) {
					jbutton[i*gameboard.getColumn()+j].setIcon(icon);
				}
				gameboard.open(i, j);
			}
		}
	}	
			
	private void openAround(int x, int y) {	
		for(int i = x - 1; i < x + 2; i++) {
			if(i >= 0 && i < gameboard.getRow()) {
				for(int j = y - 1; j < y + 2; j++) {
					if(j >=0 && j < gameboard.getColumn()) {
						openButton(i, j);
					}
				}
			}
		}
	}
}
