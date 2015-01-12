package samples.springboot.gs.serving_web_content;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by izeye on 15. 1. 12..
 */
@Controller
public class GreetingController {

	@RequestMapping("/gs/serving-web-content/greeting")
	public String greeting(
			@RequestParam(value = "name", required = false, defaultValue = "World") String name,
			Model model) {
		model.addAttribute("name", name);
		return "gs/serving-web-content/greeting";
	}

}
