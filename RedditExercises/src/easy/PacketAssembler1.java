package easy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * [2017-09-26] Challenge #333 [Easy] Packet Assembler
 * https://www.reddit.com/r/dailyprogrammer/comments/72ivih/20170926_challenge_333_easy_packet_assembler/
 * This version orders output by message ID, so it does not conform exercise request. As stated in 'Output description':
 * 'Output each completed message, one line per packet. Messages should be outputted in the order in which they are completed.'
 * This version does not support language-specific characters as in Tannhäuser and Voilà!
 * 
 * @author dream-tree
 */

public class PacketAssembler1 {

	SortedMap<Integer, List<String>> map;
	
	public PacketAssembler1() {
		map = new TreeMap<Integer, List<String>>();
	}
	
	public static void main(String[] args) {

		PacketAssembler1 pa = new PacketAssembler1();
		pa.processLineByLine();		
		pa.printMap();	
	//	pa.printMap2();
	}	

	/**
	 * Reads input from a text file
	 * and processes each input text line to {@link #processLine(String)} method.
	 */
	public void processLineByLine() {
		try(Scanner in = new Scanner(new File("C:/Users/oVoISheRe/OneDrive/test files/packetAssembler/input.txt"))) {
			while(in.hasNextLine()) {
				processLine(in.nextLine());
			}	
		} catch(IOException e) {
			System.err.println("Something went wrong while reading incoming packets. Printing stack trace:");
			e.printStackTrace();
		}		
	}
	
	/**
	 *  Splits each line of input text file into a four parts
	 *  and constructs the map with Integer objects (message Ids) mapped to list of String objects (message packets).
	 *  @param line line of text from input text file
	 */
	public void processLine(String line) {
		
		Scanner in = new Scanner(line);
		Integer messageID = Integer.parseInt(in.next());   // w ogóle trim() nie jest potrzebne... hehe
		Integer packetID = Integer.parseInt(in.next());
		Integer totalNumberOfPackets = Integer.parseInt(in.next());
		String packet = in.nextLine().trim();      // tu też niepotrzebne ale wyrównuje wcięcie o jeden dla indeksów 0-7, a 0-10
		
		// checking if key exists 
		List<String> message = map.get(messageID);
		if(message == null) {		
			map.put(messageID, message = new ArrayList<String>());
			for(int i = 0; i < totalNumberOfPackets; i++) {
				message.add("");
			}
		}		
		message.set(packetID, packet);     //  add ma 2 wersje!!!! namęczyłem się tutaj!!! btw - każda ma inny return type!!!!
									//	boolean add(E e)  Appends the specified element to the end of this list. (zawsze zwraca true)
									//	void add(int index, E element)  Inserts the specified element at the specified position in this list. 
						// ani add() ani set() nie zadziałają bez ustawienia wszytkich poądanych elementów ArrayLista bo on jest pusty!!
						// on musi mieć size a jest komunikat: Exception in thread "main" java.lang.IndexOutOfBoundsException: Index: 1, Size: 0
						// wrzucenie Z tutaj: map.put(X, message = new ArrayList<String>(Z)); nic nie daje bo capacity, a nie size
		in.close();
		}

	/**
	 * Prints all map elements.
	 */
	public void printMap() {
		
		for(Integer messageID : map.keySet()) {
			for(int listIndex = 0; listIndex < map.get(messageID).size(); listIndex++) {   // for(String s : map.get(i)) not used to print Y and Z (index and total size)
				System.out.println(messageID + "    " + listIndex + "   " + map.get(messageID).size() + 
						"  " + map.get(messageID).get(listIndex));		
			}                                          // List<String> java.util.Map.get(Object key)
		}                                              // String java.util.List.get(int index)
	}	
	/**
	 * Prints all map elements as formatted output.
	 */
	public void printMap2() {
		for(Integer messageID : map.keySet()) {
			for(int listIndex = 0; listIndex < map.get(messageID).size(); listIndex++) {   // for(String s : map.get(i)) not used to print Y and Z (index and total size)
				System.out.format("%d%5d%5d %s%n", messageID, listIndex, map.get(messageID).size(), 
						map.get(messageID).get(listIndex));	
			}
		}
	}	
}