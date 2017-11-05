package easy;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;


public class Temp {


	public static void main(String[] args) {
		int year = 1900;
		if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) {			
			 System.out.println(29);
		} else {
			 System.out.println(28);	
		}	
		
		
		
		
	}		
}