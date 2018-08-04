package org.launchcode.controllers;

import org.launchcode.models.Event;
import org.launchcode.models.EventData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("event")
public class EventController {

    // Request path: /event
    @RequestMapping(value = "")
    public String index(Model model) {

        model.addAttribute("events", EventData.getAll());
        model.addAttribute("title", "Events");

        return "event/index";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String displayAddEventForm(Model model) {
        model.addAttribute("title", "Add Event");
        return "event/add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String processAddEventForm(@ModelAttribute Event newEvent) {
        EventData.add(newEvent);
        return "redirect:";
    }

    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveEventForm(Model model) {
        model.addAttribute("events", EventData.getAll());
        model.addAttribute("title", "Remove Event");
        return "event/remove";
    }

    @RequestMapping(value = "remove", method = RequestMethod.POST)
    public String processRemoveEventForm(@RequestParam int[] eventIds) {

        for (int eventId : eventIds) {
            EventData.remove(eventId);
        }
        return "redirect:";
    }

}
