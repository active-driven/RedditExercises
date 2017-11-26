package easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * [2017-11-21] Challenge #341 [Easy] Repeating Numbers
 * https://www.reddit.com/r/dailyprogrammer/comments/7eh6k8/20171121_challenge_341_easy_repeating_numbers/
 * input: 82156821568221   
 * 		  output: 8215682:2 821568:2 215682:2 82156:2 21568:2 15682:2 8215:2 2156:2 1568:2 5682:2 821:2 215:2 156:2 
 * 		          568:2 682:2 82:3 21:3 15:2 56:2 68:2
 * input: 11111011110111011
 * 		  output: 11110111:2 1111011:2 1110111:2 111101:2 111011:3 110111:2 11110:2 11101:3 11011:3 10111:2 1111:3 1110:3 
 * 			      1101:3 1011:3 0111:2 111:6 110:3 101:3 011:3 11:10 
 * input: 98778912332145
 * 		  output: 0
 * input: 124489903108444899
 * 		  output: 44899:2 4489:2 4899:2 448:2 489:2 899:2 44:3 48:2 89:2 99:2
 * 
 * @author oVoISheRe
 */

public class RepeatingNumbers {

	public static void main(String[] args) {
	
		// exercising here:
		String allDigits = "124489903108444899";   // orig: "11325992321982432123259";
		int[] allDigitsArray1 = Arrays.stream(allDigits.split("")).mapToInt(Integer::parseInt).toArray();
		int[] allDigitsArray2 = new int[allDigits.length()]; 
		for(int i = 0; i < allDigits.length(); i++) {
			allDigitsArray2[i] = Integer.parseInt(String.valueOf(allDigits.charAt(i)));
		}
		int size = allDigits.length();
		
		System.out.println("alldigits: " + allDigits + "\nallDigitsArray1: " + Arrays.toString(allDigitsArray1) + 
				"\nallDigitsArray2: " + Arrays.toString(allDigitsArray2) + "\nsize: " + size + "\nmaxSeries: " + size/2);
		// end of exercising
		
		Map<String, Integer> map = new HashMap<>();
		// maximum sequence length
		int maxSeries = allDigits.length()-1;
		for(int sequenceSize = 2; sequenceSize <= maxSeries; sequenceSize++) {
			for(int i = 0; i < size-sequenceSize; i++) {
				String temp = allDigits.substring(i, i+sequenceSize);
				// avoids checking the same sequence again if this sequence repeats
				if(map.containsKey(temp)) {
					continue;
				}
				int count = 1;
				for(int j = i; j < size-sequenceSize; j++) {			
					if(temp.equals(allDigits.substring(j+1, j+1+sequenceSize))) {						
						count++;
					}	
				}
				if(count!=1) {
					map.put(allDigits.substring(i, i+sequenceSize), count);
				}				
			}	
		}		
		if(map.size() == 0) {
			System.out.println(0);
		} else {
			for(String s : map.keySet()) {	
			System.out.println(s + ":" + map.get(s));
			}	
		}	
	}
}
