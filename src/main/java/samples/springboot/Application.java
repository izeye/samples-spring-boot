package samples.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.event.SpringApplicationEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by izeye on 2014. 11. 14..
 */
@EnableAutoConfiguration
@ComponentScan
public class Application {

	@Bean
	public ApplicationListener<ApplicationEvent> applicationListener() {
		return new ApplicationListener<ApplicationEvent>() {
			@Override
			public void onApplicationEvent(ApplicationEvent event) {
				System.out.println("In applicationListener: " + event);
			}
		};
	}

	// NOTE:
	// Impossible to register ApplicationListener for SpringApplicationEvents
	// by bean.
	@Bean
	public ApplicationListener<SpringApplicationEvent> springApplicationListener() {
		return new ApplicationListener<SpringApplicationEvent>() {
			@Override
			public void onApplicationEvent(SpringApplicationEvent event) {
				System.out.println("In springApplicationListener: " + event);
			}
		};
	}

    public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
    }

}
