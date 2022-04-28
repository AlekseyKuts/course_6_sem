package com.bsuir.course.controller;

import com.bsuir.course.model.Car;
import com.bsuir.course.model.Feedback;
import com.bsuir.course.model.Order;
import com.bsuir.course.model.TestdriveEntry;
import com.bsuir.course.service.ICarService;
import com.bsuir.course.service.IFeedbackService;
import com.bsuir.course.service.IOrderService;
import com.bsuir.course.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping
public class CarsController {

    @Autowired
    private ICarService carService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IFeedbackService feedbackService;


    @GetMapping("/cars")
    public String showCars(Model model) {

        List<Car> cars = (List<Car>) carService.findAll();

        model.addAttribute("trueCars", cars);
        model.addAttribute("cars", cars);

        return "cars/showCars";
    }

    @PostMapping("/cars/filter")
    public String showWithFilter(Model model, @RequestParam(value = "filters", required = false) List<String> filters, @RequestParam(value = "sortPrice", required = false) String sortPrice) {
        List<Car> trueCars = (List<Car>) carService.findAll();
        List<Car> cars = new ArrayList<Car>();
        if (filters == null || filters.size()==0) {
            cars.addAll(trueCars);
        } else  {
            for (Car car : trueCars) {
                if (filters.contains(car.getBrand())) {
                    cars.add(car);
                }
            }
        }
        if (sortPrice != null) {
            if (sortPrice.contains("ASC")) {
                Collections.sort(cars, new Comparator<Car>() {
                    @Override
                    public int compare(Car o1, Car o2) {
                        return Double.compare(o1.getPrice(), o2.getPrice());
                    }
                });
            } else if (sortPrice.contains("DESC")) {
                Collections.sort(cars, new Comparator<Car>() {
                    @Override
                    public int compare(Car o1, Car o2) {
                        return Double.compare(o2.getPrice(), o1.getPrice());
                    }
                });
            }
        }

        model.addAttribute("trueCars", trueCars);
        model.addAttribute("cars", cars);

        return "cars/showCars";
    }

    @PostMapping("/cars/search")
    public String showSearched(Model model, @RequestParam(value = "searchString", required = false) String searchString) {
        List<Car> trueCars = (List<Car>) carService.findAll();
        List<Car> cars = new ArrayList<Car>();
        if (searchString == null || searchString.length()==0) {
            cars.addAll(trueCars);
        }
        else {
            cars = carService.findByMarkAndBrandContaining(searchString);
        }
        if (cars.size() == 0){
            cars.addAll(trueCars);
        }

        model.addAttribute("trueCars", trueCars);
        model.addAttribute("cars", cars);

        return "cars/showCars";
    }

    @GetMapping("/cars/add")
    public String addCar(Model model){
        model.addAttribute("car", new Car());
        return "cars/addCar";
    }

    @GetMapping("/cars/{id}")
    public String showById(@PathVariable("id") Long id, Model model){
        Car car = carService.findById(id);
        if (car != null) {
            model.addAttribute("car", car);
            model.addAttribute("order", new Order());
            model.addAttribute("feedback", new Feedback());
            model.addAttribute("testdriveEntry", new TestdriveEntry());
            model.addAttribute("feedbacks", feedbackService.findByCarId(car.getId()));
            //return "cars/showCarById";
            return "cars/showCarById";
        }
        else {
            return "cars/noCarById";
        }
    }


    @PostMapping("/cars")
    public String add(@ModelAttribute("car") Car car){
        carService.save(car);

        return "redirect:/cars";
    }

    @GetMapping("/cars/{id}/edit")
    public String updateCar(Model model, @PathVariable("id") Long id){
        Car car = carService.findById(id);
        if (car != null) {
            model.addAttribute("car", car);
            return "cars/updateCar";
        }
        else {
            return "cars/noCarById";
        }
    }

    @PostMapping("/cars/{id}")
    public String update(@ModelAttribute("car") Car car, @PathVariable("id") Long id){
        carService.save(car);
        return "redirect:/cars";
    }

    @PostMapping("/cars/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        carService.deleteById(id);
        return "redirect:/cars";
    }




}
