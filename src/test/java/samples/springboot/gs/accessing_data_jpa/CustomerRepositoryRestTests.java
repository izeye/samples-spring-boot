package samples.springboot.gs.accessing_data_jpa;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.Is.is;

/**
 * Created by izeye on 15. 1. 28..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port=0")
public class CustomerRepositoryRestTests {

	@Value("${local.server.port}")
	int port;

	@Autowired
	CustomerRepository customerRepository;

	@Before
	public void setUp() {
		customerRepository.save(new Customer("Jack", "Bauer"));
		customerRepository.save(new Customer("Chloe", "O'Brian"));
		customerRepository.save(new Customer("Kim", "Bauer"));
		customerRepository.save(new Customer("David", "Palmer"));
		customerRepository.save(new Customer("Michelle", "Dessler"));
	}

	@Test
	public void testGet() {
		List<Customer> expectedCustomers = customerRepository.findAll();

		RestTemplate restTemplate = restTemplate();

		String url = "http://localhost:{port}/api/customers?page={page}&size={size}";

		ResponseEntity<PagedResources<Customer>> responseEntity = restTemplate.exchange(
				url, HttpMethod.GET, null,
				new ParameterizedTypeReference<PagedResources<Customer>>() {},
				port, 0, 100);
		PagedResources<Customer> resources = responseEntity.getBody();
		List<Customer> customers = new ArrayList(resources.getContent());
		assertThat(customers.size(), is(expectedCustomers.size()));
		assertThat(customers.get(0), instanceOf(Customer.class));
	}

	@Test
	public void testPost() {
		List<Customer> customersBefore = customerRepository.findAll();

		RestTemplate restTemplate = restTemplate();

		String url = "http://localhost:{port}/api/customers";

		Customer customer = new Customer();
		try {
			restTemplate.postForObject(url, customer, Void.class, port);
//			List<Customer> customersAfter = customerRepository.findAll();
//			assertThat(customersAfter.size(), is(customersBefore.size() + 1));
		} catch (HttpClientErrorException e) {
			assertThat(e.getStatusCode(), is(HttpStatus.METHOD_NOT_ALLOWED));
		}

	}

	private RestTemplate restTemplate() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.registerModule(new Jackson2HalModule());

		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(MediaType.parseMediaTypes("application/hal+json"));
		converter.setObjectMapper(mapper);
		return new RestTemplate(Arrays.asList(converter));
	}

}
