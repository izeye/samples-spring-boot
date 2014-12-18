package samples.springboot.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import samples.springboot.mybatis.config.MyBatisProperties;
import samples.springboot.mybatis.dao.PersonDao;
import samples.springboot.mybatis.domain.Person;

import java.util.List;

/**
 * Created by izeye on 14. 12. 18..
 */
@SpringBootApplication
@EnableConfigurationProperties(MyBatisProperties.class)
public class SampleMyBatisApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SampleMyBatisApplication.class, args);
		PersonDao personDao = applicationContext.getBean(PersonDao.class);
		List<Person> persons = personDao.findAll();
		System.out.println(persons);
	}

}
