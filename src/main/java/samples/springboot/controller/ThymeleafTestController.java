package samples.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by izeye on 14. 12. 2..
 */
@Controller
@RequestMapping("/test/thymeleaf")
public class ThymeleafTestController {

    @RequestMapping(value = "/test_map", method = RequestMethod.GET)
    public String testMap(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("firstName", "Johnny");
        map.put("lastName", "Lim");

        model.addAttribute("map", map);

        return "test/thymeleaf/test_map";
    }

}
