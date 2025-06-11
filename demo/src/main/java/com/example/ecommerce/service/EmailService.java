package com.example.ecommerce.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailService {

    @Value("${sendgrid.api-key}")
    private String sendGridApiKey;

    public void sendEmail(String to, String subject, String content) {
        Email from = new Email("noreply@ecommerce.com");
        Email toEmail = new Email(to);
        Content emailContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, toEmail, emailContent);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
        } catch (IOException ex) {
            ex.printStackTrace(); // log properly
        }
    }
    
    public void sendOrderConfirmation(String toEmail, String orderId) {
        Email from = new Email("ysramar@altimetrik.com"); // Must be a verified sender in SendGrid
        Email to = new Email("yuvanshankar2111@gmail.com");
        String subject = "Order Confirmation - Order ID: " + orderId;
        Content content = new Content("text/plain", "Thank you for your order! Your order ID is: " + orderId);

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(sendGridApiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            
            // Optional: Log the result
            System.out.println("Email sent. Status: " + response.getStatusCode());
        } catch (IOException e) {
            throw new RuntimeException("Failed to send email", e);
        }
    }
}

