package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.business_logic.BusinessService;
import com.example.business_logic.BusinessServiceImplProgrammatic;


//to run this app we need db config in application.properties and dependency for mysql driver


/*
 * always put the class with @SpringBootApplication into base package:
 * 
 * The @SpringBootApplication annotation triggers component scanning for the current package and its sub-packages. 
 * Therefore, a solid way to go is to have the main class of the project reside in the base package.

This is configurable, and we can still locate it elsewhere by specifying the base package manually. However, in most cases, this option is certainly simpler.
 * 
 * */



@SpringBootApplication
public class TransactionSpringApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(TransactionSpringApplication.class);
		ConfigurableApplicationContext context = app.run(args);
		
		//programmatic
		context.getBean("businessServiceImplProgrammatic", BusinessService.class).doBusiness();
		//call method that throws exception. so the transaction should be rollbacked
		context.getBean("businessServiceImplProgrammatic", BusinessService.class).doBusinessWithException();
		
		//declarative
		context.getBean("businessServiceImplDeclarative", BusinessService.class).doBusiness();
		context.getBean("businessServiceImplDeclarative", BusinessService.class).doBusinessWithException();
	}

}
