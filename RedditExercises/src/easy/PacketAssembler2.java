package easy;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * [2017-09-26] Challenge #333 [Easy] Packet Assembler
 * https://www.reddit.com/r/dailyprogrammer/comments/72ivih/20170926_challenge_333_easy_packet_assembler/
 * This version orders output by message ID, so it does not conform exercise request. As stated in 'Output description':
 * 'Output each completed message, one line per packet. Messages should be outputted in the order in which they are completed.'
 * This version is short but heavily uses string concatenation to conform proper sorting.
 * This version supports language-specific characters as in Tannhäuser and Voilà! 
 *    It uses StandardCharsets.UTF_16.name() in Scanner constructor. UTF_8 doesn't work.
 *    File is encoded as .txt file with UTF-8 coding (by Microsoft Notepad). Simple .txt or .rtf file doesn't work.
 *    If file isn't encoded this way, it is unreadable while Scanner constructor with StandardCharsets.UTF_16.name() is used.
 * 
 * @author dream-tree
 */

	public class PacketAssembler2 {

		List<String> list;
		
		public PacketAssembler2() {
			list = new ArrayList<String>();
		}
		
		public static void main(String[] args) {

			PacketAssembler2 pa = new PacketAssembler2();
			pa.processLineByLine();	
		}
		
		/**
		 * Reads input from a text file
		 * and creates the list of Strings objects consisting of 
		 * message ID, packet ID, total number of packets in the message and string of characters altogether.
		 */
		public void processLineByLine() {
			try(Scanner in = new Scanner(new File("C:/Users/oVoISheRe/OneDrive/test files/packetAssembler/input2.txt"), 
					StandardCharsets.UTF_16.name())) {
				while(in.hasNextLine()) {
					String line = in.nextLine();
					String checked = checkString(line);
					list.add(checked);
				}	
			} catch(IOException e) {
				System.err.println("Something went wrong while reading incoming packets. Printing stack trace:");
				e.printStackTrace();
			}
			processLine();
		}
			
		/**
		 * Checks if message ID is one-digit number or two-digit number.
		 * If message ID is one-digit number, it reorders the string to conform to two-digit sequence
		 * to avoid sorting this way: 0, 1, 10, 11, 12, 13, 2, 3, 4 etc.
		 * @param line string line to check
		 * @return reordered string if message ID is one-digit number or same string if message ID is two-digit number.
		 */
		public String checkString(String line) {
			if(line.substring(9, 10).equals(" ")) {
				return line.substring(0, 8) + " " + line.substring(8, 9) + line.substring(10);
			} else {
			return line;
			}
		}
		
		/**
		 * Streams the list, sorts it and prints the results.
		 */	
		public void processLine() {
			list
			.stream()
			.sorted((a, b) -> a.compareTo(b))
			.forEach(x -> System.out.println(x));			
		}
	}