package com.email.writer.app;


/*
Jackson library class

JsonNode is used to read and navigate JSON response

Example:
{
 "name":"John"
}

JsonNode helps extract "John"
*/
import com.fasterxml.jackson.databind.JsonNode;


/*
ObjectMapper is used to convert:

JSON → Java Object
Java Object → JSON
*/
import com.fasterxml.jackson.databind.ObjectMapper;



/*
@Value annotation

Used to read values from application.properties file

Example:

gemini.api.url=https://...
gemini.api.key=xxxx
*/
import org.springframework.beans.factory.annotation.Value;



/*
@Service annotation

Marks this class as Service Layer

Service layer contains business logic

Spring automatically creates object of this class
*/
import org.springframework.stereotype.Service;



/*
WebClient is used to call external APIs

Example:
Gemini API
OpenAI API
Google API
*/
import org.springframework.web.reactive.function.client.WebClient;



/*
Map is used to create JSON request body
*/
import java.util.Map;




/*
@Service

Spring manages this class automatically
*/
@Service
public class EmailGeneratorService {



    /*
    WebClient object

    Used to call Gemini API
    */
    private final WebClient webClient;




    /*
    Reads Gemini API URL from application.properties

    Example:

    gemini.api.url=https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=
    */
    @Value("${gemini.api.url}")
    private String geminiApiUrl;




    /*
    Reads Gemini API Key from application.properties
    */
    @Value("${gemini.api.key}")
    private String geminiApiKey;




    /*
    Constructor

    Spring automatically injects WebClient.Builder

    Then we build WebClient object
    */
    public EmailGeneratorService(WebClient.Builder webClientBuilder) {

        this.webClient = webClientBuilder.build();
    }




    /*
    Main method to generate email reply

    This method is called from Controller
    */
    public String generateEmailReply(EmailRequest emailRequest) {



        /*
        Step 1: Build prompt

        Prompt is instruction sent to Gemini AI

        Example:

        Generate professional email reply...
        */
        String prompt = buildPrompt(emailRequest);




        /*
        Step 2: Create request body

        This converts into JSON

        JSON format required by Gemini API
        */
        Map<String, Object> requestBody = Map.of(

                "contents", new Object[] {

                        Map.of(

                                "parts", new Object[]{

                                        Map.of(
                                                "text", prompt
                                        )

                                }
                        )
                }
        );





        /*
        Step 3: Call Gemini API

        post() → POST request

        uri() → API URL

        header() → content type

        bodyValue() → send JSON

        retrieve() → get response

        bodyToMono() → convert response

        block() → wait and get result
        */
        String response = webClient.post()

                .uri(geminiApiUrl + geminiApiKey)

                .header("Content-Type","application/json")

                .bodyValue(requestBody)

                .retrieve()

                .bodyToMono(String.class)

                .block();




        /*
        Step 4: Extract text from JSON response
        */
        return extractResponseContent(response);
    }






    /*
    Method to extract actual reply from Gemini response
    */
    private String extractResponseContent(String response) {

        try {

            /*
            Create ObjectMapper
            */
            ObjectMapper mapper = new ObjectMapper();



            /*
            Convert JSON String → JsonNode
            */
            JsonNode rootNode = mapper.readTree(response);




            /*
            Navigate JSON and extract text

            Path:

            candidates → content → parts → text
            */
            return rootNode

                    .path("candidates")

                    .get(0)

                    .path("content")

                    .path("parts")

                    .get(0)

                    .path("text")

                    .asText();

        }

        catch (Exception e) {

            /*
            If error occurs, return error message
            */
            return "Error processing request: " + e.getMessage();
        }
    }







    /*
    Method to build AI prompt
    */
    private String buildPrompt(EmailRequest emailRequest) {


        /*
        StringBuilder is used to create string efficiently
        */
        StringBuilder prompt = new StringBuilder();




        /*
        Add main instruction
        */
        prompt.append(
                "Generate a professional email reply for the following email content. Please don't generate a subject line "
        );




        /*
        Check if tone exists
        */
        if (

                emailRequest.getTone() != null

                        &&

                        !emailRequest.getTone().isEmpty()

        ) {

            prompt.append("Use a ")

                    .append(emailRequest.getTone())

                    .append(" tone.");
        }




        /*
        Add original email content
        */
        prompt.append("\nOriginal email: \n")

                .append(emailRequest.getEmailContent());




        /*
        Return prompt
        */
        return prompt.toString();
    }

}