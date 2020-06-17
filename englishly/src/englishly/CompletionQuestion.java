package englishly;

import java.util.Scanner;

class CompletionQuestion extends Question {
	private String rightAnswer;
	
	public CompletionQuestion(int id, 
			String rightAnswer, 
			Difficulty difficulty, 
			String content) {
		this.id = id;
		this.rightAnswer = rightAnswer;
		this.difficulty= difficulty;
		this.content = content;
		
		this.type = QuestionType.COMPLETION;
	}
	
	public void printQuestion() {
		System.out.format("[Question %d] %s", this.id, this.content);
		System.out.println();
	}
	
	public boolean checkAnwer(String answer) {
		return answer == this.rightAnswer;
	}
	
	public boolean isRightAnswer() {
		Scanner scanner = new Scanner(System.in);
		String answer = scanner.next();
		return this.checkAnwer(answer);
	}
}