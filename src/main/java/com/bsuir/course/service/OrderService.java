package com.bsuir.course.service;

import com.bsuir.course.model.Car;
import com.bsuir.course.model.Order;
import com.bsuir.course.repository.CarRepository;
import com.bsuir.course.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService implements IOrderService{

    @Autowired
    private OrderRepository repository;

    @Override
    public List<Order> findAll() {
        return (List<Order>) repository.findAll();
    }

    @Override
    public void save(Order order) {
        repository.save(order);
    }

    @Override
    public Order findById(Long id) {
        Optional<Order> order = repository.findById(id);
        if (order.isPresent()) return order.get();
        else return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Order order) {
        repository.delete(order);
    }

    @Override
    public List<Order> findByCarId(Long id) {
        return repository.findByCarId(id);
    }
}
