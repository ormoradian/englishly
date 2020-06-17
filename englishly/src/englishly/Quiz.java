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

	public void finalizeQuiz() throws QuestionNotFoundException {
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
		
		System.out.println("Do you want to produce an email? [yes\\no]");
		String answer = this.scanner.nextLine();
		answer = answer.toLowerCase();
		if (answer.equals("yes") || answer.equals("ye") || answer.equals("y")) {
			System.out.println("Sending the quiz result with mail");
			this.sendQuizAsEmailContent();
		}
	}
	
	public void sendQuizAsEmailContent() throws QuestionNotFoundException {
		String mailContent = String.format(
				"Grade: %d\tDifficulty: %s\nQuestions:\n",
				this.grade,
				this.difficulty
		);
		
		int questionCounter = 1;
		
		for (int questionId : this.questionIds) {
			Question question = this.databaseManager.getQuestionByID(questionId);
			mailContent += String.format("[Question %d] %s (Diffuculty: %s)\n", 
					questionCounter,
					question.content,
					question.difficulty);
			questionCounter++;
		}
		
		System.out.println("Provide target mail:");
		Scanner scanner = new Scanner(System.in);
		String mailAddress = scanner.next();
		
		EmailSender emailSender = new EmailSender(mailAddress, "Englishly report", mailContent);
		emailSender.sendMail();
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
