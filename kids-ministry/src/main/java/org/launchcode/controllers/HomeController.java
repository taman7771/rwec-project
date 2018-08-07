package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("home")
public class HomeController {

    @RequestMapping(value = "")
    public String index(Model model) {
        model.addAttribute("title", "Home");
        return "home/index";
    }

}