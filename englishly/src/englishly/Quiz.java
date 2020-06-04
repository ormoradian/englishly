package englishly;

import java.util.ArrayList;
import java.time.Instant;

import englishly.Question.Difficulty;

public class Quiz {
	protected int id;
	protected Difficulty difficulty;
	protected int numberOfQuestions;
	protected int grade;
	private Long startTime;
	private Long endTime;
	protected Long complitionSeconds;
	protected ArrayList<Integer> questionIds;
	private DatabaseManager databaseManager;
	
	public Quiz(Difficulty difficulty, int numberOfQuestions) {
		this.difficulty = difficulty;
		this.databaseManager = DatabaseManager.getInstance();
		this.id = this.databaseManager.produceQuizId();
		this.numberOfQuestions = numberOfQuestions;
		this.questionIds = this.databaseManager.getQuestionsByDifficulty(difficulty);
	}
	
	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	public void finalizeQuiz() {
		this.endTime = Instant.now().getEpochSecond();
		this.complitionSeconds = this.endTime - this.startTime;
		// calculate quiz result and save to DB
	}
	
	public void produceEmailContent() {
		// produce the email content to send to the email sender
	}
	
	public void startQuiz() {
		 this.startTime = Instant.now().getEpochSecond();
		 // present questions and record results
	}
}
