package englishly;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestEmailSender {

	@Test
	public void testSendingMail() {
		String ENGLISHLY_MAIL_ADDRESS = "prussyuval@gmail.com";
		EmailSender emailSender = new EmailSender(ENGLISHLY_MAIL_ADDRESS, "Englishly issue report", "Hay!");
		emailSender.sendMail();
	}

}
