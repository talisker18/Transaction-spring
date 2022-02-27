package com.example.business_logic;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("businessServiceImplDeclarative")
public class BusinessServiceImplDeclarative implements BusinessService{

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void doBusiness() {
		// TODO Auto-generated method stub
		//db operations come here
		System.out.println("db operations from doBusiness() declarative");
		
	}

	@Transactional
	@Override
	public void doBusinessWithException() {
		// TODO Auto-generated method stub
		//db operations here
		throw new IllegalStateException("exception declarative");
		
	}

}
