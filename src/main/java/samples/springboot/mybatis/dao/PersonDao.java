package samples.springboot.mybatis.dao;

import samples.springboot.mybatis.domain.Person;

import java.util.List;

/**
 * Created by izeye on 14. 12. 18..
 */
public interface PersonDao {

	List<Person> findAll();

}
