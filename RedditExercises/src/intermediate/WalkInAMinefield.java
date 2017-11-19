package intermediate;

import java.awt.Point;
import java.util.Random;
import java.util.Scanner;

/**
 * [2017-11-14] Challenge #340 [Intermediate] Walk in a Minefield
 * https://www.reddit.com/r/dailyprogrammer/comments/7d4yoe/20171114_challenge_340_intermediate_walk_in_a/
 * Original grid is 11x8 and the moving area is 9x6 (except of enter and exit fields).
 * 
 * @author oVoISheRe
 */
public class WalkInAMinefield {

	// starting node coordinates
	int x = 6; 
	int y = 0;
	private char[][] grid;
	// grid size
	private int horizontal = 11;
	private int vertical = 8;
	Point[] mines;
	
	public WalkInAMinefield(int userNumberOfMines) { 
		Random random = new Random();
		mines = new Point[userNumberOfMines];
		for(int i = 0; i < mines.length; i++) {
			int a = random.nextInt(6)+1;
			int b = random.nextInt(9)+1;
			// avoid placing mines in front of the entrance and exit
			if(a == 6 && b == 1 || a == 1 && b == horizontal-2) {
				i-=1;		
				continue;			
			} else {
				mines[i] = new Point(a, b);	
			}				
		}
		this.grid = new char[vertical][horizontal];        
		for(int i = 0; i < this.vertical; i++) {          
			for(int j = 0; j < this.horizontal; j++) {	   
				// starting point
				if(i == vertical-2 && j == 0) {
					grid[i][j] = 'M';
				// exit node
				} else if(i == 1 && j == horizontal-1) {
					grid[i][j] = '0';
				// grid borders
				} else if(i == 0 || j == 0 || j == horizontal-1 || i == vertical-1) {
					grid[i][j] = '+';
				// filling grid with zeros
				} else {
					grid[i][j] = '0';				
				}			
			}
		}		
		for(int i = 0; i < userNumberOfMines; i++) {
			int a = (int)mines[i].getX();
			int b = (int)mines[i].getY();
			grid[a][b] = '*';
		}
		printGrid();
	}
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("Enter number of mines: ");
		int numberOfMines = in.nextInt();
		WalkInAMinefield game = new WalkInAMinefield(numberOfMines);
		try {
			Thread.sleep(50);   // used for printing grid&mine message in proper order
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.go();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		game.printGrid();

	}
	
	public void go() {
		System.out.println();
		String s = "IENENNNNEEEEEEEE-";
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == 'I') {
				continue;
			} else if(s.charAt(i) == 'E') {
				y+=1;
			} else if(s.charAt(i) == 'W') {
				y-=1;
			} else if(s.charAt(i) == 'N') {
				x-=1;
			} else if(s.charAt(i) == 'S') {
				x+=1;
			} else if(s.charAt(i) == '-') {
				break;
			} else {
				continue;
			}
			if(checkMineNodes()) {
				return;
			} else {
				grid[x][y] = ':';
			}
		}
	}
	
	public boolean checkMineNodes() {
		if(grid[x][y] == '*') {
			System.err.println("Mine!!! Detonation!!! Game over!!!");
			return true;
		}		
		return false;
	}
	
	public void printGrid() {
		for(int i = 0; i < this.vertical; i++) {
			for(int j = 0; j < this.horizontal; j++) {
				System.out.print(grid[i][j]);
			}
			System.out.println("");
		}
	}
}

