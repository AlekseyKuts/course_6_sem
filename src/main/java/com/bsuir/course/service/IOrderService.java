package com.bsuir.course.service;

import com.bsuir.course.model.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAll();
    void save(Order order);
    Order findById(Long id);
    void deleteById(Long id);
    void delete(Order order);
    List<Order> findByCarId(Long id);
}
