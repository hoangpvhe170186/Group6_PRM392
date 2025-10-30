package com.example.f_food.screen.authentication_authorization;

import android.os.Handler;
import android.os.Looper;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GMailSender {
    private final String emailSender = "minhpqhe173298@fpt.edu.vn";
    private final String passwordSender = "krpi ujeg vzul mgvp";
    private String emailRecipient;
    private String subject;
    private String message;
    private SendMailCallback callback;

    public interface SendMailCallback {
        void onSuccess();
        void onFailure(Exception e);
    }

    public GMailSender(String emailRecipient, String subject, String message, SendMailCallback callback) {
        this.emailRecipient = emailRecipient;
        this.subject = subject;
        this.message = message;
        this.callback = callback;
    }

    public void execute() {
        new Thread(() -> {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(emailSender, passwordSender);
                }
            });

            try {
                Message mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(emailSender));
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRecipient));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(message);
                Transport.send(mimeMessage);
                new Handler(Looper.getMainLooper()).post(callback::onSuccess);
            } catch (Exception e) {
                e.printStackTrace();
                new Handler(Looper.getMainLooper()).post(() -> callback.onFailure(e));
            }
        }).start();
    }
}