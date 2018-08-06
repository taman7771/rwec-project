package org.launchcode.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("resources")
public class ResourceController {

    @RequestMapping(value = "")
    public String index() {
        return "resource/index";
    }

}