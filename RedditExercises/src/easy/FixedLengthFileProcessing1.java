package easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * [2017-11-06] Challenge #339 [Easy] Fixed-length file processing
 * https://www.reddit.com/r/dailyprogrammer/comments/7b5u96/20171106_challenge_339_easy_fixedlength_file/
 * @author oVoISheRe
 *
 */

public class FixedLengthFileProcessing1 {

	private static int salary = 0;
	private static String person;
	private static String[] pair;
	
	public static void main(String[] args) {

		processFile("C:/Users/oVoISheRe/OneDrive/test files/fixedLengthFileProcessing/EmployeeRecords.txt");
		String value = NumberFormat.getCurrencyInstance(Locale.US).format(salary);
		System.out.println(person + ", " + value);
	}

	public static void processFile(String s) {
		
		try(Scanner in = new Scanner(new File(s))) {
			while(in.hasNext()) {
				String a = in.nextLine();
				// if line starts with name, allocate new array
				if(a.charAt(0) != ':') {
					pair = new String[2];
					pair[0] = a.substring(0, 20);
				// else add element to already existing array
				} else {
					// if ::EXT::JOB line is encountered, we can add it and proceed having name and salary in the array, 
					// if there are other ::EXT lines they won't be added to the array and processLine method won't be called 
					// (no more if-else branch); these lines are omitted;
					// it allocates unnecessary arrays as well (i.e. if no ::EXT::SAL exists it allocates array only with person too)
					if(a.substring(0, 10).equals("::EXT::SAL")) {
						pair[1] = a.substring(11);
						processLine(pair);				
					}				
				}
			} 
		} catch (FileNotFoundException e) {
				e.printStackTrace();
		}
	}	
	
	public static void processLine(String[] pairs) {

		if(Integer.parseInt(pairs[1]) > salary)  {
			salary = Integer.parseInt(pairs[1]);
			person = pairs[0].trim();
		}
	}	
}
	
