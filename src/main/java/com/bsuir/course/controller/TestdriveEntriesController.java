package com.bsuir.course.controller;

import com.bsuir.course.model.Car;
import com.bsuir.course.model.Customer;
import com.bsuir.course.model.TestdriveEntry;
import com.bsuir.course.service.ICarService;
import com.bsuir.course.service.ICustomerService;
import com.bsuir.course.service.ITestdriveEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class TestdriveEntriesController {
    @Autowired
    private ICarService carService;
    @Autowired
    private ITestdriveEntryService testdriveEntryService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/cars/{id}/testdriveEntries")
    public String getTestdriveEntriesByCarId(@PathVariable("id") Long id, Model model){
        List<TestdriveEntry> testdriveEntries = testdriveEntryService.findByCarId(id);
        model.addAttribute("testdriveEntries", testdriveEntries);
        return "testdriveEntries/showTestdriveEntries";
    }

    @GetMapping("/testdriveEntries/{id}")
    public String getTestdriveEntryById(@PathVariable("id") Long id, Model model){
        TestdriveEntry testdriveEntry = testdriveEntryService.findById(id);
        model.addAttribute("testdriveEntry", testdriveEntry);
        return "testdriveEntries/showTestdriveEntryById";
    }

    @GetMapping("/testdriveEntries")
    public String getTestdriveEntries(Model model){
        List<TestdriveEntry> testdriveEntries = testdriveEntryService.findAll();
        model.addAttribute("testdriveEntries", testdriveEntries);
        return "testdriveEntries/showTestdriveEntries";
    }

    @PostMapping("/cars/{id}/testdriveEntries")
    public String addTestdriveEntry(@PathVariable("id") Long id, @ModelAttribute("customer") Customer customer, @ModelAttribute("testdriveEntry") TestdriveEntry testdriveEntry, Model model){
        Car car = carService.findById(id);
        testdriveEntry.setCar(car);
        Customer nCustomer = customerService.findCustomerByNameAndPhone(customer.getName(), customer.getPhone());
        if (nCustomer==null) nCustomer = new Customer();
        nCustomer.setName(customer.getName());
        nCustomer.setPhone(customer.getPhone());
        nCustomer.setEmail(customer.getEmail());
        customerService.save(nCustomer);
        testdriveEntry.setCustomer(nCustomer);
        testdriveEntryService.save(testdriveEntry);
        return "redirect:/cars/" + id + "?idCustomer=" + nCustomer.getId() + "#openModal";
    }

    @PostMapping("/testdriveEntries/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        testdriveEntryService.deleteById(id);
        return "redirect:/testdriveEntries";
    }

    @PostMapping("/testdriveEntries/{id}/switchCheckField")
    public String switchCheckField(@PathVariable("id") Long id, @ModelAttribute("testdriveEntry") TestdriveEntry testdriveEntry, @RequestParam String switchValue) {
        testdriveEntry = testdriveEntryService.findById(id);
        testdriveEntry.setCheckField(switchValue);
        testdriveEntryService.save(testdriveEntry);
        return "redirect:/testdriveEntries";
    }




    @GetMapping("/testdriveEntries/{id}/edit")
    public String updateTestdriveEntry(Model model, @PathVariable("id") Long id){
        TestdriveEntry testdriveEntry = testdriveEntryService.findById(id);
        model.addAttribute("testdriveEntry", testdriveEntry);
        model.addAttribute("customer", testdriveEntry.getCustomer());
        return "testdriveEntries/updateTestdriveEntry";
    }

    @PostMapping("/testdriveEntries/{id}")
    public String update(@ModelAttribute("testdriveEntry") TestdriveEntry testdriveEntry, @ModelAttribute("customer") Customer customer, @PathVariable("id") Long id){
        testdriveEntry.setCar(testdriveEntryService.findById(id).getCar());
        Customer nCustomer = customerService.findCustomerByNameAndPhone(customer.getName(), customer.getPhone());
        if (nCustomer==null) nCustomer = new Customer();
        nCustomer.setName(customer.getName());

        System.out.println(customer.getName());
        System.out.println(nCustomer.getName());

        nCustomer.setPhone(customer.getPhone());
        nCustomer.setEmail(customer.getEmail());

        customerService.save(nCustomer);
        testdriveEntry.setCustomer(nCustomer);
        testdriveEntryService.save(testdriveEntry);
        return "redirect:/testdriveEntries";
    }

}
