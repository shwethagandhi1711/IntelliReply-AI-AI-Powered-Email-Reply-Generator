package com.email.writer.app;

/*
Lombok annotation

@AllArgsConstructor automatically creates a constructor
with all required fields

Without this, we must manually write constructor

This is provided by Lombok library
*/
import lombok.AllArgsConstructor;


/*
ResponseEntity is used to send HTTP response to client

It contains:
- Body
- Status code
- Headers
*/
import org.springframework.http.ResponseEntity;


/*
Spring Boot REST annotations
*/
import org.springframework.web.bind.annotation.*;


/*
@RestController

This annotation marks this class as REST Controller

It combines:
@Controller + @ResponseBody

It means:
This class handles HTTP requests
and returns JSON / String response

Client → Controller → Response
*/
@RestController



/*
@RequestMapping

Defines base URL for this controller

All APIs inside this class will start with:

http://localhost:8080/api/email
*/
@RequestMapping("/api/email")



/*
@AllArgsConstructor

Automatically creates constructor like:

public EmailGeneratorController(EmailGeneratorService service){
    this.emailGeneratorService = service;
}

This is used for Dependency Injection
*/
@AllArgsConstructor



/*
@CrossOrigin

This allows frontend (React) to access backend

Because:

Frontend → localhost:5173
Backend → localhost:8080

Different ports = Cross Origin

origins="*" means allow all origins

Without this → CORS error occurs
*/
@CrossOrigin(origins = "*")



/*
Controller Class

This class handles email generation requests
*/
public class EmailGeneratorController {



    /*
    Service layer object

    private → accessible only inside class

    final → cannot be changed

    Spring injects this automatically
    */
    private final EmailGeneratorService emailGeneratorService;





    /*
    @PostMapping

    This handles HTTP POST request

    URL:

    http://localhost:8080/api/email/generate
    */
    @PostMapping("/generate")



    /*
    Method to generate email reply

    @RequestBody

    Converts JSON request into Java object

    Example JSON from frontend:

    {
      "emailContent": "Hello",
      "tone": "professional"
    }

    Converts into EmailRequest object
    */
    public ResponseEntity<String> generateEmail(

            @RequestBody EmailRequest emailRequest

    ) {



        /*
        Call service layer method

        Business logic happens in service layer
        */
        String response =
                emailGeneratorService.generateEmailReply(emailRequest);




        /*
        Return response to frontend

        ResponseEntity.ok()

        Means:

        Status: 200 OK
        Body: generated email
        */
        return ResponseEntity.ok(response);
    }

}