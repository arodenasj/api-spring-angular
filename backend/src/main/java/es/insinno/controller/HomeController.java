package es.insinno.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "redirect:/api/resumes";
    }

    @GetMapping("/swagger")
    public String swagger() {
        return "redirect:/swagger-ui/index.html";
    }
}
