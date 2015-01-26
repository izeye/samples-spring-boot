package samples.springboot.gs.serving_web_content;

import org.apache.catalina.filters.RemoteAddrFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.servlet.Filter;

/**
 * Created by izeye on 15. 1. 12..
 */
@SpringBootApplication
@EnableConfigurationProperties(ApiAclProperties.class)
public class Application {

	private final Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	ApiAclProperties apiAclProperties;

	@Bean
	public FilterRegistrationBean remoteAddressFilter() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setName("test1");
		RemoteAddrFilter filter = new RemoteAddrFilter();
//		filter.setAllow("127.0.0.1");
//		filter.setAllow("0:0:0:0:0:0:0:1");
		filter.setAllow(apiAclProperties.getIpAddressPatterns());
		filterRegistrationBean.setFilter(filter);
//		filterRegistrationBean.addUrlPatterns("/gs/serving-web-content/testParameters");
//		filterRegistrationBean.addUrlPatterns("/gs/serving-web-content/testHeaders");
		apiAclProperties.getUrlPatterns().forEach(
				urlPattern -> filterRegistrationBean.addUrlPatterns(urlPattern));
		return filterRegistrationBean;
	}

//	@Bean
	public FilterRegistrationBean remoteAddressFilter2() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		RemoteAddrFilter filter = new RemoteAddrFilter();
		filterRegistrationBean.setName("test2");
		filter.setAllow("127.0.0.1");
//		filter.setAllow("0:0:0:0:0:0:0:1");
		filterRegistrationBean.setFilter(filter);
		filterRegistrationBean.addUrlPatterns("/gs/serving-web-content/testHeaders");
		return filterRegistrationBean;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
