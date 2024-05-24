package com.steg.backendSteg.DemandeService;
import org.springframework.stereotype.Service;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
@Service
public class NotificationService {

    public void envoyerEmail(String destinataire, String sujet, String contenu) {
        // SMTP server information
        String host = "smtp.gmail.com";
        final String username = "kacemnesrin@gmail.com"; // your Gmail username
        final String password = "ldgv trsn nqll lsxf"; // your Gmail password

        // Set up properties for the mail session
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");

        // Create a session with an authenticator
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create a new email message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinataire));
            message.setSubject(sujet);
            message.setText(contenu);

            // Send the email
            Transport.send(message);
            System.out.println("E-mail envoyé avec succès.");
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }
}