package englishly;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;


public class EmailSender {
	public EmailSender(String to, String mailSubject, String mailBody)
	{
		
		this.host = "localhost";
        this.subject = mailSubject;
        this.body = mailBody;
        this.to = new String[]{ to };
		// Get system properties
	}

	private String from = "englishlyhit1";
    private String pass = "ab123456$";
    public String subject;
    public String body;
    private String host;
	private String[] to;
	Session session;

    public void sendMail( ) {
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", this.host);
        props.put("mail.smtp.user", this.from);
        props.put("mail.smtp.password", this.pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[this.to.length];

            // To get the array of addresses
            for( int i = 0; i < to.length; i++ ) {
                toAddress[i] = new InternetAddress(this.to[i]);
            }

            for( int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }

            message.setSubject(this.subject);
            message.setText(this.body);
            Transport transport = session.getTransport("smtp");
            transport.connect(host, from, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            System.out.println("Mail sent successfully");
        }
        catch (AddressException ae) {
            ae.printStackTrace();
        }
        catch (MessagingException me) {
            me.printStackTrace();
        }
    }
	
}
