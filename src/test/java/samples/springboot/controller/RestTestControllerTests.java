package samples.springboot.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import samples.springboot.Application;
import samples.springboot.domain.Person;

import java.util.List;

/**
 * Created by izeye on 14. 12. 22..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class RestTestControllerTests {

	@Value("${local.server.port}")
	int port;

	@Test
	public void getPersons() {
		String url = "http://localhost:" + port + "/test/rest/persons";

		TestRestTemplate restTemplate = new TestRestTemplate();
		ResponseEntity<List> responseEntity = restTemplate.getForEntity(url, List.class);
		List<Person> persons = responseEntity.getBody();
		System.out.println(persons);
	}

}
