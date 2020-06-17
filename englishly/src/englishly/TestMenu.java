package englishly;

import static org.junit.Assert.*;

import org.junit.Test;

import englishly.Question.Difficulty;

public class TestMenu extends junit.framework.TestCase {

	@Test
	public void testCastingFromIntToDifficulty() {
		this.assertEquals(Menu.getDifficultyFromInt(0), Difficulty.LOW);
		this.assertEquals(Menu.getDifficultyFromInt(1), Difficulty.MEDIUM);
		this.assertEquals(Menu.getDifficultyFromInt(2), Difficulty.HIGH);
	}

}
