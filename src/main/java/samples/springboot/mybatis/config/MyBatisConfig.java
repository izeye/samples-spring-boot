package samples.springboot.mybatis.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * Created by izeye on 14. 12. 18..
 */
@Configuration
public class MyBatisConfig {

	@Bean
	public DataSource dataSourceForMyBatis(
			@Value("${mybatis.driver-class-name}") String driverClassName,
			@Value("${mybatis.url}") String url,
			@Value("${mybatis.username}") String username,
			@Value("${mybatis.password}") String password) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}

	@Bean
	public SqlSessionFactoryBean sqlSessionFactoryForMyBatis(
			DataSource dataSource,
			@Value("${mybatis.config-location}") Resource configLocation,
			@Value("${mybatis.mapper-locations}") Resource[] mapperLocations) {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		sqlSessionFactoryBean.setConfigLocation(configLocation);
		sqlSessionFactoryBean.setMapperLocations(mapperLocations);
		return sqlSessionFactoryBean;
	}

	@Bean
	public SqlSession sqlSessionForMyBatis(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

}
