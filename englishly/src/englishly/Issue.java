package englishly;

public class Issue {
	private int questionId;
	private String content;
	private String suggestedRightAnswer;
	
	public Issue(int questionId, String content, String suggestedRightAnswer) {
		this.questionId = questionId;
		this.content = content;
		this.suggestedRightAnswer = suggestedRightAnswer;
	}
}
