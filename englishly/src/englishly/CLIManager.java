package englishly;

import java.util.ArrayList;
import java.util.Scanner;

import englishly.Question.Difficulty;

public final class CLIManager {

	private CLIManager(){
		
	}
	
	public static int getQuizLevel()
	{
		Scanner input = new Scanner(System.in);
		
		Difficulty.allOf(seasons.class)
        .forEach(season -> System.out.println(season));
		
		System.out.println("Please choose the level of the quiz");
        System.out.println("-------------------------\n");
        System.out.println("1 - Beginners");
        System.out.println("2 - Intermediate");
        System.out.println("3 - Advanced");
        System.out.println("4 - Quit");
        int selection = input.nextInt();
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
