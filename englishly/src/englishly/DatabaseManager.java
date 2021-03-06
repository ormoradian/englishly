package englishly;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import englishly.Question.Difficulty;


public class DatabaseManager {
	private String path;
	private ArrayList<Question> questions;
	private JSONArray quizResults;
	
	private static DatabaseManager single_instance = null;
	
    public static DatabaseManager getInstance() 
    { 
        if (single_instance == null) 
            single_instance = new DatabaseManager(); 
 
        return single_instance; 
    } 

	public DatabaseManager() {
		this.path = ".\\src\\resources\\db";
		this.questions = new ArrayList<Question>();

		this.loadQuestions();
		this.loadQuizs();
	}
	
    public JSONArray getQuizResults() {
    	return this.quizResults;
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
		FileReader quizFile;
		try {
			quizFile = new FileReader(this.path + "\\quiz.json");
		} catch (FileNotFoundException e) {
			System.out.println("Database not found...");
			return;
		}
		
		JSONParser parser = new JSONParser();
		try {
			this.quizResults = (JSONArray) parser.parse(quizFile);
		} catch (Exception e) {
			this.quizResults = new JSONArray();
		}
		
		System.out.println("Loaded quizes: " + String.valueOf(this.quizResults.size()) + " quizes.");
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
			System.out.println("Failed to parse the JSON database...");
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
	
	public ArrayList<Integer> getQuestionsByDifficulty(Difficulty difficulty, int numberOfQuestions) {
		ArrayList<Question> relevantQuestions = new ArrayList<Question>(this.questions);
		ArrayList<Integer> questionIds = new ArrayList<Integer>();
		int questionsCount = 0;
		
		relevantQuestions.removeIf(question -> question.difficulty != difficulty);
		for (Question question : relevantQuestions)
	    {
			questionIds.add(question.id);

			questionsCount++;
			if (questionsCount == numberOfQuestions) {
				break;
			}
	    }
		return questionIds;
	}
	
	public Optional<Question> getQuestionById(int questionId) {
		return this.questions.stream().filter(question -> question.id == questionId).findFirst();
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
	
	public int produceQuizId() {
		Random rand = new Random(); 
		return rand.nextInt(10000);
	}

	public void saveQuiz(int quizId, Difficulty difficulty, int numberOfQuestions,
			int grade, int complitionSeconds, ArrayList<Integer> questionIds) {
		JSONObject jsonQuiz = new JSONObject();
		jsonQuiz.put("difficulty", difficulty.name());
		jsonQuiz.put("grade", grade);
		this.quizResults.add(jsonQuiz);
		String decodedQuizReuslts = this.quizResults.toString();

		try {
			FileWriter quizFile = new FileWriter(this.path + "\\quiz.json");
			quizFile.write(decodedQuizReuslts);
			quizFile.close();
		} catch (IOException e) {
			System.out.println("Exception saving quiz result...");
			return;
		}
	}
}