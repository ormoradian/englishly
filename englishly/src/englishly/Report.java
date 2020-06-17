package englishly;

import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Report {
	private boolean isimproved;
	private JSONArray quizResults;
	
	public Report() {
		DatabaseManager databaseManager = DatabaseManager.getInstance();
		this.quizResults = databaseManager.getQuizResults(); 
	}
	
	private boolean getIsImproved() {
		boolean isImproved = true;
		Long previousGrade = new Long(0);
		
		for (Object quizResult : this.quizResults) {
			JSONObject quizResultJSONObject = (JSONObject) quizResult;
			Long grade = (Long)quizResultJSONObject.get("grade");
			if (previousGrade > grade ) {
				isImproved = false;
				break;
			}
			previousGrade = grade;
		}
		
		return isImproved;
	}
	
	public boolean sendReport() {
		String mailContent = "";
		int quizCounter = 1;
		
		for (Object quizResult : this.quizResults) {
			JSONObject quizResultJSONObject = (JSONObject) quizResult;

			mailContent += String.format(
					"[%d] Grade: %d\tDifficulty: %s\n",
					quizCounter,
					quizResultJSONObject.get("grade"),
					quizResultJSONObject.get("difficulty")
			);
			quizCounter++;
		}
		
		if (this.getIsImproved() ) {
			mailContent += "Yes!!! You have improved!\n";
		} else {
			mailContent += "Oh no!!!! You didn't improved!\n";
		}
		
		System.out.println("Provide target mail:");
		Scanner scanner = new Scanner(System.in);
		String mailAddress = scanner.next();

		EmailSender emailSender = new EmailSender(mailAddress, "Englishly report", mailContent);
		emailSender.sendMail();
		
		return true;
	}
}
