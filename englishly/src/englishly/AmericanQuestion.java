package englishly;

import java.util.ArrayList;
import java.util.Scanner;

class AmericanQuestion extends ChoiceQuestion {
	public AmericanQuestion(int id, 
			int rightAnswer,
			ArrayList<String> answers,
			Difficulty difficulty, 
			String content) {
		this.id = id;
		this.rightAnswer = rightAnswer;
		this.answers = answers;
		this.difficulty= difficulty;
		this.content = content;
		
		this.type = QuestionType.AMERICAN;
	}
	
	public void printQuestion() {
		System.out.format("[Question %d] %s", this.id, this.content);
		System.out.println();
		int i = 1;
		for (String answer: this.answers) {
			System.out.println("[" + i + "] " + answer);
			i++;
		}
	}
	
	public boolean checkAnwer(int answer) {
		return answer - 1 == this.rightAnswer;
	}
	
	public boolean isRightAnswer() {
		Scanner scanner = new Scanner(System.in);
		int answer = scanner.nextInt();
		return this.checkAnwer(answer); 
	}
}