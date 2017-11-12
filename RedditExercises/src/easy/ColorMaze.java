package easy;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * [2017-08-01] Challenge #325 [Easy] Color maze
 * https://www.reddit.com/r/dailyprogrammer/comments/6qutez/20170801_challenge_325_easy_color_maze/
 * letters sequence 1: private static final String[] sequence = {"O", "G"};
 * letters sequence 2: private static final String[] sequence = {"R", "O", "Y", "P", "O"};
 * This piece of code is crappy and needs improvements.
 * This version doesn't work with first sequence, it needs changes; no time to check it out.
 * 
 * @author dream-tree
 */

public class ColorMaze {
	
	private static final String[] sequence = {"R", "O", "Y", "P", "O"}; 
	private static int sequenceIndex = 0;
	private int count = 0; 
	private String[][] grid;
	private List<Pairs> pathCoordinates = new ArrayList<>();
	// badNodes marks node with no connection to seeking letter;
	// badNode capacity is 1 and it is cleared shortly after checking node by checkBadNodes() method in each else-if branch 
	// in seekPath() method to avoid leaving node marked as "bad" when we look for ANOTHER letter in other/next turn(s) 
	private Queue<Pairs> badNodes = new ArrayBlockingQueue<Pairs>(1);
	private int row;  
	private int col = 0;
		
	public ColorMaze(int size) {
		grid = new String[size][size];
		row = size - 1;
	}
	
	public static void main(String[] args) {

		ColorMaze maze = new ColorMaze(20);   // 5 or 20
		String filePath = "C:/Users/oVoISheRe/OneDrive/test files/colorMaze/large.txt";    // or letters.txt
		maze.readTextFile(filePath);		
		maze.start();			
	}

	public void readTextFile(String s) {
		Path path = Paths.get(s);
		List<String> list = new ArrayList<>();
		try {
			list = Files.readAllLines(path);
		} catch (IOException e) {
			System.out.println("Something went wrong. Printing stack trace:");
			e.printStackTrace();
		}
		list
			.stream()
			.forEach(t -> processLine(t));	
	}
	
	public void processLine(String s) {
		try(Scanner in = new Scanner(s)) {
			int i = 0;
			while(in.hasNext()) {
				grid[count][i] = in.next();
				i++;
			}
		}
		count++;	
		if(count==grid.length)
			print();
	}
	
	public void print() {
		for(int i = 0; i < grid.length; i++) {
			System.out.print("Printing array: ");
			for(int j = 0; j < grid.length; j++) {
				System.out.print(grid[i][j] + " ");		
			}
			System.out.println();
		}
		System.out.println();
	}
	
	public void changeLetters() {
		for(Pairs x : pathCoordinates) {
			int a = x.getRow();
			int b = x.getCol();
			grid[a][b] = ":";
		}
		print();
	}
	
	public void start() {
		outer:
		for(int i = grid.length-1; i >= 0; i--) {
			for(int j = 1; j < grid.length-1; j++) {
				if(grid[i][j].equals(sequence[0])) {	
					pathCoordinates.add(new Pairs(i, j));
					row = i;
					col = j;		
					String temp = changeSeekingLetter();
					seekPath(temp);
					break outer;  
				} 			
			}		
		}
	}	
	
	public void seekPath(String seekingLetter) {
	//	System.out.println("seeking letter: " + sequence[index]);
	//	System.out.println(row + " " + col + ", index:" + index);
	//	System.out.println("grid[row-1][col]: " + grid[row-1][col]);
		if(row == 0) {
			for(Pairs a : pathCoordinates) {
				System.out.print(a + " ");
			}
			System.out.println();
			changeLetters();
		} else if(row-1 < grid.length && grid[row-1][col].equals(seekingLetter) && checkBadNodes(row-1, col)) {         
		//	System.out.println("up");
			pathCoordinates.add(new Pairs(row-1, col));   
			row-=1;
			seekPath(changeSeekingLetter());	
		} else if(col-1 > 0 && grid[row][col-1].equals(seekingLetter) && checkBadNodes(row, col-1)) {   
		//	System.out.println("left");
			pathCoordinates.add(new Pairs(row, col-1));           
			col-=1;
			seekPath(changeSeekingLetter());					
		} else if(col+1 < grid.length && grid[row][col+1].equals(seekingLetter) && checkBadNodes(row, col+1)) {
		//	System.out.println("right");
			pathCoordinates.add(new Pairs(row, col+1));
			col+=1;                 
			seekPath(changeSeekingLetter());
		} else if(row+1 < grid.length && grid[row+1][col].equals(seekingLetter) && checkBadNodes(row+1, col)) {   
		//	System.out.println("down");
			pathCoordinates.add(new Pairs(row+1, col));    
			row+=1;
			seekPath(changeSeekingLetter()); 			
		} else {
			// if dead end, go back to previous letter and try again; mark that node as a bad node by
			// adding it to separate collection to avoid checking it again; this is diff use of Pairs class
			Pairs tmp = new Pairs(row, col);
			badNodes.poll(); 
			badNodes.add(tmp);
    		System.out.println("badNode: " + tmp);
		//	System.out.println("all badNodes: " + badNodes);
			// removing bad node from final path
			pathCoordinates.remove(pathCoordinates.size()-1);
			// getting last good node to seek again
			Pairs lastGood = pathCoordinates.get(pathCoordinates.size()-1);
			row = lastGood.getRow();
			col = lastGood.getCol();
		//	System.out.println(row + " " + col + ", index:" + index);
			// go to previous letter (move back by 2 letters, 2 because changeSeekLetter() method move forth by 1 letter)
			if(sequenceIndex == 0) {
				sequenceIndex = sequence.length-3;
				seekPath(changeSeekingLetter());
			} else if(sequenceIndex == 1) {
				sequenceIndex = sequence.length-2;
				seekPath(changeSeekingLetter());	
			} else  {
				sequenceIndex = sequenceIndex - 2;
				seekPath(changeSeekingLetter());
			} 
		} 
	}
	// nice frag!
	public String changeSeekingLetter() {
		if(sequenceIndex == sequence.length-1) {
			sequenceIndex = 0;
			return sequence[sequenceIndex];
		} else
			return sequence[++sequenceIndex];
	}	
	
	public boolean checkBadNodes(int col, int row) {
		Pairs temp = new Pairs(col, row);
		for(Pairs x : badNodes) {
			if(x.equals(temp)) {
				badNodes.poll(); 
				return false;
			}
		}
		badNodes.poll();
		return true;
		}
	}

	class Pairs {
		int row;
		int col;

		public Pairs(int row, int col) {
			this.row = row;
			this.col = col;
		}
		
		public int getRow() {
			return row;
		}
		
		public int getCol() {
			return col;
		}
		
		@Override
		public String toString() {
			return row + ":" + col;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pairs other = (Pairs) obj;
			if (col != other.col)
				return false;
			if (row != other.row)
				return false;
			return true;
		}
}