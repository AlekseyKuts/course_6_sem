package com.bsuir.course.controller;

import com.bsuir.course.model.Car;
import com.bsuir.course.model.Feedback;
import com.bsuir.course.model.Order;
import com.bsuir.course.model.ServiceEntry;
import com.bsuir.course.service.ICarService;
import com.bsuir.course.service.IFeedbackService;
import com.bsuir.course.service.IOrderService;
import com.bsuir.course.service.IServiceEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping
public class ServiceEntryController {

    @Autowired
    private IServiceEntryService serviceEntryService;

    @GetMapping("/serviceentries")
    public String showEntries(Model model) {

        List<ServiceEntry> serviceEntries = (List<ServiceEntry>) serviceEntryService.findAll();

        model.addAttribute("seriveentries", serviceEntries);

        return "serviceEntries/showServiceEntries";
    }

    @PostMapping("/serviceentries")
    public String add(@ModelAttribute("serviceentry") ServiceEntry serviceEntry){
        serviceEntryService.save(serviceEntry);

        return "redirect:/";
    }

    @GetMapping("/serviceentries/add")
    public String addServiceEntry(Model model){
        ServiceEntry serviceEntry = new ServiceEntry();
        model.addAttribute("serviceentry", new ServiceEntry());
        return "serviceEntries/addServiceEntry";
    }

}
