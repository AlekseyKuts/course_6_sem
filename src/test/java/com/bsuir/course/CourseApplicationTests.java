package com.bsuir.course;

import com.bsuir.course.model.Car;
import com.bsuir.course.model.Feedback;
import com.bsuir.course.model.Order;
import com.bsuir.course.service.ICarService;
import com.bsuir.course.service.ICustomerService;
import com.bsuir.course.service.IFeedbackService;
import com.bsuir.course.service.IOrderService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseApplicationTests {

    @Autowired
    private ICarService carService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IFeedbackService feedbackService;

    @Test
    void carServiceTest() {
        Car car = new Car("TestBrand","TestModel","TestEngine", 2012, "TestColor", 20.05, 20);
        carService.save(car);
        Car nCar = carService.findCarByBrandAndModel("TestBrand","TestModel");
        Assert.assertEquals(car.getBrand(), nCar.getBrand());
    }

    @Test
    void orderServiceTest() {
        Order order = new Order("TestSpecialNoteOrder","", carService.findById(Long.valueOf(15)), customerService.findById(Long.valueOf(6)));
        orderService.save(order);
        Order nOrder = orderService.findOrderByNoteOrder("TestSpecialNoteOrder");
        Assert.assertEquals(nOrder.getNoteOrder(), order.getNoteOrder());
    }

    @Test
    void feedbackServiceTest() {
        Feedback feedback = new Feedback("TestFeedbackName", "TestFeedbackMessage", carService.findById(Long.valueOf(15)));
        feedbackService.save(feedback);
        Feedback nFeedback = feedbackService.findFeedbackByNameAndFeedbackField("TestFeedbackName", "TestFeedbackMessage");
        Assert.assertEquals(nFeedback.getFeedbackField(), feedback.getFeedbackField());
    }

}
