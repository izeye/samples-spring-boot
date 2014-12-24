package samples.springboot.gs.accessing_data_jpa;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsSame.sameInstance;

/**
 * Created by izeye on 14. 12. 24..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class CustomerRepositoryTests {

	@Autowired
	CustomerRepository repository;

	@Autowired
	CacheManager cacheManager;

	@Test
	public void testCacheable() {
		Customer customer = new Customer("Johnny", "Lim");

		repository.save(customer);

		Customer found = repository.findOne(customer.getId());
		assertThat(repository.findOne(customer.getId()), is(sameInstance(found)));
	}

	@Test
	public void testCacheEvict() {
		Customer customer = new Customer("Johnny", "Lim");
		Customer anotherCustomer = new Customer("Johnny", "Lee");

		repository.save(customer);
		repository.save(anotherCustomer);

		Customer found = repository.findOne(customer.getId());
		assertThat(repository.findOne(customer.getId()), is(sameInstance(found)));

		Customer foundAnother = repository.findOne(anotherCustomer.getId());
		assertThat(repository.findOne(anotherCustomer.getId()), is(sameInstance(foundAnother)));

		Cache cache = cacheManager.getCache("customers");

		Cache.ValueWrapper valueWrapper = cache.get(found.getId());
		Cache.ValueWrapper anotherValueWrapper = cache.get(foundAnother.getId());
		System.out.println(valueWrapper == null ? null : valueWrapper.get());
		System.out.println(anotherValueWrapper == null ? null : anotherValueWrapper.get());

		found.setLastName("Kim");
		repository.save(found);

		valueWrapper = cache.get(found.getId());
		anotherValueWrapper = cache.get(foundAnother.getId());
		System.out.println(valueWrapper == null ? null : valueWrapper.get());
		System.out.println(anotherValueWrapper == null ? null : anotherValueWrapper.get());

		assertThat(repository.findOne(found.getId()), is(not(sameInstance(found))));
		assertThat(repository.findOne(foundAnother.getId()), is(sameInstance(foundAnother)));
	}

}
