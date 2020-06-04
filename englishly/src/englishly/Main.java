package englishly;

import java.util.Scanner;

import englishly.Question.Difficulty;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
    	
		DatabaseManager db = DatabaseManager.getInstance();
		Menu menu = new Menu();
		
		menu.showMenu();
		int action = menu.getAction();
		System.out.println("The selected action: " + Integer.toString(action));
		
		if (action == 1) {
			// start a quiz
			// TODO choose a difficulty
			Quiz quiz = new Quiz(Difficulty.MEDIUM, 3);
			quiz.startQuiz();
			quiz.finalizeQuiz();
		}
    }
}
