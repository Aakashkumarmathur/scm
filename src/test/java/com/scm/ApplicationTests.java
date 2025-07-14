package com.scm;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.scm.services.impl.EmailServiceImpl;

@SpringBootTest
class ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private EmailServiceImpl service;

	@Test
	void sendEmailTest(){
		service.sendEmail("aakashmathur1311@gmail.com", "This is testing email", "I am sending email from the scm team");
	}
}
