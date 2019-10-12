package communtiy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/thymeleaf")

public class ThymeleafController {

    @RequestMapping("/xixi")
    public String hello(Model model) {

        model.addAttribute("name", "Dear");

        return "xixi";

    }

}