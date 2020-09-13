package minesweeper.label;

import java.awt.Color;
import javax.swing.JLabel;
import minesweeper.font.GameFont;

public class GameLabel extends JLabel {

	private static final long serialVersionUID = 3282255206L;
	
	GameFont Orbitron = new GameFont();
	
	public GameLabel(String arg0) {
		super(arg0);
		setFont(Orbitron);
		setBackground(Color.BLACK);
		setForeground(Color.RED);
		setOpaque(true);
	}
}
