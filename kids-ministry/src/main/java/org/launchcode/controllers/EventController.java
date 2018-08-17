package org.launchcode.controllers;

import org.launchcode.models.Event;
import org.launchcode.models.data.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("event")
public class EventController {

    @Autowired
    private EventDao eventDao;


    // Request path: /event
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("events", eventDao.findAll());
        model.addAttribute("title", "Events");

        return "event/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddEventForm(Model model) {
        model.addAttribute("title", "Add Event");
        model.addAttribute(new Event());
        return "event/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddEventForm(@ModelAttribute @Valid Event newEvent,
                                      Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Event");
            return "event/add";
        }

        eventDao.save(newEvent);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveEventForm(Model model) {
        model.addAttribute("events", eventDao.findAll());
        model.addAttribute("title", "Remove Event");
        return "event/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveEventForm(@RequestParam int[] eventIds) {

        for (int eventId : eventIds) {
            eventDao.deleteById(eventId);
        }
        return "redirect:";
    }

    @InitBinder
    public void initBinder(final WebDataBinder binder){
        final SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
