package easy;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * [2017-11-06] Challenge #339 [Easy] Fixed-length file processing
 * https://www.reddit.com/r/dailyprogrammer/comments/7b5u96/20171106_challenge_339_easy_fixedlength_file/
 * Solution based on Daige user answer.
 * @author dream-tree
 */

public class FixedLengthFileProcessing2 {
	
    public static void main(String[] args) {
    	  	
        String highestName = "";
        int highestSalary = 0;
        String name = ""; 
        
        try(Scanner in = new Scanner(new File("C:/Users/oVoISheRe/OneDrive/test files/fixedLengthFileProcessing/EmployeeRecords.txt"))) {

        while(in.hasNextLine()) {
        	String line = in.nextLine();
            if(line.startsWith("::EXT::SAL")){
                int salary = Integer.parseInt(line.substring(11));
                if(highestSalary < salary) {
                	highestSalary = salary;
                	// name from else if branch is referenced to highestName only if salary is highest than before
                	// so no unnecessary names are referenced
                    highestName = name;
                }          
            }
            // this branch always starts first so each name is remembered till if branch executes
            else if(!line.startsWith("::EXT::")){
                name = line.substring(0, 20).trim();
            }
        }
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        }
        System.out.println(String.format("%s, $%,d", highestName, highestSalary));
    }
}  