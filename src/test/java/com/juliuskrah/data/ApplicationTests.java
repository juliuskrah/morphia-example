package com.juliuskrah.data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = JUnitConfig.class)
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
public class ApplicationTests {

	@Test
	public void contextLoads() {
	}

}
