package samples.springboot.gs.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.cache.guava.GuavaCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

/**
 * Created by izeye on 14. 12. 23..
 */
@SpringBootApplication
@EnableCaching
public class Application implements CommandLineRunner {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private BookRepository bookRepository;

//	@Bean
//	public CacheManager cacheManager() {
//		return new ConcurrentMapCacheManager("books");
//	}

//	@Bean
//	public CacheManager cacheManager(net.sf.ehcache.CacheManager cacheManager) {
//		return new EhCacheCacheManager(cacheManager);
//	}
//
//	@Bean
//	public EhCacheManagerFactoryBean ehcache() {
//		EhCacheManagerFactoryBean ehCacheManagerFactoryBean = new EhCacheManagerFactoryBean();
//		ehCacheManagerFactoryBean.setConfigLocation(new ClassPathResource("samples/springboot/gs/caching/ehcache.xml"));
//		return ehCacheManagerFactoryBean;
//	}

	@Bean
	public CacheManager cacheManager() {
		return new GuavaCacheManager();
	}

	@Override
	public void run(String... args) throws Exception {
		log.info("Fetching books...");
		log.info("isbn-1234: {}", bookRepository.getByIsbn("isbn-1234"));
		log.info("isbn-1234: {}", bookRepository.getByIsbn("isbn-1234"));
		log.info("isbn-1234: {}", bookRepository.getByIsbn("isbn-1234"));
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
