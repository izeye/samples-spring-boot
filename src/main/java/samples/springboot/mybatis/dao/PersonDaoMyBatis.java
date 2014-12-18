package samples.springboot.mybatis.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import samples.springboot.mybatis.domain.Person;

import java.util.List;

/**
 * Created by izeye on 14. 12. 18..
 */
@Repository
public class PersonDaoMyBatis implements PersonDao {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<Person> findAll() {
		PersonMapper mapper = sqlSession.getMapper(PersonMapper.class);
		return mapper.findAll();
	}

}
