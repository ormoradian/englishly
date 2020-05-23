package englishly;

import java.util.ArrayList;

class BinaryQuestion extends ChoiceQuestion {
	public BinaryQuestion(int id, 
			int rightAnswer,
			Difficulty difficulty, 
			String content) {
		this.id = id;
		this.rightAnswer = rightAnswer;
		this.answers = new ArrayList<String>();
		this.answers.add("true");
		this.answers.add("false");
		this.difficulty= difficulty;
		this.content = content;
		
		this.type = QuestionType.BINARY;
	}
	
	public void printQuestion() {
		System.out.println(this.content);
		System.out.println("[1] false");
		System.out.println("[2] true");
	}
	
	public boolean isRightAnswer(int answer) {
		return answer - 1 == this.rightAnswer;
	}
}
