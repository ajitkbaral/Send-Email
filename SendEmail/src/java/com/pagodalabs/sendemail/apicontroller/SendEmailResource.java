/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pagodalabs.sendemail.apicontroller;

import com.pagodalabs.sendemail.util.GetDate;
import java.io.PrintWriter;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Ajit Kumar Baral
 */
@Path("sendEmail")
public class SendEmailResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public SendEmailResource() {
    }

    /**
     * Retrieves representation of an instance of
     * com.pagodalabs.sendemail.apicontroller.GenericResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/xml")
    public String getXml() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/xml")
    public void putXml(String content) {
    }

    private String getDate() {
        GetDate date = new GetDate();
        return date.getCurrentDate();
    }

    @POST
    @Produces(value = MediaType.TEXT_PLAIN)
    public String sendEmail(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        String result="";
        // Recipient's email ID needs to be mentioned.
        String sender = request.getParameter("sender");
        String receiver = request.getParameter("receiver");
        String subject = request.getParameter("subject");
        String messageFromSender = request.getParameter("message");
        String smtp = request.getParameter("smtp");
        System.out.println("display"+sender+receiver+subject+messageFromSender+smtp);
        if (smtp!=null && sender != null && receiver != null && subject != null && messageFromSender != null) {
            // Recipient's email ID needs to be mentioned.
            String to = receiver;

            // Sender's email ID needs to be mentioned
            String from = sender;

            // Assuming you are sending email from localhost
            String host = smtp;//"smtp.wlink.com.np";

            // Get system properties
            Properties properties = System.getProperties();

            // Setup mail server
            properties.setProperty("mail.smtp.host", host);

            // Get the default Session object.
            Session session = Session.getDefaultInstance(properties);

            try {
                // Create a default MimeMessage object.
                MimeMessage message = new MimeMessage(session);
                // Set From: header field of the header.
                message.setFrom(new InternetAddress(from));
                // Set To: header field of the header.
                message.addRecipient(Message.RecipientType.TO,
                        new InternetAddress(to));
                // Set Subject: header field
                message.setSubject(subject);
                // Now set the actual message
                message.setText(messageFromSender + "\n\nThis message was sent on :" + getDate());
                // Send message
                Transport.send(message);
                result = "Email Successfully sent";
            } catch (MessagingException mex) {
                mex.printStackTrace();
                result="Error Occured. Please Try again";
            }
        }
        return result;
    }

}
