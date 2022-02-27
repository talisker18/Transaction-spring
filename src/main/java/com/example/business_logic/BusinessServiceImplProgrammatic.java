package com.example.business_logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

@Service("businessServiceImplProgrammatic") //with this annotation, we can inject this class as a bean in e.g. the class with main method and @SpringBootApplication
//when we use instance var annotaed with @Autowired, we need a config class with @Configuration and @ComponentScan
public class BusinessServiceImplProgrammatic implements BusinessService {
	
	private final TransactionTemplate transactionTemplate;
	
	public BusinessServiceImplProgrammatic(PlatformTransactionManager platformTransactionManager) {
		this.transactionTemplate = new TransactionTemplate(platformTransactionManager);
		this.transactionTemplate.setTimeout(30);
		this.transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
	}

	@Override
	public void doBusiness() {
		// TODO Auto-generated method stub
		String message = this.transactionTemplate.execute(status -> {
			//do here your db operations. commit will occur automatically
			
			return "transaction committed programmatic";
		});
		
		System.out.println(message);
		
	}
	
	@Override
	public void doBusinessWithException() {
		String message = this.transactionTemplate.execute(status -> {
			
			try {
				//do here your db operations
				throw new IllegalStateException("exception occured within transaction");
			} catch (Exception e) {
				// TODO: handle exception
				//catch exception and rollback transaction
				status.setRollbackOnly();
				return "exception occured programmatic. transaction will be rolled back";
			}
		});
		
		System.out.println(message);
		
	}

}
