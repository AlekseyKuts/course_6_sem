package com.bsuir.course;

import com.bsuir.course.model.Car;
import com.bsuir.course.service.ICarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CourseApplicationTests {

    @Autowired
    private ICarService carService;

    @Test
    void carServiceTest() {
        Car car = new Car("TestBrand","TestModel","TestEngine", 2012, "TestColor", 20.05, 20);
        carService.save(car);

    }



}
