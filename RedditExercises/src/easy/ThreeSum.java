package easy;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * [2017-07-10] Challenge #323 [Easy] 3SUM
 * https://www.reddit.com/r/dailyprogrammer/comments/6melen/20170710_challenge_323_easy_3sum/
 * 
 * @author dream-tree
 */

public class ThreeSum {

	public static void main(String[] args) {

		readNumbers("C:/Users/oVoISheRe/OneDrive/test files/3SUM/3SUM1.txt");
		
		
	}

	public static void readNumbers(String s) {
		
		Scanner in = null;
		try {
			in = new Scanner(new File(s));			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
//		String[] arr1 = in.nextLine().split(" ");
//		System.out.println("arr1: " + Arrays.toString(arr1));
		
//		int[] arr2 = Arrays.stream(in.nextLine().split(" "))
//								.mapToInt(Integer::parseInt)
//								.sorted()
//								.toArray();
//		System.out.println("arr2: " + Arrays.toString(arr2));
		
		Path path = Paths.get(s);
		List<String> list = new ArrayList<>();
		try {
			list = Files.readAllLines(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int[] arr3 = Arrays.stream(list.get(0).split(" "))
								.mapToInt(Integer::parseInt)
								.sorted()
								.toArray();
		System.out.println("arr3: " + Arrays.toString(arr3));
		
		int[] temp = arr3;
		// {-25, -10, -7, -3, 2, 4, 8, 10};
		
		
		for(int i = 0; i < temp.length-3; i++) {
			int a = temp[i];
			int start = i+1;
			int end = temp.length-1;
			while(end>start) {
				int b = temp[start];
				int c = temp[end];
				if(a+b+c==0) {
					System.out.println(a + " " + temp[start] + " " + temp[end]);
				 // continues searching for all triplet combinations summing to zero	
					start = start + 1;   // or: end = end - 1;
				 // original wiki version:
					// if (b == temp[start+1]) {  // I think it avoids (most but not all) checking same combinations if there are 2 same 
					// 		start = start + 1;    // numbers next to each other; but really my ver works better but if always?
					//	} else {
					//		end = end - 1;
					//	}
				} else if(a+b+c>0) {
					end = end-1;
				} else {
					start = start+1;
				}
			}
		}
		
		
		

	}
	
}
