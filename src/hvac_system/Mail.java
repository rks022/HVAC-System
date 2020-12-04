package hvac_system;
import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	public static void sendEmail(String recepient ,String s1, String s2) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("Preparing email");
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		
		String account = "billisingh94@gmail.com";
		String password = "01Jan2000@";
		
		Session session = Session.getInstance(properties, new Authenticator() {
			
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account, password);
				
			}
			
		
		});
                
     
		Message message = prepareMessage(session, account, recepient ,s1,s2) ;
				
		Transport.send(message);
                 HvacNavigation hnav = new  HvacNavigation(); 
                 
		System.out.println("Message Sent");
	}

	private static Message prepareMessage(Session session, String account, String recipent,String s1, String s2) {
		// TODO Auto-generated method stub
		
		try {
                       
		
                        
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(account));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipent));
			message.setSubject(s1);
			message.setText(s2);
			return message;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return null;
	}

   
}