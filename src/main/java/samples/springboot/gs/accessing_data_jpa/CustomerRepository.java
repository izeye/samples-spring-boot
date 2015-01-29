package samples.springboot.gs.accessing_data_jpa;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.List;

/**
 * Created by izeye on 14. 12. 24..
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	List<Customer> findByLastName(String lastName);

	@Cacheable("customers")
	Customer findOne(Long id);

	// NOTE:
	// This is only for test.
	// I think @CachePut is better than @CacheEvict for save().
	@CacheEvict(value = "customers", key = "#p0.id")
	@RestResource(exported = false)
	Customer save(Customer customer);

}
