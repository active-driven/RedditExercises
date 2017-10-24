package easy;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * [2017-09-26] Challenge #333 [Easy] Packet Assembler
 * https://www.reddit.com/r/dailyprogrammer/comments/72ivih/20170926_challenge_333_easy_packet_assembler/
 * Exercise note: (output description):
 * 'Output each completed message, one line per packet. Messages should be outputted in the order in which they are completed.'
 * This version conforms this request.
 * This version supports language-specific characters as in Tannhäuser and Voilà! 
 *    It uses StandardCharsets.UTF_16.name() in Scanner constructor. UTF_8 doesn't work.
 *    File is encoded as .txt file with UTF-8 coding (by Microsoft Notepad). Simple .txt or .rtf file doesn't work.
 *    If file isn't encoded this way, it is unreadable while Scanner constructor with StandardCharsets.UTF_16.name() is used.
 * 
 * @author dream-tree
 */

public class PacketAssembler3 {

	Map<Integer, List<String>> map;
	
	public PacketAssembler3() {
		map = new HashMap<Integer, List<String>>();
	}
	
	public static void main(String[] args) {

		PacketAssembler3 pa = new PacketAssembler3();
		pa.processLineByLine();		
	//	pa.printMap();	
	//	pa.printMap2();
	}	

	/**
	 * Reads input from a text file
	 * and processes each input text line to {@link #processLine(String)} method.
	 */
	public void processLineByLine() {
		try(Scanner in = new Scanner(new File("C:/Users/oVoISheRe/OneDrive/test files/packetAssembler/input2.txt"), StandardCharsets.UTF_16.name())) {
			while(in.hasNextLine()) {
				processLine(in.nextLine());
			}	
		} catch(IOException e) {
			System.err.println("Something went wrong while reading incoming packets. Printing stack trace:");
			e.printStackTrace();
		}		
	}
	
	/**
	 *  Splits each line of input text file into a two parts
	 *  and constructs the map with Integer objects (message Ids) mapped to list of String objects i.e. 
	 *  packetID, total number of packets and message packets altogether.
	 *  It also splits this string into two additional parts (integers) to make sorting possible.
	 *  @param line line of text from input text file
	 */
	public void processLine(String line) {
		
		Scanner in = new Scanner(line);
		Integer messageID = Integer.parseInt(in.next()); 
		// reading all remaining part of line to set values in List<String>> map
		String allremainingLine = in.nextLine();
			// splitting line to separate integers to make sorting possible
			Scanner splittedLine = new Scanner(allremainingLine);
			Integer packetID = splittedLine.nextInt();
			Integer totalNumberOfPackets = splittedLine.nextInt();    
			splittedLine.close();
		// checking if key exists 
		List<String> message = map.get(messageID);
		if(message == null) {		
			map.put(messageID, message = new ArrayList<String>());
			for(int i = 0; i < totalNumberOfPackets; i++) {
				message.add(null);
			}
		}		
		message.set(packetID, allremainingLine);  
		checkIfCompleted();
		in.close();
		}
	
	/**
	 * Checks if list (List<String>) is completed i.e. if all message packets are already available 
	 * (if there is no reference to null). 
	 */
	public void checkIfCompleted() {
		// impossible: for(Integer i : map.keySet()) {(..)   Exception in thread "main" java.util.ConcurrentModificationException
		for(Iterator<Integer> it = map.keySet().iterator(); it.hasNext(); ) {		
			// temp variable to use it again as argument in print() method
			Integer temp = it.next();
			if(map.get(temp) 
					.stream()
					.allMatch(Objects::nonNull)) { 
				print(temp, map.get(temp));	
				it.remove();
				}				
		}
	}
	
	/**
	 * Prints elements of completed list.
	 */
	public void print(Integer temp, List<String> list) {
		for(String s : list) {
			System.out.println(temp + s);
		}
	}	

	/**
	 * Prints all map elements.
	 * Method not used.
	 */
	public void printMap() {
		
		for(Integer messageID : map.keySet()) {
			for(int listIndex = 0; listIndex < map.get(messageID).size(); listIndex++) {  
				System.out.println(messageID + "    " + listIndex + "   " + map.get(messageID).size() + 
						"  " + map.get(messageID).get(listIndex));		
			}                                          
		}                                             
	}	
	/**
	 * Prints all map elements as formatted output.
	 * Method not used.
	 */
	public void printMap2() {
		for(Integer messageID : map.keySet()) {
			for(int listIndex = 0; listIndex < map.get(messageID).size(); listIndex++) {   
				System.out.format("%d%5d%5d %s%n", messageID, listIndex, map.get(messageID).size(), 
						map.get(messageID).get(listIndex));	
			}
		}
	}	
}