package englishly;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import englishly.Question.Difficulty;


public class DatabaseManager {
	private String path;
	private ArrayList<Question> questions;
	
	private static DatabaseManager single_instance = null;
	
    public static DatabaseManager getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new DatabaseManager(); 
 
        return single_instance; 
    } 

	public DatabaseManager() {
		// Don't call me directly!! Call getInstance method instead
		this.path = System.getenv("ENGLISHLY_DB_PATH");
		this.questions = new ArrayList<Question>();

		this.loadQuestions();
		this.loadQuizs();
	}
	
	private Question parseQuestionByType(JSONObject questionJSONObject) throws UnknowQuestionTypeException{
		Question question;
		
		String stringQuestionType = (String) questionJSONObject.get("type");
		QuestionType questionType = QuestionType.valueOf(stringQuestionType);
		String difficulty = (String) questionJSONObject.get("difficulty");
		int id = ((Long) questionJSONObject.get("id")).intValue();
		String content = (String) questionJSONObject.get("content");
		
		switch(questionType) {
			case AMERICAN:
				int americanRightAnswer = ((Long) questionJSONObject.get("rightAnswer")).intValue();
				JSONArray answersObjects = (JSONArray)questionJSONObject.get("answers");
				ArrayList<String> answers = new ArrayList<String>();
			    for (Object answer : answersObjects)
			    {
			    	answers.add((String)answer);
			    }
			    question = new AmericanQuestion(id, americanRightAnswer, answers, Difficulty.valueOf(difficulty), content);
			    break;
			case BINARY:
				int binaryRightAnswer = ((Long) questionJSONObject.get("rightAnswer")).intValue();
				question = new BinaryQuestion(id, binaryRightAnswer, Difficulty.valueOf(difficulty), content);
				break;
			case COMPLETION:
				String completionRightAnswer = (String) questionJSONObject.get("rightAnswer");
				question = new CompletionQuestion(id, completionRightAnswer, Difficulty.valueOf(difficulty), content);
				break;
			default:
				throw new UnknowQuestionTypeException("Received unknown type of question...");
		}

		return question;
	}
	
	private void loadQuizs() {
		
	}

	private void loadQuestions() {
		FileReader questionsFile;
		try {
			questionsFile = new FileReader(this.path + "\\question.json");
		} catch (FileNotFoundException e) {
			System.out.println("Database not found...");
			return;
		}

		JSONParser parser = new JSONParser();

		JSONArray questionsArray;
		try {
			questionsArray = (JSONArray) parser.parse(questionsFile);
		} catch (Exception e) {
			System.out.println("Failed to paese the JSON database...");
			return;
		}

		for (Object questionObject : questionsArray) {
			JSONObject questionJSONObject = (JSONObject) questionObject;
			Question question;
			try {
				question = this.parseQuestionByType(questionJSONObject);	
			} catch (UnknowQuestionTypeException e) {
				System.out.println("Received unknown type of question...");
				return;
			}
			
			this.questions.add(question);
		}
		System.out.println("Loaded questions: " + String.valueOf(this.questions.size()) + " questions.");
	}

	public Question getQuestionByID(int id) throws QuestionNotFoundException {
		for (Question question: this.questions) {
			if (question.id == id) {
				return question;
			}
		}
		
		throw new QuestionNotFoundException("Question not found");
	}
	
	public ArrayList<Question> getShuffledQuestions(Difficulty difficulty, int length) {
		ArrayList<Question> filteredQuestions = new ArrayList<Question>();
		for (Question question: this.questions) {
			if (question.difficulty == difficulty) {
				filteredQuestions.add(question);
			}
		}
		Collections.shuffle(filteredQuestions);
		
		return new ArrayList<Question>(filteredQuestions.subList(0, length - 1));
	}

	public void saveQuestion() {

	}

	public void getQuiz() {
		// TODO
	}

	public void saveQuiz() {
		// TODO
	}
}