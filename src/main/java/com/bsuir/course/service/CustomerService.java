package com.bsuir.course.service;

import com.bsuir.course.model.Customer;
import com.bsuir.course.model.ServiceRating;
import com.bsuir.course.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService implements ICustomerService{

    @Autowired
    private CustomerRepository repository;

    @Override
    public List<Customer> findAll() {
        return (List<Customer>) repository.findAll();
    }

    @Override
    public void save(Customer customer) {
        repository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        Optional<Customer> customer = repository.findById(id);
        if (customer.isPresent()) return customer.get();
        else return null;
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void delete(Customer customer) {
        repository.delete(customer);
    }

    @Override
    public Customer findCustomerByNameAndPhone(String name, String phone) {
        return repository.findCustomerByNameAndPhone(name, phone);
    }



}
