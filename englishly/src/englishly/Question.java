package englishly;

import java.util.ArrayList;

enum QuestionType {
	AMERICAN,
	BINARY,
	COMPLETION
}

abstract public class Question {
	public enum Difficulty {
	    LOW,
	    MEDIUM,
	    HIGH
	}

	protected int id;
	protected Difficulty difficulty;
	protected QuestionType type;
	protected String content;

	public abstract void printQuestion();
	public abstract boolean isRightAnswer();
}
