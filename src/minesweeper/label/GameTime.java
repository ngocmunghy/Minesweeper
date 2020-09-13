package minesweeper.label;

import minesweeper.component.PlayComponent;

public class GameTime extends GameLabel implements Runnable {
	private static final long serialVersionUID = 3282255206L;
	
	private int current;
	
	public GameTime() {
		super(" 0 : 00");
		current = 0;
		setBounds(100, 25, 200, 50);
	}

	@Override
	public void run() {
		while(PlayComponent.GameStatus == 0) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			current++;
			int min = current / 60;
			int sec = current - min * 60;
			String s = " ";
			s += (min%10) + " : ";
			if(sec < 10) s += "0";s+= sec; 
			setText(s);
		}
	}
}
