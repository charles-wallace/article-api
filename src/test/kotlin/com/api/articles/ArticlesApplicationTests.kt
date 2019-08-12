package com.api.articles

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

/*
	- 	Junit test using SpringBootTests
	- 	SpringRunner is a Junit runner instructs spring to launch
		spring boot application
	-	After SpringRunner launches Spring Application Context loads
		and beans are created.
*/
@RunWith(SpringRunner::class)
@SpringBootTest
class ArticlesApplicationTests {

	@Test
	fun contextLoads() {
	}

}
