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
		
		while (action != menu.getExitOptionNumber()) {
			if (action == 1) {
				// start a quiz
				Difficulty difficulty = menu.getDifficulty();
				int length = menu.getQuizLength();
				Quiz quiz = new Quiz(Difficulty.MEDIUM, length);
				quiz.startQuiz();
				quiz.finalizeQuiz();
			}
			
			if (action == 2) {
				Report report = new Report();
				report.sendReport();
			}
			
			if (action == 3) {
				System.out.println("Provide question id:");
				int questionId = scanner.nextInt();
				System.out.println("Provide issue content:");
				String issueContent = scanner.next();
				System.out.println("Provide suggested right answer:");
				String rightAnswer = scanner.next();
				Issue issue = new Issue(questionId, issueContent, rightAnswer);
				issue.sendIssueMail();
			}
			
			menu.showMenu();
			action = menu.getAction();
			System.out.println("The selected action: " + Integer.toString(action));
		}
		
		
    }
}
