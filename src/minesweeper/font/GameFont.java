package minesweeper.font;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;

public class GameFont extends Font{
	private static final long serialVersionUID = 3282255206L;

	public GameFont() {
		super(createFont().getName(), Font.PLAIN, 50);
	}

	private static Font createFont() {
		Font font = null;
		try {
			font = Font.createFont(Font.TRUETYPE_FONT, new File("src/minesweeper/font/Orbitron-VariableFont_wght.ttf"));
		} catch (FontFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return font;
	}
}
