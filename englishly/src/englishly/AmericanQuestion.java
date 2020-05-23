package englishly;

import java.util.ArrayList;

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
		System.out.println(this.content);
		int i = 1;
		for (String answer: this.answers) {
			System.out.println("[" + i + "] " + answer);
			i++;
		}
	}
	
	public boolean isRightAnswer(int answer) {
		return answer - 1 == this.rightAnswer; 
	}
}