package samples.springboot.gs.serving_web_content;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by izeye on 15. 1. 26..
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class ApiAclPropertiesTests {

	@Autowired
	ApiAclProperties apiAclProperties;

	@Test
	public void test() {
		System.out.println(apiAclProperties);
	}

}
