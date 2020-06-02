package englishly;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;


public class Menu {
	private Map<Integer, String> options;
	
	public Menu() {
		this.options = new HashMap<Integer, String>() {{
		    put(1, "Start a quiz");
	        put(2, "Exit");
		}};
	}
	
	public void showMenu() {
		String leftAlignFormat = "| %-3d| %-14s |%n";
		
		System.out.format("+----+----------------+%n");
		System.out.format("| ID |     Option     |%n");
		System.out.format("+----+----------------+%n");
		for (Map.Entry<Integer, String> entry : this.options.entrySet()) {
		    Integer optionNumber = entry.getKey();
		    String optionContent = entry.getValue();
		    System.out.format(leftAlignFormat, optionNumber, optionContent);
		}
		System.out.format("+----+----------------+%n");
	}
	
	public int getAction() {
		Scanner scanner = new Scanner(System.in);
		int numericOption = -1;
		boolean isError = false;
		
		System.out.println("Select an option from the menu: ");
		
		while (numericOption <= 0) {
			String selectedOption = scanner.nextLine();
			try { 
				numericOption = Integer.parseInt(selectedOption);
			} catch(NumberFormatException e){  
				System.out.println("Wrong option! please select a valid option: ");
				isError = true;
			}
			
			if (!isError && numericOption < 0) {
				System.out.println("Wrong option! please select an positive option: ");
			}
		}
		
		return numericOption;
	}
}