package englishly;

import java.util.ArrayList;
import java.util.Scanner;

public final class CLIManager {

	private CLIManager(){
		
	}
	
	public static int getQuizLevel()
	{
		Scanner input = new Scanner(System.in);
		int selection;
		System.out.println("Please choose the level of the quiz");
        System.out.println("-------------------------\n");
        System.out.println("1 - Beginners");
        System.out.println("2 - Intermediate");
        System.out.println("3 - Advanced");
        System.out.println("4 - Quit");
        selection = input.nextInt();
        return selection;   
	}
	
	public static int presentQuestion(String question,ArrayList<String> answers)
	{
		Scanner scanner = new Scanner(System.in);
		int selection;
		System.out.println(question);
        System.out.println("-------------------------\n");
        for (int i = 0; i < answers.size(); i++) {
        	  System.out.println(String.format("%d = %s", i+1,answers.get(i)));
        	}
        selection = scanner.nextInt();
        return selection;   
	}
}
