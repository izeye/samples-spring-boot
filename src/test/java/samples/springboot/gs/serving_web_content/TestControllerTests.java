package samples.springboot.gs.serving_web_content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 1. 22..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=0")
public class TestControllerTests {

	@Value("${local.server.port}")
	int port;

	@Test
	public void testHeaderSizeLimit() {
		testHeaderSizeLimit(7 * 1024, HttpStatus.OK);

		// Based on http://tomcat.apache.org/tomcat-7.0-doc/config/http.html.
		testHeaderSizeLimit(8 * 1024, HttpStatus.BAD_REQUEST);
	}

	private void testHeaderSizeLimit(int headerValueSize, HttpStatus expectedStatus) {
		String url = "http://localhost:" + port + "/gs/serving-web-content/testHeaders";

		StringBuilder sbName = new StringBuilder();
		for (int i = 0; i < headerValueSize; i++) {
			sbName.append("a");
		}
		String name = sbName.toString();

		TestRestTemplate restTemplate = new TestRestTemplate();

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.add("name", name);

		HttpEntity<Void> httpEntity = new HttpEntity<>(httpHeaders);

		ResponseEntity<Void> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, Void.class);

		assertThat(responseEntity.getStatusCode(), is(expectedStatus));
	}

}
