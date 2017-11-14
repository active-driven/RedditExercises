package easy;

/**
 * [2017-11-13] Challenge #340 [Easy] First Recurring Character
 * https://www.reddit.com/r/dailyprogrammer/comments/7cnqtw/20171113_challenge_340_easy_first_recurring/
 * IKEUNFUVFV
 * PXLJOUDJVZGQHLBHGXIW
 * l1J?)yn%R[}9~1"=k7]9;0[$
 * 
 * @author oVoISheRe
 */

public class FirstRecurringCharacter {

	public static void main(String[] args) {

		String s = "*l1J?)yn%R[}9~1\"=k7]9;0[$";
		
		int[] all = new int[128];
		
		for(int i = 0; i < s.length(); i++) {
			if(all[s.charAt(i)] != 0) {
				System.out.println(s.charAt(i));
				break;
			} else {
				all[s.charAt(i)] = 1;
			}
		}
		
		// bonus:
		String t = "*l1J?)yn%R[}9~1\"=k7]9;0[$";
		
		int[] allToo = new int[128];
		
		for(int i = 0; i < s.length(); i++) {
			if(allToo[t.charAt(i)] != 0) {
				System.out.println("character: " + t.charAt(i) + ", index: " + allToo[t.charAt(i)]);
				break;
			} else {
				allToo[t.charAt(i)] = i;
			}
		}
		
		
		// really nice answer by whereismycow42 (reddit user):
		String input = "*l1J?)yn%R[}9~1\\\"=k7]9;0[$";
        int i = 0;
        	// indexOf returns FIRST occurrence of the specified character so if char repeats, 
            // second part of logical operator && is false
        while (i < input.length() && input.indexOf(input.charAt(i)) == i)  {
        	i++;
        }
        System.out.println((i == input.length()) ? "There are no recurring characters" : ("Found recurring character "+ input.charAt(i) + ""
        		+ " at position "+ (input.indexOf(input.charAt(i))+1)));
	}	
}