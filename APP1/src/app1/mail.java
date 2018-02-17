package app1;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class mail {
	 
	 private String username = "masciaanna.68@gmail.com";
	 private String password = "nino9425.";

	 public static void main(String[] args) throws AddressException, MessagingException {
	   mail es = new mail();
	   try {
		es.sendEmail(
		     "sebastianopanico@libero.it",
		     "Email di prova",
		     "Eccomi!!!!!!!!!!!!!!! \n\n\nHo inviato l'email!");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 }

	 public void sendEmail(String to, String subject, String text) throws AddressException, MessagingException, IOException {
	   /***** creiamo un oggetto di tipo Properties che conterrà i parametri di configurazione per la connessione al mail server *****/
	   Properties props = new Properties();
	   props.put("mail.smtp.auth", "true");
	   props.put("mail.smtp.starttls.enable", "true");
	   props.put("mail.smtp.host", "smtp.gmail.com");
	   props.put("mail.smtp.port", "587");
	 
	  /******* Creiamo una connessione al mail server ********/
	  Session session = Session.getInstance(
	     props,
	     new javax.mail.Authenticator() {
	       protected PasswordAuthentication getPasswordAuthentication() {
	          return new PasswordAuthentication(username, password);
	       }
	    });

	  /****** creiamo il messaggio impostando, destinatari, oggetto e contenuto del messaggio ******/
	  Message message = new MimeMessage(session);
	  String ciao="ciao";
	  Address fromAddress=new InternetAddress("masciaanna.68@gmail.com",ciao);
	  message.setFrom(fromAddress);
	  message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	  message.setSubject(subject);
	  message.setText(text);
	  Multipart multipart = new MimeMultipart();
	  File allegati = new File ("C:\\Users\\Sebastiano\\Desktop\\CVMEGHI.pdf");
	  URL path = null; 
	  path = allegati.toURL(); //recupero url del file
	  allegati = new File(path.getFile());// nome del file
	  String nomefile = allegati.getName();//prendo il nome del file
	  BodyPart bodyPart = new MimeBodyPart();
	((MimeBodyPart) bodyPart).setText((text), "ISO-8859-15");
	  multipart.addBodyPart( bodyPart );
	 bodyPart = new MimeBodyPart();
     ((MimeBodyPart) bodyPart).attachFile( allegati);
     bodyPart.setFileName( nomefile );// Il nome con cui si vuole che l'allegato venga visto
     multipart.addBodyPart( bodyPart );
     message.setContent( multipart );
	  /***** INVIAMO L'EMAIL! ******/
	  Transport.send(message);
	 }
	}


