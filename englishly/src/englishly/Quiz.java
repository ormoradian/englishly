package englishly;

import java.util.ArrayList;
import java.util.Scanner;
import java.time.Instant;

import englishly.Question.Difficulty;

public class Quiz {
	protected int id;
	protected Difficulty difficulty;
	protected int numberOfQuestions;
	protected int grade;
	
	protected int complitionSeconds;
	protected ArrayList<Integer> questionIds;
	
	private DatabaseManager databaseManager;
	private Scanner scanner;
	private Long startTime;
	private Long endTime;
	private int numberOfRightQuestions;
	
	public Quiz(Difficulty difficulty, int numberOfQuestions) {
		this.difficulty = difficulty;
		this.databaseManager = DatabaseManager.getInstance();
		this.id = this.databaseManager.produceQuizId();
		this.numberOfQuestions = numberOfQuestions;
		this.questionIds = this.databaseManager.getQuestionsByDifficulty(difficulty, numberOfQuestions);
		this.scanner = new Scanner(System.in);
		this.numberOfRightQuestions = 0;
	}
	
	public Difficulty getDifficulty() {
		return this.difficulty;
	}

	public void finalizeQuiz() {
		this.endTime = Instant.now().getEpochSecond();
		this.complitionSeconds = (int)(this.endTime - this.startTime);
		// calculate quiz result and save to DB
		this.grade = (int)(((float)numberOfRightQuestions / (float)numberOfQuestions) * (float)100);
		System.out.format("You finished the quiz with the grade of: %d.", this.grade);
		System.out.println();
		System.out.format("You finished the quiz after %d seconds.", this.complitionSeconds);
		System.out.println();
		this.databaseManager.saveQuiz(
				this.id,
				this.difficulty,
				this.numberOfQuestions,
				this.grade, 
				this.complitionSeconds, 
				this.questionIds		
		);
	}
	
	public void produceEmailContent() {
		// produce the email content to send to the email sender
	}
	
	public void startQuiz() throws QuestionNotFoundException {
		 this.startTime = Instant.now().getEpochSecond();
		 // present questions and record results
		 for (int questionId : this.questionIds) {
			 Question quesion = this.databaseManager.getQuestionByID(questionId);
			 quesion.printQuestion();
			 boolean isRightAnswer = quesion.isRightAnswer();
			 if (isRightAnswer) {
				 this.numberOfRightQuestions++;
				 System.out.println("You're right!");
			 } else {
				 System.out.println("You're wrong!");
			 }
		 }
	}
}
