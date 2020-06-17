package englishly;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import englishly.Question.Difficulty;


public class TestQuiestion extends junit.framework.TestCase {

	@Test
	public void testAmericanQuestion() {
		ArrayList<String> answers = new ArrayList<String>();
		answers.add("Yes");
		answers.add("No");
		answers.add("Maybe");

		AmericanQuestion question = new AmericanQuestion(1, 1, answers, Difficulty.MEDIUM, "Is ice cream tasty?");
		
		this.assertFalse(question.checkAnwer(1));
		this.assertTrue(question.checkAnwer(2));
		this.assertFalse(question.checkAnwer(3));	
	}

	@Test
	public void testBinaryQuestion() {
		BinaryQuestion question = new BinaryQuestion(1, 1, Difficulty.MEDIUM, "Is ice cream tasty?");
		
		this.assertFalse(question.checkAnwer(0));
		this.assertFalse(question.checkAnwer(1));
		this.assertTrue(question.checkAnwer(2));
		this.assertFalse(question.checkAnwer(3));
	}

	@Test
	public void testCompletionQuestion() {
		CompletionQuestion question = new CompletionQuestion(1, "Maybe", Difficulty.MEDIUM, "Is ice cream tasty?");
		
		this.assertTrue(question.checkAnwer("Maybe"));
		this.assertFalse(question.checkAnwer("No"));
		this.assertFalse(question.checkAnwer("Yes"));
	}
}
