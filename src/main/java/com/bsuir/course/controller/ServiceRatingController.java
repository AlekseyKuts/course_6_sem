package com.bsuir.course.controller;

import com.bsuir.course.model.Car;
import com.bsuir.course.model.Order;
import com.bsuir.course.model.ServiceRating;
import com.bsuir.course.service.ICarService;
import com.bsuir.course.service.IOrderService;
import com.bsuir.course.service.IServiceRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class ServiceRatingController {

    @Autowired
    private IServiceRatingService service;

    @GetMapping("/servicerating")
    public String findAll(Model model) {
        List<ServiceRating> serviceRatings = service.findAll();
        model.addAttribute("services", serviceRatings);
        return "servicerating/showServiceRating";
    }

    @PostMapping("/servicerating")
    public String add(@RequestParam(value = "rating", required = true) int rating, @RequestParam(value = "idPhone", required = true) String idPhone) {
        service.save(new ServiceRating(idPhone, rating));
        return "redirect:/cars";
    }

}
