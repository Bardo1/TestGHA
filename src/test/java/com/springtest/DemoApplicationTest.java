package com.springtest;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@SpringBootApplication
public class DemoApplicationTest {

	@Test
	public void contextLoads() {
		//this.applicationContextTest();
	}

}
