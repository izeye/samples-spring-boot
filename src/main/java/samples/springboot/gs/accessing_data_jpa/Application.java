package samples.springboot.gs.accessing_data_jpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Created by izeye on 14. 12. 24..
 */
@SpringBootApplication
@EnableCaching
public class Application {

	@Bean
	public CacheManager cacheManager() {
		return new ConcurrentMapCacheManager("customers");
	}

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
		CustomerRepository customerRepository = applicationContext.getBean(CustomerRepository.class);

		customerRepository.save(new Customer("Jack", "Bauer"));
		customerRepository.save(new Customer("Chloe", "O'Brian"));
		customerRepository.save(new Customer("Kim", "Bauer"));
		customerRepository.save(new Customer("David", "Palmer"));
		customerRepository.save(new Customer("Michelle", "Dessler"));

		Iterable<Customer> customers = customerRepository.findAll();
		for (Customer customer : customers) {
			System.out.println(customer);
		}
		System.out.println();

		Customer customer = customerRepository.findOne(1L);
		System.out.println(customer);
		System.out.println();

		List<Customer> bauers = customerRepository.findByLastName("Bauer");
		for (Customer bauer : bauers) {
			System.out.println(bauer);
		}

//		applicationContext.close();
	}

}
