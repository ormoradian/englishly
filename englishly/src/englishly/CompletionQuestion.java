package englishly;

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
		System.out.println(this.content);
	}
	
	public boolean isRightAnswer(String answer) {
		return answer == this.rightAnswer;
	}
}