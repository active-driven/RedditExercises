package intermediate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 * [2017-11-08] Challenge #339 [Intermediate] A car renting problem
 * https://www.reddit.com/r/dailyprogrammer/comments/7btzrw/20171108_challenge_339_intermediate_a_car_renting/
 * Exercising few different things here.
 * 
 * @author dream-tree
 */

public class CarRentingProblem {
	
	private static int count = 0;
	private static List<TwoIntPair> pairs = createList();
	private static List<TwoIntPair> bestPairs = new ArrayList<>();
	
	public static void main(String[] args) {
	
		processFile("C:/Users/oVoISheRe/OneDrive/test files/carRenting/car2.txt");

		// first element in the list is the one with the lowest y value (after sorting)
		find(pairs.get(0));
		print(bestPairs);		
	}
	
	
	public static List<TwoIntPair> createList() {
		pairs = new ArrayList<>(10);
		for(int i = 0; i < 10; i++) {
			pairs.add(new TwoIntPair(0, 0));
		}
		return pairs;
	}
	
	public static void processFile(String filePath) {		
		try(Scanner in = new Scanner(new File(filePath))) {
			while(in.hasNextLine()) {
				String s = in.nextLine();
				try(Scanner scan = new Scanner(s)) {
					if(count < 10) {
						while(scan.hasNext()) {
							int x = scan.nextInt();
							TwoIntPair temp = pairs.get(count);
							temp.setX(x);
							count++;
						}
					} else {
						count = 0;
						while(scan.hasNext()) {
							int y = scan.nextInt();
							TwoIntPair temp = pairs.get(count);
							temp.setY(y);
							count++;
						}
					}
				}
			}
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		print(pairs);
//      my own sorting version:
		insertionSort();
//   	List interaface sort() method:
		pairs.sort((x, y) -> x.getY() - y.getY());
		print(pairs);
//		List interaface sort() method using Comparator.comparingInt:
		pairs.sort(Comparator.comparingInt(x -> x.getY()));
		print(pairs);
	}
	
	public static void print(List<TwoIntPair> list) {
		System.out.println();
		list
			.stream()
			.forEach(x -> System.out.println(x));
	}
	
	public static void insertionSort() {
		for(int i = 1; i < 10; i++) {
			for(int j = i; j > 0 && pairs.get(j).getY() < pairs.get(j-1).getY(); j--) {   // or .getX() to sort by x
				TwoIntPair temp1 = pairs.get(j);
				TwoIntPair temp2 = pairs.get(j-1);
				pairs.set(j, temp2);
				pairs.set(j-1, temp1);
			}
		}
		print(pairs);
	}
	
	public static void find(TwoIntPair lowestY) {
		if(pairs.size() == 1) {
			bestPairs.add(lowestY);
			return;
		} else {
			for(Iterator<TwoIntPair> it = pairs.iterator(); it.hasNext(); ) {
				int x = it.next().getX();
				if(x < lowestY.getY()) {
					it.remove();	
				}
			}
			bestPairs.add(lowestY);
			pairs.remove(lowestY);
			find(pairs.get(0));	
		}
	}	
}	
	 class TwoIntPair {
		
		private int x;
		private int y;
		
		public TwoIntPair(int min, int max) {  
			this.x = min;
			this.y = max;
		}
		
		public int getX() {
			return x;
		}
		
		public int getY() {
			return y;
		}	
		
		public void setX(int x) {
			this.x = x;
		}
		
		public void setY(int y) {
			this.y = y;
		}
		
		@Override
		public String toString() {
			return String.format("x: %2s, y: %3s", x, y);
		}
	}