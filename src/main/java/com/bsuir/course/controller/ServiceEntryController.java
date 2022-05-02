package com.bsuir.course.controller;

import com.bsuir.course.model.*;
import com.bsuir.course.service.*;
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
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/serviceentries")
    public String showEntries(Model model) {

        List<ServiceEntry> serviceEntries = (List<ServiceEntry>) serviceEntryService.findAll();

        model.addAttribute("seriveentries", serviceEntries);

        return "serviceEntries/showServiceEntries";
    }

    @PostMapping("/serviceentries")
    public String add(@ModelAttribute("serviceentry") ServiceEntry serviceEntry, @ModelAttribute("customer") Customer customer){
        Customer nCustomer = customerService.findCustomerByNameAndPhone(customer.getName(), customer.getPhone());
        if (nCustomer==null) nCustomer = new Customer();
        nCustomer.setName(customer.getName());

        System.out.println(customer.getName());
        System.out.println(nCustomer.getName());

        nCustomer.setPhone(customer.getPhone());
        nCustomer.setEmail(customer.getEmail());

        customerService.save(nCustomer);
        serviceEntry.setCustomer(nCustomer);

        serviceEntryService.save(serviceEntry);

        return "redirect:/";
    }

    @GetMapping("/serviceentries/add")
    public String addServiceEntry(Model model){
        ServiceEntry serviceEntry = new ServiceEntry();
        model.addAttribute("serviceentry", new ServiceEntry());
        model.addAttribute("customer", new Customer());
        return "serviceEntries/addServiceEntry";
    }

    @PostMapping("/serviceentries/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        serviceEntryService.deleteById(id);
        return "redirect:/serviceentries";
    }

}
