package englishly;

import java.util.Scanner;

import englishly.Question.Difficulty;

public class Main {
	public static void main(String[] args) throws QuestionNotFoundException {
		Scanner scanner = new Scanner(System.in);
    	
		DatabaseManager db = DatabaseManager.getInstance();
		Menu menu = new Menu();
		
		menu.showMenu();
		int action = menu.getAction();
		System.out.println("The selected action: " + Integer.toString(action));
		
		while (action != 2) {
			if (action == 1) {
				// start a quiz
				Difficulty difficulty = menu.getDifficulty();
				Quiz quiz = new Quiz(Difficulty.MEDIUM, 3);
				quiz.startQuiz();
				quiz.finalizeQuiz();
			}
			
			menu.showMenu();
			action = menu.getAction();
			System.out.println("The selected action: " + Integer.toString(action));
		}
		
		
    }
}
