package samples.springboot.gs.serving_web_content;

import org.apache.catalina.valves.RemoteAddrValve;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by izeye on 15. 1. 26..
 */
//@Component
public class TomcatRemoteAddrValveCustomizer implements EmbeddedServletContainerCustomizer {

	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		TomcatEmbeddedServletContainerFactory tomcatContainer =
				(TomcatEmbeddedServletContainerFactory) container;
		RemoteAddrValve remoteAddrValve = new RemoteAddrValve();
//		remoteAddrValve.setAllow("127.0.0.1");
		remoteAddrValve.setAllow("0:0:0:0:0:0:0:1");
		tomcatContainer.addContextValves(remoteAddrValve);
	}

}
