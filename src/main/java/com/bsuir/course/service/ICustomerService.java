package com.bsuir.course.service;

import com.bsuir.course.model.Customer;
import com.bsuir.course.model.ServiceRating;

import java.util.List;

public interface ICustomerService {
    List<Customer> findAll();
    void save(Customer customer);
    Customer findById(Long id);
    void deleteById(Long id);
    void delete(Customer customer);
    Customer findCustomerByNameAndPhone(String name, String phone);
}
