package com.example.assignment_webtech;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.MessagingException;
import javax.mail.Authenticator;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

@WebServlet(name = "SignServletHandler" , urlPatterns = "/sign_up")
public class SignServletHandler extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

       String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passportPic = request.getParameter("student_passport");
       String certificate = request.getParameter("certificates");

       System.out.println(username);
        System.out.println(certificate);
        sendEmail(username);
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + username+ " " + password +"</h1>");
        out.println("</body></html>");
    }

    private void sendEmail(String username) {
        final String senderEmail = "mwezejeanclaude130@gmail.com"; // Change this to your email address
        final String senderPassword = "cnufnmgxrcaegope"; // Change this to your email password


        // Set up the properties for the SMTP server
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com"); // Change this to your SMTP server
        properties.put("mail.smtp.port", "587"); // Change this to the appropriate port for your SMTP server
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        // Create a Session object to authenticate with the SMTP server
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(username));

            // Set the subject and content of the email
            message.setSubject("New User Registration");
            message.setText("Username: " + username + "\nPassword: " + "\n thank you for taking your registration: " );

            // Send the email
            Transport.send(message);
        } catch (MessagingException e) {
            System.out.println("error occur");
            e.printStackTrace();
        }
    }
}
