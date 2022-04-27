package com.bsuir.course.service;

import com.bsuir.course.model.Car;
import com.bsuir.course.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class CarService implements ICarService{

    @Autowired
    private CarRepository repository;

    @Override
    public List<Car> findAll() {
        return (List<Car>) repository.findAll();
    }

    @Override
    public void save(Car car) {
        repository.save(car);
    }

    @Override
    public Car findById(Long id) {
        Optional<Car> car = repository.findById(id);
        if (car.isPresent()) return car.get();
        else return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Car car) {
        repository.delete(car);
    }

    public List<Car> findByEngineContaining(String engine){
        List<Car> trueCars = (List<Car>) repository.findAll();
        engine = engine.toLowerCase(Locale.ROOT);
        List<Car> cars = new ArrayList<Car>();
        for (Car car: trueCars){
            if (car.getEngine().toLowerCase(Locale.ROOT).contains(engine)) cars.add(car);
        }
        return cars;
    }


}
