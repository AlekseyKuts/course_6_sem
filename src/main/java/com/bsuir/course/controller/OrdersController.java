package com.bsuir.course.controller;

import com.bsuir.course.model.Car;
import com.bsuir.course.model.Customer;
import com.bsuir.course.model.Order;
import com.bsuir.course.service.ICarService;
import com.bsuir.course.service.ICustomerService;
import com.bsuir.course.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

@Controller
@RequestMapping
public class OrdersController {
    @Autowired
    private ICarService carService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private ICustomerService customerService;

    @GetMapping("/cars/{id}/orders")
    public String getOrdersByCarId(@PathVariable("id") Long id, Model model){
        List<Order> orders = orderService.findByCarId(id);
        model.addAttribute("orders", orders);
        return "orders/showOrders";
    }

    @GetMapping("/orders/{id}")
    public String getOrderById(@PathVariable("id") Long id, Model model){
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        return "orders/showOrderById";
    }

    @GetMapping("/orders")
    public String getOrders(Model model){
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);

        List<List<Object>> chartData = new ArrayList<List<Object>>();
        Map<String, Integer> map = new HashMap<>();

        for (Order order : orders){
            if (map.get(order.getCar().getBrand()) == null) map.put(order.getCar().getBrand(), 1);
            else map.put(order.getCar().getBrand(), map.get(order.getCar().getBrand()) + 1);
        }

        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            chartData.add(List.of(pair.getKey(), pair.getValue()));
        }

        model.addAttribute("chartData", chartData);
        return "orders/showOrders";
    }

    @PostMapping("/cars/{id}/orders")
    public String addOrder(@PathVariable("id") Long id, @ModelAttribute("order") Order order, @ModelAttribute("customer") Customer customer, Model model){
        Car car = carService.findById(id);
        order.setCar(car);
        Customer nCustomer = customerService.findCustomerByNameAndPhone(customer.getName(), customer.getPhone());
        if (nCustomer==null) nCustomer = new Customer();
        nCustomer.setName(customer.getName());
        nCustomer.setPhone(customer.getPhone());
        nCustomer.setEmail(customer.getEmail());
        customerService.save(nCustomer);
        order.setCustomer(nCustomer);
        orderService.save(order);
        return "redirect:/cars/" + id + "?idCustomer=" + nCustomer.getId() + "#openModal";

    }

    @PostMapping("/orders/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return "redirect:/orders";
    }

    @PostMapping("/orders/{id}/switchCheckField")
    public String switchCheckField(@PathVariable("id") Long id, @ModelAttribute("order") Order order, @RequestParam String switchValue) {
        order = orderService.findById(id);
        order.setCheckField(switchValue);
        orderService.save(order);
        return "redirect:/orders";
    }




    @GetMapping("/orders/{id}/edit")
    public String updateOrder(Model model, @PathVariable("id") Long id){
        Order order = orderService.findById(id);
        model.addAttribute("order", order);
        model.addAttribute("customer", order.getCustomer());
        return "orders/updateOrder";
    }

    @PostMapping("/orders/{id}")
    public String update(@ModelAttribute("order") Order order, @ModelAttribute("customer") Customer customer, @PathVariable("id") Long id){
        order.setCar(orderService.findById(id).getCar());
        Customer nCustomer = customerService.findCustomerByNameAndPhone(customer.getName(), customer.getPhone());
        if (nCustomer==null) nCustomer = new Customer();
        nCustomer.setName(customer.getName());

        System.out.println(customer.getName());
        System.out.println(nCustomer.getName());

        nCustomer.setPhone(customer.getPhone());
        nCustomer.setEmail(customer.getEmail());

        customerService.save(nCustomer);
        order.setCustomer(nCustomer);
        orderService.save(order);
        return "redirect:/orders";
    }

}
