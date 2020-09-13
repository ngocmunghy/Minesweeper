package minesweeper.boardgame;

import java.util.Random;

import minesweeper.Minesweeper;

public class GameBoard {
	private int board[][];
	private int status[][];
	private int row;
	private int column;
	private int boom;
	private int opened;
	
	public GameBoard(int x, int y,  int z) {
		row = x;
		column = y;
		opened = 0;
		board = new int[row][column];
		status = new int[row][column];
		boom = z;
	}
	
	public void open(int x, int y) {
		if(opened == 0) {
			firstClick(x, y);
		}
		
		if(status[x][y] == 0) {
			status[x][y] = 2;
			opened++;
		}
	}
	
	private void firstClick(int x, int y) {	
		Minesweeper.TimeStart();
		
		for(int i = x - 1; i < x + 2; i++) {
			if(i >= 0 && i < row) {
				for(int j = y - 1; j < y + 2; j++) {
					if(j >=0 && j < column) {
						board[i][j] = 9;
					}
				}
			}
		}
		
		for(int k = 0; k < boom; k++) {
			createBoom();
		}
		
		for(int i = 0; i < row; i++) {
			for(int j = 0; j < column; j++) {
				findBoom(i, j);
			}
		}	
	}
	
	private void createBoom() {
		Random rnd = new Random();
		int i = rnd.nextInt() % row;
		int j = rnd.nextInt() % column;
		i = Math.abs(i);
		j = Math.abs(j);
		if(board[i][j] != 0) {
			createBoom();
		}else {
			board[i][j] = -1;
		}
	}
	
	private void findBoom(int x, int y) {
		if(board[x][y] == -1) return;
		int count = 0;
		for(int i = x - 1; i < x + 2; i++) {
			if(i >= 0 && i < row) {
				for(int j = y - 1; j < y + 2; j++) {
					if(j >=0 && j < column) {
						if(board[i][j] == -1) count++;
					}
				}
			}
		}
		board[x][y] = count;
	}
	
	public void flag(int x, int y) {
		switch(status[x][y]) {
		case 0: status[x][y] = 1; break;
		case 1: status[x][y] = 0; break;
		default: break;
		}
	}

	public int getBoard(int x, int y) {
		return board[x][y];
	}

	public int getStatus(int x, int y) {
		return status[x][y];
	}

	public int getRow() {
		return row;
	}

	public int getColumn() {
		return column;
	}

	public int getBoom() {
		return boom;
	}

	public boolean checkWin() {
		return row * column == opened + boom;
	}
	
}
