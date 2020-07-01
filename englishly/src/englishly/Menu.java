package englishly;

import java.util.Scanner;

import englishly.Question.Difficulty;

import java.util.HashMap;
import java.util.Map;


public class Menu {
	private Map<Integer, String> options;
	private Scanner scanner;
	
	public Menu() {
		this.options = new HashMap<Integer, String>() {{
		    put(1, "Start a quiz");
		    put(2, "Create report");
		    put(3, "Create issue");
	        put(4, "Exit");
		}};
		this.scanner =  new Scanner(System.in);
	}
	
	public int getExitOptionNumber() {
		return 4;
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
	
	public static Difficulty getDifficultyFromInt(int difficulty) {
		return Difficulty.values()[difficulty];
	}
	
	public Difficulty getDifficulty() {
		System.out.println("Please choose the level of the quiz");
		String leftAlignFormat = "| %-3d| %-14s |%n";
		System.out.format("+----+----------------+%n");
		System.out.format("| ID |     Option     |%n");
		System.out.format("+----+----------------+%n");
		int i = 0;
		for (Difficulty difficulty : Difficulty.values()) {
			System.out.format(leftAlignFormat, i, difficulty);
			i++;
		}
		System.out.format("+----+----------------+%n");
        int selection = this.scanner.nextInt();
        return Menu.getDifficultyFromInt(selection);
	}
	
	public int getQuizLength() {
		System.out.println("Enter quiz length: ");
		return this.scanner.nextInt();
	}
	
	public int getAction() {
		int numericOption = -1;
		boolean isError = false;
		
		System.out.println("Select an option from the menu: ");
		
		while (numericOption <= 0) {
			String selectedOption = this.scanner.next();
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