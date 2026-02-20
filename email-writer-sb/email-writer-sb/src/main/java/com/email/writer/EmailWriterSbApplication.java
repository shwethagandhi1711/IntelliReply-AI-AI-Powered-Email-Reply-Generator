package com.email.writer;


/*
Import SpringApplication class

SpringApplication is used to:

• Start Spring Boot application
• Create Spring Container
• Start embedded server (Tomcat)
*/
import org.springframework.boot.SpringApplication;


/*
@SpringBootApplication annotation

This is MOST IMPORTANT annotation in Spring Boot

It combines 3 annotations:

1. @Configuration
   → Marks class as configuration class

2. @EnableAutoConfiguration
   → Automatically configures Spring Boot

3. @ComponentScan
   → Scans all components like:
      @Controller
      @Service
      @Repository
*/
import org.springframework.boot.autoconfigure.SpringBootApplication;




/*
@SpringBootApplication

Marks this class as main class

Spring Boot starts from here
*/
@SpringBootApplication
public class EmailWriterSbApplication {



    /*
    Main method

    This is entry point of Java application

    JVM starts execution from here
    */
    public static void main(String[] args) {



		/*
		SpringApplication.run()

		This line does 5 important things:

		1. Starts Spring Boot application

		2. Creates Spring Container
		   (IoC Container)

		3. Performs Component Scanning

		4. Creates all Beans automatically

		5. Starts Embedded Server (Tomcat)

		Default server port: 8080

		Application runs at:
		http://localhost:8080
		*/
        SpringApplication.run(

                EmailWriterSbApplication.class,

                args
        );

    }

}