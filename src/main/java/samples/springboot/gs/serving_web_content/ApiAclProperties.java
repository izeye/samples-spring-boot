package samples.springboot.gs.serving_web_content;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by izeye on 15. 1. 26..
 */
@ConfigurationProperties(prefix = "api.acl")
public class ApiAclProperties {

	private List<String> urlPatterns;
	private String ipAddressPatterns;

	public List<String> getUrlPatterns() {
		return urlPatterns;
	}

	public void setUrlPatterns(List<String> urlPatterns) {
		this.urlPatterns = urlPatterns;
	}

	public String getIpAddressPatterns() {
		return ipAddressPatterns;
	}

	public void setIpAddressPatterns(String ipAddressPatterns) {
		this.ipAddressPatterns = ipAddressPatterns;
	}

	@Override
	public String toString() {
		return "ApiAclProperties{" +
				"urlPatterns=" + urlPatterns +
				", ipAddressPatterns='" + ipAddressPatterns + '\'' +
				'}';
	}

}
