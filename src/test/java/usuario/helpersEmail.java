/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package usuario;

/**
 *
 * @author Rodolfo
 */

import controlls.controll.concrets.ctrlUsuario;

public class helpersEmail {

      public static void main(String[] args) {
//            Properties props = new Properties();
//            props.put("mail.smtp.host", "smtp.gmail.com");
//            props.put("mail.smtp.starttls.enable","true");
//            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.port", "587");
//
//            Session session = Session.getDefaultInstance(props,
//                        new javax.mail.Authenticator() {
//                             protected PasswordAuthentication getPasswordAuthentication() 
//                             {
//                                   return new PasswordAuthentication("papersrs2002@gmail.com", "123MulherNua");
//                             }
//                        });
//            try {
//                  Message message = new MimeMessage(session);
//                  message.setFrom(new InternetAddress("papersrs2002@gmail.com")); //Remetente
//
//                  Address[] toUser = InternetAddress //Destinatário(s)
//                             .parse("rodisantana2002@gmail.com");  
//                  message.setRecipients(Message.RecipientType.TO, toUser);
//                  message.setSubject("Enviando email com JavaMail");//Assunto
//                  message.setText("Enviei este email utilizando JavaMail com minha conta GMail!");
//
//                  Transport.send(message);
//                  System.out.println("Feito!!!");
//             } catch (MessagingException e) {
//                  throw new RuntimeException(e);
//            }

//          clsTrataEmail trataEmail = new clsTrataEmail();
//          String endereco = "rodisantana2002@gmail.com";
//          String assunto = "muita treta";
//          String conteudo = "hahahahahaha";
//          clsPSR.prt(trataEmail.enviarEmail(endereco, assunto, conteudo));

          ctrlUsuario Usuario = new ctrlUsuario();
          Usuario.enviarSenha("rodisantana2002@gmail.com");
          System.exit(0);
      }
}