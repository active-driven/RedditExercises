package easy;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * [2017-10-16] Challenge #336 [Easy] Cannibal numbers
 * https://www.reddit.com/r/dailyprogrammer/comments/76qk58/20171016_challenge_336_easy_cannibal_numbers/
 * 
 * @author dream-tree
 */

/* 	Important thing to remember:
 * 	We don't have to find the right sequences but we have to find the max number of such sequences and this is much simpler to do!
 * 	Why: after throwing away numbers with values equal to or greater than a specified value,  
 * 	we have to find the moment when the sum of consecutive differences between specified value and current max number 
 * 	is less or equal the current number of numbers. When this request is not satisfied, we found the max number of cannibals. 
 * 	Example:
 * 	Counting max number of cannibal numbers available:
 * 	7 2                seven: numbers total, two: queries
 * 	21 7 5 8 10 1 3    these seven numbers
 * 	10 15              min value to achieve (1st query: 10, 2nd query: 15)
 * 	sorted:
 * 	1 3 5 7 8 // 10 21
 * 	first round: 
 * 	min value to achieve - max left current number (cannibal) <= max (current number of numbers) - 1 ('deleting' current element)
 * 	10 - 8 <= 5 - 1   satisfied
 * 	each next round:
 * 	min value to achieve - max left current number (cannibal) <= actual max (current number of numbers) - 1 ('deleting' current element) - 
 * 	     - (10-8) (previous difference i.e., previously 'deleted' number of elements)
 * 	      10 - 7 <=  4 - 1 -(10 - 8) => 3 <= 1    not satisfied
 * 
 * Interesting sequence: 3 3 3 2 2 2 1 1 1  Answer is 4, not 3.
 */

public class CannibalNumbers {

	public static void main(String[] args) {

	List<Integer> list = new ArrayList<>();
	list.add(21);
	list.add(9);
	list.add(5);
	list.add(8);
	list.add(10);
	list.add(1);
	list.add(3);
//	list.add(1);
//	list.add(1);
	
	// testing
	list
	.stream()
	.forEach(x -> System.out.print(x + " "));
	System.out.println();
	
	list.sort((a, b) -> b-a);
	
	// testing
	list
	.stream()
	.forEach(x -> System.out.print(x + " "));
	System.out.println();

	int limit = 10;
	int count = 0;
	Iterator<Integer> iterator = list.iterator();
	while(iterator.hasNext()) {
		if(iterator.next() >= limit) {
			iterator.remove();
			count++;
		}
	}
	
	// testing
	list
	.stream()
	.forEach(x -> System.out.print(x + " "));
	System.out.println();
	
	if(limit - list.get(0) <= list.size() - 1) {	
		int diff = limit - list.get(0);
		count++;
		list.remove(0);
		while(limit - list.get(0) <= list.size() - 1 - diff) {
			diff = limit - list.get(0);
			count++;
			list.remove(0);
			list.remove(list.size()-1);
		}
	}	 
	System.out.println(count);	
	}		
}