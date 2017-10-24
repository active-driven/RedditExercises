package easy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * [2017-10-09] Challenge #335 [Easy] Consecutive Distance Rating
 * https://www.reddit.com/r/dailyprogrammer/comments/759fha/20171009_challenge_335_easy_consecutive_distance/
 * Exercise does not precise we should count backward sequences as well.
 * 
 * @author dream-tree
 */

public class ConsecutiveDistanceRating {

	public static void main(String[] args) {

		Integer[] arr = readTextFile("C:/Users/oVoISheRe/OneDrive/test files/consecutive/consecutive6.txt");	
		System.out.println(Arrays.toString(arr));	
		ConsecutiveDistanceRating.printCounted(arr);
		
		int gap = 2;
		Integer[] arrBonus = readTextFile("C:/Users/oVoISheRe/OneDrive/test files/consecutive/consecutive6.txt");	
		System.out.println(Arrays.toString(arrBonus));	
		ConsecutiveDistanceRating.printCountedBonus(arrBonus, gap);
		
		List<Integer> list = readTextFile2("C:/Users/oVoISheRe/OneDrive/test files/consecutive/consecutive6.txt");	
		list
			.stream()
			.forEach(x -> System.out.print(x + ", "));
		ConsecutiveDistanceRating.printCountedBonus2(list, gap);
	}
	
	/**
	 * Reads input from a text file.
	 * [It is an exercise to convert list into an array].
	 * @param s path string
	 * @return array of Integers from a text file
	 */
	public static Integer[] readTextFile(String s) {
			
		// Files.readAllLines() requires List<String> so additional parsing is needed
		List<Integer> list = new ArrayList<>();
		
		try(Scanner in = new Scanner(new File(s))) {
			while(in.hasNext()) {
				list.add(in.nextInt());
			}
		} catch (IOException e) {
			System.err.println("Something went wrong.\nPrinting stack trace:");
			e.printStackTrace();
		}
				
		Integer[] arr = list.toArray(new Integer[0]);
		return arr;		
	}
	 
	/**
	 * Counts the sum of the distances between consecutive integers in the Integer array
	 * and prints the result to the console.
	 * Distance to count (size gap) is 1.
	 * @param arr array of Integers to perform counting 
	 */
	public static void printCounted(Integer[] arr) {
		
		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i]+1 == arr[j]) {               // alternate: if(arr[i]+1 == arr[j] || arr[i] - 1 == arr[j]) {
					count+=j-i;                        // there is no need for below 'for statements' then
				}
			}
		}	
//		System.out.println(count);
		
		for(int i = arr.length-1; i > 0; i--) {        
			for(int j = i-1; j >= 0; j--) {
				if(arr[i] == arr[j]-1) {
					count+=i-j;
				}
			}
		}		
		System.out.println(count);	
	}
	
	/**
	 * Counts the sum of the distances between consecutive integers in the Integer array
	 * and prints the result to the console.
	 * @param arr array of Integers to perform counting 
	 * @param gap distance to count (size gap) 
	 */
	public static void printCountedBonus(Integer[] arr, int gap) {

		int count = 0;
		for(int i = 0; i < arr.length; i++) {
			for(int j = i+1; j < arr.length; j++) {
				if(arr[i]+gap == arr[j]) {
					count+=j-i;
				}
			}
		}	
		
		for(int i = arr.length-1; i > 0; i--) {
			for(int j = i-1; j >= 0; j--) {
				if(arr[i] == arr[j]-gap) {
					count+=i-j;
				}
			}
		}		
		System.out.println(count);			
	}
	
	/**
	 * Reads input from a text file.
	 * [Operating on a list instead of an array].
	 * @param s path string
	 * @return List of Integers from a text file
	 */
	public static List<Integer> readTextFile2(String s) {
			
		List<Integer> list = new ArrayList<>();
		
		try(Scanner in = new Scanner(new File(s))) {
			while(in.hasNext()) {
				list.add(in.nextInt());
			}
		} catch (IOException e) {
			System.err.println("Something went wrong.\nPrinting stack trace:");
			e.printStackTrace();
		}				
		return list;		
	}
	
	/**
	 * Counts the sum of the distances between consecutive integers in the List<Integer>
	 * and prints the result to the console.
	 * [Operating on a list instead of an array].
	 * @param list List of Integers to perform counting 
	 * @param gap distance to count (size gap) 
	 */
	public static void printCountedBonus2(List<Integer> list, int gap) {

		int count = 0;
		for(int i = 0; i < list.size(); i++) {
			for(int j = i+1; j < list.size(); j++) {
				if(list.get(i)+gap == list.get(j) || (list.get(i)-gap == list.get(j))) {
					count+=j-i;  
				}
			}
		}	
		System.out.println("\n" + count);			
	}
}
