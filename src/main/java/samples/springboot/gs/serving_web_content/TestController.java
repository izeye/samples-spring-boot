package samples.springboot.gs.serving_web_content;

import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by izeye on 15. 1. 22..
 */
@RestController
public class TestController {

	@RequestMapping("/gs/serving-web-content/testParameters")
	public String testParameters(@RequestParam("name") String name) {
		return name;
	}

	@RequestMapping("/gs/serving-web-content/testHeaders")
	public String testHeaders(@RequestHeader("name") String name) {
		return name;
	}

	@RequestMapping("/gs/serving-web-content/testRemoteAddr")
	public String testRemoteAddr(HttpServletRequest request) {
		return request.getRemoteAddr();
	}

}
