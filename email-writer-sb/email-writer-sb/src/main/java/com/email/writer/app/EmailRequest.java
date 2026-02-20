package com.email.writer.app;


/*
Import Lombok Data annotation

@Data is a Lombok annotation that automatically generates:

• Getters
• Setters
• toString()
• equals()
• hashCode()
• Constructor

Without @Data, we must write all manually

This reduces boilerplate code
*/
import lombok.Data;



/*
@Data annotation

This tells Lombok to generate:

getEmailContent()
setEmailContent()

getTone()
setTone()

and other methods automatically
*/
@Data



/*
EmailRequest class

This is a DTO (Data Transfer Object)

Purpose:

To transfer data from Frontend → Backend

Frontend sends JSON
Spring converts JSON → EmailRequest object
*/
public class EmailRequest {



    /*
    Stores original email content

    Example:

    "Hello, can we schedule a meeting tomorrow?"
    */
    private String emailContent;




    /*
    Stores tone of reply

    Example:

    "professional"
    "casual"
    "friendly"
    */
    private String tone;

}