/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers.email;

import helpers.excecoes.excMessages;
import java.util.Properties;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Rodolfo
 */
public class clsTrataEmail {
    private Properties props;
    
    public clsTrataEmail(){
        props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.starttls.enable","true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");        
    }
    
    public String enviarEmail(String enderecos, String assunto, String conteudo){ // conteudo poderá ser text ou html
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("horacerta2002@gmail.com", "123Perkons");
                }
            });
        try {
            Message message = new MimeMessage(session);                                    
            message.setFrom(new InternetAddress("naoresponder@papers.com")); //Remetente

            Address[] toUser = InternetAddress.parse(enderecos);  
            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(assunto);//Assunto
            message.setContent(conteudo, "text/html");

            Transport.send(message);              
            return excMessages.STR_EMAIL_SUCESSO;
              
         } catch (MessagingException e) {
            return excMessages.STR_EMAIL_INSUCESSO;
        }        
    }
}
