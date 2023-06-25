package com.amitu.spring.cloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CouponserviceApplicationTests {
	
	@Autowired
	MockMvc mvc;

	@Test
	void contextLoads() {
	}

}
