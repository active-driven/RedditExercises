package easy;

import java.util.Scanner;

/**
 * [2017-09-11] Challenge #331 [Easy] The Adding Calculator
 * https://www.reddit.com/r/dailyprogrammer/comments/6ze9z0/20170911_challenge_331_easy_the_adding_calculator/
 * Lack of time, so not done: decimal bonus, avoiding Math.abs(), exception handling.
 * 
 * @author dream-tree
 */

public class TheAddingCalculator {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		System.out.println("Enter first number: ");
		int first = in.nextInt();
		System.out.println("Enter sign (+ or - or * or / or ^)");
		String sign = in.next();
		System.out.println("Enter first number: ");
		int second = in.nextInt();
		System.out.println("Your question to solve is: " + first + " " + sign + " " + second);
		if(sign.equals("+")) {
			int addition = first + second;
			System.out.println("And the answer is " + addition + ".");
		}
		if(sign.equals("*")) {
			multiply(first, second);
		}	
		if(sign.equals("/")) {
			divide(first, second);
		}
		if(sign.equals("-")) {
			subtract(first, second);
		}
		if(sign.equals("^")) {
			System.out.println("Check: " + Math.round(Math.pow(first, second)));
			exponents(first, second, first, 0, 1);
		}
		in.close();
	}
	
	/**
	 * Prints result of multiplication (product).
	 * @param first multiplier 
	 * @param second multiplicand
	 */
	public static void multiply(int first, int second) {
		int total = 0;
		for(int i = 0; i < Math.abs(first); i++) {
			total+=second;
		}
		if(first < 0) {
			// we can't use unary operator "-" as in -total so negate() method implemented
			System.out.println("And the answer is " + negate(total) + ".");
		} else {
			System.out.println("And the answer is " + total + ".");
		}	
	}
	
	/**
	 * Prints result of division (quotient).
	 * @param first dividend
	 * @param second divisor 
	 */
	public static void divide(int first, int second) {
		int firstAbs = Math.abs(first);
		int secondAbs = Math.abs(second);
		int temp = secondAbs;
		int count = 1;
		if(second == 0) {
			System.out.println("Not-defined.");
			return;
		}
		if(secondAbs > firstAbs) {
			if(firstAbs == 0) {
				System.out.println(0);
			} else {
			System.out.println("Non-integral answer.");
			return;
			}
		}
		while(temp <= firstAbs) {
			// division is possible and two minuses gives positive integer
			if(temp == firstAbs && first < 0 && second < 0) {
				System.out.println("And the answer is " + count + ".");
				break;
			}
			// division is possible and one minus and one plus gives non-positive integer
			if(temp == firstAbs && (first < 0 || second < 0)) {
				System.out.println("And the answer is " + negate(count) + ".");
				break;
			}
			// division is possible, no sign change
			if(temp == firstAbs) {
				System.out.println("And the answer is " + count + ".");
				break;
			}
			temp+=secondAbs;
			count++;
		}
		// division impossible
		if(temp > firstAbs) {
			System.out.println("Non-integral answer.");
			return;
		}
	}
		
	/**
	 * Prints result of subtraction (difference).
	 * @param first minuend
	 * @param second subtrahend
	 */	
	public static void subtract(int first, int second) {
		// flag variable tracks if second is greater than first to change ultimate result sign due to second/first positions change
		boolean flag = false;
		if(second > first) {
			int temp = first;
			first = second;
			second = temp;
			flag = true;
		}
		int count = 0;
		while(second < first) {
			second++;
			count++;
		}
		if(flag == true) {
			System.out.println("And the answer is " + negate(count) + ".");
		} else {
			System.out.println("And the answer is " + count + ".");
		}
	}	
	/**
	 * Prints result of exponentiation.
	 * @param changingBase original base changing in each step of calculation
	 * @param exponent exponent
	 * @param finalBase non-changing original base as typed by user
	 * @param total counts current exponentiation calculation; if base case is not fulfilled - total is reset to zero
	 * @param flag tracks exponent; we could use second--; expression instead to avoid this param but only increment unary operator is allowed
	 */	
	public static void exponents(int changingBase, int exponent, int finalBase, int total, int flag) {
		// sample: 3^4 = 3*3*3*3 = 9*3*3 etc.

		// special case: exponent < 0 as in 5 ^ 0: 
		if(exponent < 0) {
			System.out.println("Non-integral answer.");
			return;
		}
		// special case: base == 0
		if(changingBase == 0) {
			System.out.println("And the answer is " + 0 + ".");
			return;
		}
		// special case: exponent == 0
		if(exponent == 0) {
			System.out.println("And the answer is " + 1 + ".");
			return;
		}	
		// base case:
		if(flag == exponent) {
			// special case: -1 ^ 1
			if(total == 0 && changingBase == negate(1)) {
				System.out.println("And the answer is " + negate(1) + ".");
				return;
			}		
			// special case: 1 ^ 1
			if(total == 0) {
				System.out.println("And the answer is " + 1 + ".");
				return;
			}
			// standard case
			System.out.println("And the answer is " + total + ".");
			return;
		}		
		total = 0;
		for(int i = 0; i < Math.abs(changingBase); i++) {
			total+=finalBase;
		}
		changingBase = total;
		flag++;
		
		exponents(changingBase, exponent, finalBase, total, flag);
		}	
	
	/**
	 * Negates given positive or negative number as we can't use unary operator "-".
	 * @param number number to negate - to change from positive to negative or from negative to positive
	 * @return number number changed from positive to negative or from negative to positive
	 */
	public static int negate(int number) {
		// the result of Integer.MIN_VALUE + Integer.MAX_VALUE calculation is -1
		int minusOne = Integer.MIN_VALUE + Integer.MAX_VALUE;
		int total = 0;
		if(number > 0) {
			for(int i = 0; i < number; i++) {
				total+=minusOne;
			}
		} else {
			for(int i = 0; i > number; i+=minusOne) {   // i-- not allowed
				total+=1;
			}
		}			
		return total;
	}
		
	/**
	 * Negates given positive number as we can't use unary operator "-".
	 * Alternative version. Much slower version.
	 * @param number number to negate - to change from positive to negative
	 * @return number number changed from positive to negative
	 */
	public static int negateAlt(int number) {
		int minValue = Integer.MIN_VALUE;
		// looking for complement value
		while(minValue + number != 0) {
			minValue++;
		}
		return minValue;
	}
}