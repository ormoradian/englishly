package englishly;

import static org.junit.Assert.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;

public class TestReport extends junit.framework.TestCase {

	@Test
	public void testIsImproved() {
		Report report = new Report();
		
		JSONArray quizResults = new JSONArray();
		JSONObject quiz1 = new JSONObject();
		quiz1.put("grade", 65l);
		quiz1.put("difficulty", "EASY");
		
		JSONObject quiz2 = new JSONObject();
		quiz2.put("grade", 66l);
		quiz2.put("difficulty", "MEDIUM");
		
		quizResults.add(quiz1);
		quizResults.add(quiz2);
		
		report.quizResults = quizResults;
		
		this.assertTrue(report.getIsImproved());
	}
	
	@Test
	public void testIsNotImproved() {
		Report report = new Report();
		
		JSONArray quizResults = new JSONArray();
		JSONObject quiz2 = new JSONObject();
		quiz2.put("grade", 65l);
		quiz2.put("difficulty", "EASY");
		
		JSONObject quiz1 = new JSONObject();
		quiz1.put("grade", 66l);
		quiz1.put("difficulty", "MEDIUM");
		
		quizResults.add(quiz1);
		quizResults.add(quiz2);
		
		report.quizResults = quizResults;
		
		this.assertFalse(report.getIsImproved());
	}

}
