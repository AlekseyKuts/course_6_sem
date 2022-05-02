package com.bsuir.course.controller;

import com.bsuir.course.model.Car;
import com.bsuir.course.model.Feedback;
import com.bsuir.course.model.Order;
import com.bsuir.course.service.ICarService;
import com.bsuir.course.service.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping
public class FeedbacksController {

    @Autowired
    private ICarService carService;

    @Autowired
    private IFeedbackService feedbackService;

    @PostMapping("/cars/{id}/feedbacks")
    public String addFeedback(@PathVariable("id") Long id, @ModelAttribute("feedback") Feedback feedback, Model model){
        Car car = carService.findById(id);
        feedback.setCar(car);
        feedbackService.save(feedback);
        return "redirect:/cars/" + id;
    }

    @GetMapping("/feedbacks")
    public String showFeedbacks(Model model){
        List<Feedback> feedbacks = (List<Feedback>) feedbackService.findAll();

        model.addAttribute("feedbacks", feedbacks);

        return "feedbacks/showFeedbacks";
    }
}
