package easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * [2017-10-30] Challenge #338 [Easy] What day was it again?
 * https://www.reddit.com/r/dailyprogrammer/comments/79npf9/20171030_challenge_338_easy_what_day_was_it_again/
 * This solution is based on counting number of days from certain point in time called EPOCH (here: 1.1.0) to given date.
 * This solution does not use languages built in Calendar functions/classes except for 2 cases:
 * 	- to set off the day of week of certain point in time (EPOCH)
 *  - to check out if the answer is correct
 * This solution does not use Zeller's congruence/algorithm.
 * 
 * @author oVoISheRe
 */

public class WhatDayWasItAgain {

	public static final DayOfWeek TODAY = LocalDate.now().getDayOfWeek();
	public static final DayOfWeek EPOCH = LocalDate.of(0,01,01).getDayOfWeek();
	public static final int YEAR_EPOCH = 0;
	
		public static void main(String[] args) {
      
			WhatDayWasItAgain.samples();
		
			WhatDayWasItAgain.processLineByLine("C:/Users/oVoISheRe/OneDrive/test files/whatDayWasItAgain/datesall.txt");

		}	
		/**
		 * Reads .txt file containing dates to check.
		 * @param path path to .txt file.
		 */
		public static void processLineByLine(String path) {
			try(Scanner in = new Scanner(new File(path))) {
				while(in.hasNextLine()) {
					processLine(in.nextLine());			
				}
			} catch(FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		/**
		 * Reads single line from .txt file converting it to year, month and day,
		 * and passes it to methods counting number of days from EPOCH day (here: 1.1.0).
		 * @param s single line from .txt file, i.e. single date to check.
		 */
		public static void processLine(String s) {
			System.out.println(s);
			Scanner in = new Scanner(s);
			int yearInput = in.nextInt();
			int monthInput = in.nextInt();
			int dayInput = in.nextInt();
			System.out.println("year: " + yearInput + ", month: " + monthInput + ", day: " + dayInput);
			in.close();
			
			DayOfWeek check = LocalDate.of(yearInput,monthInput,dayInput).getDayOfWeek();
			System.out.println("API check: " + check);
			
			// sum of days total from EPOCH day to input date inclusive
			int daysTotal = 0;
					
			if(yearInput == YEAR_EPOCH) {
				daysTotal = countDaysOfBorderYear(yearInput, monthInput, dayInput);
				System.out.println("Total number of days: " + daysTotal);
			}
			if(yearInput > YEAR_EPOCH) {
				daysTotal = countDaysBetweenYears(yearInput) + countDaysOfBorderYear(yearInput, monthInput, dayInput);
				System.out.println("Total number of days: " + daysTotal);
			}	
			checkDayOfWeek(daysTotal);				
		}
		
		/**
		 * Counts number of days in February of input year.
		 * @param yearInput year to check the number of days in February
		 * @return number of days in February of input year
		 */
		public static int countFebruaryDays(int year) {
			if((year % 4 == 0 && year % 100 != 0 ) || year % 400 == 0) {			
				return 29;
			} else {
				return 28;	
			}	
		}
	
		/**
		 * Counts days between EPOCH year (inclusive) and border year (exclusive)
		 * @param yearInput border year of counting days 
		 * @return number of days between EPOCH year (inclusive) and border year (exclusive)
		 */
		public static int countDaysBetweenYears(int year) {
			
			int daysCount = 0;  
			for(int i = YEAR_EPOCH; i < year; i++) {
				if((i % 4 == 0 && i % 100 != 0 ) || i % 400 == 0) {			
					daysCount+=366;
				} else {
					daysCount+=365;
				}	
			}
			return daysCount;
		}
		/**
		 * Counts number of days in this single input year. 
		 * @param yearInput input year
		 * @param monthInput input month
		 * @param dayInput input day
		 * @return number of days in this single input year
		 */
		
		public static int countDaysOfBorderYear(int yearInput, int monthInput, int dayInput) {
			
			int borderYearNumberOfDays = 0;	
			switch(monthInput) {
			case 1: borderYearNumberOfDays = dayInput;
				break;
			case 2: borderYearNumberOfDays = 31 + dayInput;
				break;
			case 3: borderYearNumberOfDays = 31+countFebruaryDays(yearInput) + dayInput; 
				break;
			case 4: borderYearNumberOfDays = 62+countFebruaryDays(yearInput) + dayInput;
				break;
			case 5: borderYearNumberOfDays = 92+countFebruaryDays(yearInput) + dayInput;
				break;
			case 6: borderYearNumberOfDays = 123+countFebruaryDays(yearInput) + dayInput;
				break;
			case 7: borderYearNumberOfDays = 153+countFebruaryDays(yearInput) + dayInput;
				break;
			case 8: borderYearNumberOfDays = 184+countFebruaryDays(yearInput) + dayInput;
				break;
			case 9: borderYearNumberOfDays = 215+countFebruaryDays(yearInput) + dayInput;
				break;
			case 10: borderYearNumberOfDays = 245+countFebruaryDays(yearInput) + dayInput;
				break;
			case 11: borderYearNumberOfDays = 276+countFebruaryDays(yearInput) + dayInput;
				break;
			case 12: borderYearNumberOfDays = 306+countFebruaryDays(yearInput) + dayInput;
				break;
			default: borderYearNumberOfDays = 0;
				break;	
			}
			return borderYearNumberOfDays;
		}
		/**
		 * Checks out what day was at the given date
		 * and prints the result.
		 * @param numberOfdays number of days between EPOCH year (inclusive) and border year (inclusive)
		 */
		public static void checkDayOfWeek(int numberOfdays) {
			
			int remains = numberOfdays % 7;
			DayOfWeek day;
			switch(remains) {
				case 1: day = DayOfWeek.SATURDAY;    // starting from Thursday because EPOCH day was on Saturday
					break;
				case 2: day = DayOfWeek.SUNDAY; 
					break;
				case 3: day = DayOfWeek.MONDAY; 
					break;
				case 4: day = DayOfWeek.TUESDAY; 
					break;
				case 5: day = DayOfWeek.WEDNESDAY; 
					break;
				case 6: day = DayOfWeek.THURSDAY; 
					break;
				case 0: day = DayOfWeek.FRIDAY; 
					break;
				default: day = null;
					break;
			}	
			System.out.println("Own check: " + day + "\n");
		}
		
		public static void samples() {
			System.out.println("----------------------\nSamples: ");
			System.out.println("Today: " + TODAY);
			System.out.println("Epoch: " + EPOCH);
			DayOfWeek day1 = LocalDate.now().getDayOfWeek();
			int day2 = LocalDate.now().getDayOfMonth();
			int day3 = LocalDate.now().getDayOfYear();
			System.out.printf("Day of week:  %s%n", day1);
			System.out.printf("Day of month: %d%n", day2);
			System.out.printf("Day of year:  %d%n", day3);
			
			DayOfWeek dayOfWeek = DayOfWeek.FRIDAY;
			System.out.println(dayOfWeek);
			Instant ins = Instant.EPOCH;
			System.out.println(ins);
			System.out.println(Instant.EPOCH);
			System.out.println(LocalDate.now());
			System.out.println(LocalDateTime.now());
			System.out.println("End of samples.\n----------------------");
		}	
	}