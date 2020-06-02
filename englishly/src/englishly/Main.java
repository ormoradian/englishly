package englishly;

import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
    	
		DatabaseManager db = DatabaseManager.getInstance();
		Menu menu = new Menu();
		
		menu.showMenu();
		int action = menu.getAction();
		System.out.println("The selected action: " + Integer.toString(action));
		
    }
}
