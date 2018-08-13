package org.launchcode.controllers;


import org.launchcode.models.User;
import org.launchcode.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import javax.validation.Valid;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserDao userDao;


    // Request path: /event
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("users", userDao.findAll());
        model.addAttribute("title", "Users");

        return "user/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddUserForm(Model model) {
        model.addAttribute("title", "Sign Up");
        model.addAttribute(new User());
        return "user/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddUserForm(@ModelAttribute @Valid User newUser,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Sign Up");
            return "user/add";
        }

        userDao.save(newUser);
        return "home/index";
    }



}
