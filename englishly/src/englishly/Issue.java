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
	
	public void sendIssueMail() {
		String ENGLISHLY_MAIL_ADDRESS = "englishlyhit1@gmail.com";
		
		String mailContent = String.format(
				"Issue about a question id: %d\nIssue content: %s\nSuggested answer: %s",
				this.questionId,
				this.content,
				this.suggestedRightAnswer
		);

		EmailSender emailSender = new EmailSender(ENGLISHLY_MAIL_ADDRESS, "Englishly issue report", mailContent);
		emailSender.sendMail();
	}
}
