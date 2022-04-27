package com.bsuir.course.service;

import com.bsuir.course.model.Car;

import java.util.List;
import java.util.Optional;

public interface ICarService {
    List<Car> findAll();
    void save(Car car);
    Car findById(Long id);
    void deleteById(Long id);
    void delete(Car car);
    public List<Car> findByEngineContaining(String engine);
}
